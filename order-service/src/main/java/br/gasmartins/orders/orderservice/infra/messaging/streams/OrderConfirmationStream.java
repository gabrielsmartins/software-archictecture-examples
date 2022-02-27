package br.gasmartins.orders.orderservice.infra.messaging.streams;

import br.gasmartins.orders.orderservice.infra.messaging.config.TopicProperties;
import br.gasmartins.orders.orderservice.infra.messaging.streams.mapper.OrderConfirmationStreamMapper;
import br.gasmartins.schemas.inventories.inventory_allocated.InventoryAllocated;
import br.gasmartins.schemas.payments.payment_accepted.PaymentAccepted;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(TopicProperties.class)
@Slf4j
public class OrderConfirmationStream {

    private final TopicProperties topicProperties;
    private final OrderConfirmationStreamMapper mapper;

    @Bean
    public KStream<String, PaymentAccepted> stream(StreamsBuilder builder){
        var paymentTopic = this.topicProperties.getInputTopic(TopicProperties.PAYMENT_TOPIC);
        var inventoryTopic = this.topicProperties.getInputTopic(TopicProperties.INVENTORY_TOPIC);
        var shippingTopic = this.topicProperties.getInputTopic(TopicProperties.SHIPPING_TOPIC);

        KStream<String, PaymentAccepted> paymentAcceptedStream = builder.stream(paymentTopic);
        KStream<String, InventoryAllocated> inventoryAllocatedStream = builder.stream(inventoryTopic);

        SpecificAvroSerde<PaymentAccepted> paymentAvroSerde = new SpecificAvroSerde<>();
        SpecificAvroSerde<InventoryAllocated> inventorySerde = new SpecificAvroSerde<>();

         paymentAcceptedStream.join(inventoryAllocatedStream,
                                    this.mapper,
                                    JoinWindows.of(Duration.ofSeconds(10)),
                                    StreamJoined.with(Serdes.String(), paymentAvroSerde, inventorySerde))
                                                .peek((k, v) -> log.info("Sending shipping event: {},{}", k,v))
                                                .to(shippingTopic);
        return paymentAcceptedStream;
    }
}
