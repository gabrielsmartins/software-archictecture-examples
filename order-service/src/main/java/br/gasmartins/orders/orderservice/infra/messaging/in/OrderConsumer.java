package br.gasmartins.orders.orderservice.infra.messaging.in;

import br.gasmartins.orders.orderservice.infra.async.adapter.OrderAsyncProcessor;
import br.gasmartins.orders.orderservice.infra.messaging.in.mapper.OrderConsumerMapper;
import br.gasmartins.schemas.orders.order_submitted.OrderSubmitted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import static net.logstash.logback.argument.StructuredArguments.kv;

@KafkaListener(topics = "${topics.input.basket}", groupId = "${spring.kafka.consumer.group-id}")
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final OrderAsyncProcessor processor;
    private final OrderConsumerMapper mapper;

    @KafkaHandler
    public void consume(@Headers MessageHeaders headers, @Payload OrderSubmitted message){
        log.info("Reading message: {},{}", kv("headers", headers), kv("payload", message));

        log.info("Mapping message to order: {}", kv("message", message));
        var order = this.mapper.map(message);
        log.info("Order was mapped successfully: {}", kv("order", message));

        log.info("Processing order: {}", kv("order", order));
        this.processor.process(order);
        log.info("Order was processed successfully");
    }

    @KafkaHandler(isDefault = true)
    public void consume(Object object){
        log.warn("Unrecognized message: {}", object);
    }

}
