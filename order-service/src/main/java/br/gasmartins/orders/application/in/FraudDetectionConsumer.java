package br.gasmartins.orders.application.in;

import org.springframework.kafka.annotation.KafkaListener;

@KafkaListener(topics = "${topics.input.fraud}", groupId = "${spring.kafka.consumer.group-id}")
public class FraudDetectionConsumer {


    public void consume(){

    }

}
