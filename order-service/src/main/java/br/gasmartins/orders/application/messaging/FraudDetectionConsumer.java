package br.gasmartins.orders.application.messaging;

import org.springframework.kafka.annotation.KafkaListener;

@KafkaListener(topics = "${topics.input.fraud}", groupId = "${spring.kafka.consumer.group-id}")
public class FraudDetectionConsumer {


    public void consume(){

    }

}
