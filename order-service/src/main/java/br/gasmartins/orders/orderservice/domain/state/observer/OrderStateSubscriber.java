package br.gasmartins.orders.orderservice.domain.state.observer;

import br.gasmartins.orders.orderservice.domain.Order;

import java.util.concurrent.Flow.Subscriber;

public interface OrderStateSubscriber extends Subscriber<Order> {

}
