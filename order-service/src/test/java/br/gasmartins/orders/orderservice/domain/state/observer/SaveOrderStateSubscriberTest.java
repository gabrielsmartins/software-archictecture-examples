package br.gasmartins.orders.orderservice.domain.state.observer;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Flow;

import static br.gasmartins.orders.orderservice.domain.support.OrderSupport.defaultOrder;
import static org.mockito.Mockito.*;

public class SaveOrderStateSubscriberTest {

    private SaveOrderStateSubscriber subscriber;
    private OrderRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = mock(OrderRepository.class);
        this.subscriber = new SaveOrderStateSubscriber(this.repository);
    }

    @Test
    @DisplayName("Given Subscriber When On Next Then Save Order")
    public void givenSubscriberWhenOnNextThenSaveOrder(){
        var order = defaultOrder().build();
        this.subscriber.onNext(order);
        verify(this.repository, times(1)).save(any(Order.class));
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