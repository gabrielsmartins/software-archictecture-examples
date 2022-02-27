package br.gasmartins.orders.orderservice.domain.state.observer;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.processor.OrderProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Flow;

import static br.gasmartins.orders.orderservice.domain.support.OrderSupport.defaultOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NotifyOrderStateSubscriberTest {

    private NotifyOrderStateSubscriber subscriber;
    private OrderProcessor processor;

    @BeforeEach
    public void setup(){
        this.processor = mock(OrderProcessor.class);
        this.subscriber = new NotifyOrderStateSubscriber(this.processor);
    }

    @Test
    @DisplayName("Given Subscriber When On Next Then Process Order")
    public void givenSubscriberWhenOnNextThenProcessOrder(){
        var order = defaultOrder().build();
        this.subscriber.onNext(order);
        verify(this.processor, times(1)).process(any(Order.class));
    }


    @Test
    @DisplayName("Given Subscriber When On Error Then Log Error")
    public void givenSubscriberWhenOnErrorThenLogError(){
        this.subscriber.onError(new RuntimeException("Error"));
    }

    @Test
    @DisplayName("Given Subscriber When On Complete Then Log")
    public void givenSubscriberWhenOnCompleteThenLog(){
        this.subscriber.onComplete();
    }

    @Test
    @DisplayName("Given Subscriber When On Subscribe Then Request Subscription")
    public void givenSubscriberWhenOnSubscribeThenRequestSubscription(){
        var subscription = mock(Flow.Subscription.class);
        this.subscriber.onSubscribe(subscription);
        verify(subscription, times(1)).request(1L);
    }
}