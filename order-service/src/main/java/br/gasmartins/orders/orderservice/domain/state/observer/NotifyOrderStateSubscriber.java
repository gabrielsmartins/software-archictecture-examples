package br.gasmartins.orders.orderservice.domain.state.observer;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.processor.OrderProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow.Subscription;

@RequiredArgsConstructor
@Slf4j
public class NotifyOrderStateSubscriber implements OrderStateSubscriber {

    private final OrderProcessor processor;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Order order) {
        this.processor.process(order);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error notifying order", throwable);
    }

    @Override
    public void onComplete() {
      log.info("Order notified successfully");
    }
}
