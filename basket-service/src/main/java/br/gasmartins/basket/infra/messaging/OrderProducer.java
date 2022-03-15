package br.gasmartins.basket.infra.messaging;

import br.gasmartins.basket.domain.Order;
import br.gasmartins.basket.domain.dispatcher.OrderDispatcher;
import br.gasmartins.basket.infra.messaging.config.TopicProperties;
import br.gasmartins.basket.infra.messaging.mapper.OrderProducerMapper;
import br.gasmartins.schemas.basket.order_submitted.OrderSubmitted;
import br.gasmartins.schemas.orders.order_created.OrderCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(TopicProperties.class)
@Slf4j
public class OrderProducer implements OrderDispatcher {

    private final OrderProducerMapper mapper;
    private final TopicProperties properties;
    private final ReplyingKafkaTemplate<String, OrderSubmitted, OrderCreated> template;

    @Override
    public Mono<Order> dispatch(Order order) {
        log.info("Mapping order: {}", kv("order", order));
        var payload = this.mapper.mapToMessage(order);

        log.info("Sending order: {}", kv("payload", payload));
        String basketTopic = this.properties.getOutputTopic(TopicProperties.BASKET_TOPIC);

        var message = new ProducerRecord<>(basketTopic, payload.getCustomerId(), payload);
        var future = this.template.sendAndReceive(message, Duration.ofSeconds(5L));
        return Mono.fromFuture(future.completable())
                   .doOnSuccess(o -> log.info("Order submitted successfully: {}", kv("order", o)))
                   .doOnError(throwable -> log.error("Error submitting order", throwable))
                   .map(r -> this.mapper.mapToDomain(r.value()));
    }

}