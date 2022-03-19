package br.gasmartins.orders.infra.messaging;

import br.gasmartins.orders.infra.messaging.config.TopicProperties;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Map;

import static br.gasmartins.orders.domain.support.OrderSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, controlledShutdown = true)
@ActiveProfiles("test")
public class OrderProducerTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private OrderProducer producer;

    @Autowired
    private TopicProperties topicProperties;

    private String topic;

    @BeforeEach
    public void setup() {
        this.topic = this.topicProperties.getOutputTopic(TopicProperties.ORDER_TOPIC);
    }

    @Test
    @DisplayName("Given Order When Send Call Template")
    public void givenOrderWhenSendCallTemplate() {

        var orderItem = defaultOrderItem().build();
        var orderPaymentMethod = defaultOrderPaymentMethod().build();

        var order = defaultOrder().build();
        order.addItem(orderItem);
        order.addPaymentMethod(orderPaymentMethod);

        this.producer.dispatch(order);
        var consumer = createConsumer();
        var singleRecord = KafkaTestUtils.getSingleRecord(consumer, this.topic);

        assertThat(singleRecord).isNotNull();
        assertThat(singleRecord.key()).isEqualTo(order.getId().toString());
        assertThat(singleRecord.value()).isNotNull();
    }


    private Consumer<String, SpecificRecord> createConsumer() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(OrderProducerTest.class.getSimpleName(), "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        consumerProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        consumerProps.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://http://localhost:8081");
        Consumer<String, SpecificRecord> consumer = new DefaultKafkaConsumerFactory<String, SpecificRecord>(consumerProps).createConsumer();
        consumer.subscribe(Collections.singleton(this.topic));
        return consumer;
    }


}
