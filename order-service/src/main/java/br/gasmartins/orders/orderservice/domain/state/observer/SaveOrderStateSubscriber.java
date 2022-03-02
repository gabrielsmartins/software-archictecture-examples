package br.gasmartins.orders.orderservice.domain.state.observer;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow.Subscription;

@RequiredArgsConstructor
@Slf4j
public class SaveOrderStateSubscriber implements OrderStateSubscriber {

    private final OrderRepository repository;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
       this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Order order) {
        this.repository.save(order);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error saving order", throwable);
    }

    @Override
    public void onComplete() {
        log.info("Order saved successfully");
    }
}
