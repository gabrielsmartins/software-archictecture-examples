package br.gasmartins.orders.orderservice.infra.messaging.out;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.publisher.OrderPublisher;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProducer implements OrderPublisher {

    private final KafkaTemplate<String, SpecificRecord> kafkaTemplate;

    @Override
    public void publish(Order order) {

    }

}
