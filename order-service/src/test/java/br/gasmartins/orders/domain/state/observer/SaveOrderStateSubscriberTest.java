package br.gasmartins.orders.domain.state.observer;

import br.gasmartins.orders.domain.repository.OrderRepository;
import br.gasmartins.orders.domain.support.OrderSupport;
import br.gasmartins.orders.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;

import static org.mockito.Mockito.*;

public class SaveOrderStateSubscriberTest {

    private SaveOrderStateSubscriber subscriber;
    private OrderRepository repository;
    private Subscription subscription;

    @BeforeEach
    public void setup(){
        this.repository = mock(OrderRepository.class);
        this.subscriber = new SaveOrderStateSubscriber(this.repository);
        this.subscription = mock(Subscription.class);
        this.subscriber.onSubscribe(subscription);
    }

    @Test
    @DisplayName("Given Subscriber When On Next Then Save Order")
    public void givenSubscriberWhenOnNextThenSaveOrder(){
        var order = OrderSupport.defaultOrder().build();
        this.subscriber.onNext(order);
        verify(this.repository, times(1)).save(any(Order.class));
        verify(this.subscription, times(2)).request(1L);
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
