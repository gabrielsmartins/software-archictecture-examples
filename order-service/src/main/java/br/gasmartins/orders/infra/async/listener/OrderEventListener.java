package br.gasmartins.orders.infra.async.listener;

import br.gasmartins.orders.application.OrderService;
import br.gasmartins.orders.infra.async.event.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

    private final OrderService service;

    @EventListener(OrderSubmittedEvent.class)
    public void create(OrderSubmittedEvent event){
        var order = event.getOrder();
        this.service.create(order);
    }

    @EventListener(OrderValidatedEvent.class)
    public void create(OrderValidatedEvent event){
        var order = event.getOrder();
        this.service.validate(order);
    }

    @EventListener(OrderConfirmedEvent.class)
    public void create(OrderConfirmedEvent event){
        var order = event.getOrder();
        this.service.create(order);
    }

    @EventListener(OrderDeliveredEvent.class)
    public void create(OrderDeliveredEvent event){
        var order = event.getOrder();
        this.service.deliver(order);
    }

    @EventListener(OrderRejectedEvent.class)
    public void create(OrderRejectedEvent event){
        var order = event.getOrder();
        this.service.reject(order);
    }

    @EventListener(OrderFinishedEvent.class)
    public void create(OrderFinishedEvent event){
        var order = event.getOrder();
        this.service.finish(order);
    }

}
