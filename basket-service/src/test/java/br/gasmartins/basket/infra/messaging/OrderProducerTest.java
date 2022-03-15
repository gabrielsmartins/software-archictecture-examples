package br.gasmartins.basket.infra.messaging;

import br.gasmartins.basket.infra.messaging.config.TopicProperties;
import br.gasmartins.schemas.basket.order_submitted.OrderSubmitted;
import br.gasmartins.schemas.orders.order_created.Item;
import br.gasmartins.schemas.orders.order_created.OrderCreated;
import br.gasmartins.schemas.orders.order_created.PaymentMethod;
import br.gasmartins.schemas.orders.order_created.PaymentType;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static br.gasmartins.basket.domain.support.OrderSupport.*;

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

    private String inputTopic;
    private String outputTopic;

    @BeforeEach
    public void setup() {
        this.outputTopic = this.topicProperties.getOutputTopic(TopicProperties.BASKET_TOPIC);
        this.inputTopic = this.topicProperties.getInputTopic(TopicProperties.ORDER_STATUS_TOPIC);
    }

    @Test
    @DisplayName("Given Order When Send Call Template")
    public void givenOrderWhenSendCallTemplate() {

        var orderItem = defaultOrderItem().build();
        var orderPaymentMethod = defaultOrderPaymentMethod().build();

        var order = defaultOrder().build();
        order.addItem(orderItem);
        order.addPaymentMethod(orderPaymentMethod);

        var orderMono = this.producer.dispatch(order);
        this.startPolling();

        orderMono.as(StepVerifier::create)
                 .expectNextCount(1)
                 .verifyComplete();
    }


    private void startPolling(){
        var consumer = createConsumer();
        var producer = createProducer();
        var singleRecord = KafkaTestUtils.getSingleRecord(consumer, this.outputTopic);
        var orderCreatedMessage = createOrderCreatedMessage((OrderSubmitted) singleRecord.value());
        ProducerRecord<String, SpecificRecord> producerRecord = new ProducerRecord<>(this.inputTopic, singleRecord.key(), orderCreatedMessage);
        Header header = singleRecord.headers().lastHeader(KafkaHeaders.CORRELATION_ID);
        producerRecord.headers().add(KafkaHeaders.CORRELATION_ID, header.value());
        producer.send(producerRecord);
    }

    private Producer<String, SpecificRecord> createProducer() {
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        producerProps.put(KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, "true");
        producerProps.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://http://localhost:8081");
        return new DefaultKafkaProducerFactory<String, SpecificRecord>(producerProps).createProducer();
    }

    private Consumer<String, SpecificRecord> createConsumer() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(OrderProducerTest.class.getSimpleName(), "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        consumerProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        consumerProps.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://http://localhost:8081");
        Consumer<String, SpecificRecord> consumer = new DefaultKafkaConsumerFactory<String, SpecificRecord>(consumerProps).createConsumer();
        consumer.subscribe(Collections.singleton(this.outputTopic));
        return consumer;
    }
    
    private OrderCreated createOrderCreatedMessage(OrderSubmitted message){

       var items = message.getItems()
                                    .stream()
                                    .map(this::map)
                                    .collect(Collectors.toList());

        var paymentMethods = message.getPaymentMethods()
                                                      .stream()
                                                      .map(this::map)
                                                      .collect(Collectors.toList());
        return OrderCreated.newBuilder()
                .setId(1L)
                .setCreatedAt(LocalDateTime.now())
                .setCustomerId(message.getCustomerId())
                .setTotalAmount(message.getTotalAmount())
                .setTotalDiscount(message.getTotalDiscount())
                .setItems(items)
                .setPaymentMethods(paymentMethods)
                 .build();
    }

    private PaymentMethod map(br.gasmartins.schemas.basket.order_submitted.PaymentMethod paymentMethod) {
        return PaymentMethod.newBuilder()
                .setPaymentType(PaymentType.valueOf(paymentMethod.getPaymentType().name()))
                .setAmount(paymentMethod.getAmount())
                .build();
    }

    private Item map(br.gasmartins.schemas.basket.order_submitted.Item item) {
        return Item.newBuilder()
                .setProductId(item.getProductId())
                .setAmount(item.getAmount())
                .setQuantity(item.getQuantity())
                .build();
    }
}
