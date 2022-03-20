package br.gasmartins.orders.application.messaging;

import br.gasmartins.orders.MessagingConfig;
import br.gasmartins.orders.application.messaging.mapper.OrderBasketConsumerMapper;
import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.infra.async.adapter.OrderAsyncProcessor;
import br.gasmartins.orders.infra.messaging.config.TopicProperties;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static br.gasmartins.orders.application.messaging.support.BasketMessageSupport.defaultOrderMessage;
import static org.awaitility.Awaitility.waitAtMost;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = MessagingConfig.class)
@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, controlledShutdown = true)
@ActiveProfiles("test")
public class OrderBasketConsumerTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @InjectMocks
    private OrderBasketConsumer consumer;

    @MockBean
    private OrderAsyncProcessor processor;

    @SpyBean
    private OrderBasketConsumerMapper mapper;

    @Autowired
    private TopicProperties topicProperties;

    private AutoCloseable closeable;

    private String topic;

    @BeforeEach
    public void setup(){
        this.closeable = MockitoAnnotations.openMocks(this);
        this.topic = this.topicProperties.getInputTopic(TopicProperties.BASKET_TOPIC);
    }


    @Test
    @DisplayName("Given Order When Create Then Return Created Order")
    public void givenOrderWhenCreateThenReturnCreatedOrder(){
        var message = defaultOrderMessage().build();

        Producer<String, SpecificRecord> producer = createProducer();

        producer.send(new ProducerRecord<>(topic, UUID.randomUUID().toString(), message));
        producer.flush();
        producer.close();

        waitAtMost(20, TimeUnit.SECONDS).untilAsserted(() -> {
            verify(this.processor, times(1)).process(any(Order.class));
        });
    }

    private Producer<String, SpecificRecord> createProducer() {
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        producerProps.put(KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, "true");
        producerProps.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://http://localhost:8081");
        return new DefaultKafkaProducerFactory<String, SpecificRecord>(producerProps).createProducer();
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
}
