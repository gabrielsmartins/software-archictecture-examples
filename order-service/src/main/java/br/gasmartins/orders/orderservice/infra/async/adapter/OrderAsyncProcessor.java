package br.gasmartins.orders.orderservice.infra.async.adapter;

import br.gasmartins.orders.orderservice.domain.processor.OrderProcessor;
import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.infra.async.event.OrderEventFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderAsyncProcessor implements OrderProcessor {

    private final ApplicationEventPublisher publisher;
    private final OrderEventFactory factory;

    @Override
    public void process(Order order) {
        var event = this.factory.getEvent(order);
        this.publisher.publishEvent(event);
    }
}
