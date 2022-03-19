package br.gasmartins.orders.infra.messaging;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.publisher.OrderPublisher;
import br.gasmartins.orders.infra.messaging.config.TopicProperties;
import br.gasmartins.orders.infra.messaging.mapper.OrderProducerMapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProducer implements OrderPublisher {

    private final KafkaTemplate<String, SpecificRecord> kafkaTemplate;
    private final OrderProducerMapperFactory factory;
    private final TopicProperties properties;

    @Override
    public void publish(Order order) {
        var mapper = this.factory.createMapper(order);
        var message = mapper.mapToMessage(order);
        var topic = this.properties.getOutputTopic(TopicProperties.ORDER_TOPIC);
        var key = String.valueOf(order.getId());
        this.kafkaTemplate.send(topic, key, message).addCallback(result -> log.info("Message was sent successfully: {}", result),
                                                     ex -> log.error("Error sending message", ex));
    }

}
