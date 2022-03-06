package br.gasmartins.orders.domain.state.observer;

import br.gasmartins.orders.domain.Order;

import java.util.concurrent.Flow.Subscriber;

public interface OrderStateSubscriber extends Subscriber<Order> {

}
