package br.gasmartins.basket.infra.messaging.config;

import br.gasmartins.schemas.basket.order_submitted.OrderSubmitted;
import br.gasmartins.schemas.orders.order_created.OrderCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    private final TopicProperties properties;

    @Bean
    public ReplyingKafkaTemplate<String, OrderSubmitted, OrderCreated> replyingKafkaTemplate(ProducerFactory<String, OrderSubmitted> pf, ConcurrentKafkaListenerContainerFactory<String, OrderCreated> factory) {
        String topic = properties.getInputTopic(TopicProperties.ORDER_STATUS_TOPIC);
        ConcurrentMessageListenerContainer<String, OrderCreated> replyContainer = factory.createContainer(topic);
        replyContainer.getContainerProperties().setMissingTopicsFatal(false);
        replyContainer.getContainerProperties().setGroupId(groupId);
        return new ReplyingKafkaTemplate<>(pf, replyContainer);
    }

}
