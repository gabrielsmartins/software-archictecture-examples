package br.gasmartins.orders.orderservice.domain.state.observer;

import br.gasmartins.orders.orderservice.domain.state.OrderState;

public interface OrderStatePublisher {

    void addSubscriber(OrderStateSubscriber subscriber);
    void removeSubscriber(OrderStateSubscriber subscriber);
    void notifySubscribers();
    OrderState getState();

}
