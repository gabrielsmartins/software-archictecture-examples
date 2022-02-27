package br.gasmartins.orders.orderservice.domain.state;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.enums.OrderStatus;
import br.gasmartins.orders.orderservice.domain.state.observer.OrderStateObserverFactory;
import br.gasmartins.orders.orderservice.domain.state.observer.OrderStatePublisher;
import br.gasmartins.orders.orderservice.domain.state.observer.OrderStateSubscriber;
import lombok.Getter;

import java.util.concurrent.SubmissionPublisher;

@Getter
public abstract class OrderState implements OrderStatePublisher {

    protected Order order;
    private SubmissionPublisher<Order> publisher;

    public OrderState(Order order) {
        this.order = order;
        this.publisher = new SubmissionPublisher<>();
        var subscribers = OrderStateObserverFactory.getSubscribers();
        if (subscribers != null) {
            subscribers.forEach(this::addSubscriber);
            this.notifySubscribers();
        }
    }

    public OrderState(){
        super();
    }

    public abstract OrderStatus getStatus();

    public abstract OrderState next(Order order);

    public abstract OrderState reject(Order order);

    @Override
    public void addSubscriber(OrderStateSubscriber subscriber) {
        this.publisher.subscribe(subscriber);
    }

    @Override
    public void removeSubscriber(OrderStateSubscriber subscriber) {
        // TODO Auto-generated method stub
    }

    @Override
    public void notifySubscribers() {
        this.publisher.submit(order);
    }

    @Override
    public OrderState getState() {
        return this;
    }
}
