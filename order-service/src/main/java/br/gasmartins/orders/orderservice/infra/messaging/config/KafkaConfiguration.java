package br.gasmartins.orders.orderservice.infra.messaging.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafkaStreams
public class KafkaConfiguration {

    @Bean
    public NewTopic basketTopic() {
        return TopicBuilder.name("basket-orders")
                .partitions(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic fraudTopic() {
        return TopicBuilder.name("fraud-orders")
                .partitions(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name("payment-orders")
                .partitions(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic orderStatusTopic() {
        return TopicBuilder.name("order-status")
                .partitions(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic inventoryTopic() {
        return TopicBuilder.name("inventory-orders")
                .partitions(3)
                .compact()
                .build();
    }

}
