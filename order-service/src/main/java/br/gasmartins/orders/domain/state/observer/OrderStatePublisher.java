package br.gasmartins.orders.domain.state.observer;

import br.gasmartins.orders.domain.state.OrderState;

public interface OrderStatePublisher {

    void addSubscriber(OrderStateSubscriber subscriber);
    void removeSubscriber(OrderStateSubscriber subscriber);
    void notifySubscribers();
    OrderState getState();

}
