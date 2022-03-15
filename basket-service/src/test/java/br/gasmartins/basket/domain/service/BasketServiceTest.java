package br.gasmartins.basket.domain.service;

import br.gasmartins.basket.domain.Order;
import br.gasmartins.basket.domain.dispatcher.OrderDispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.gasmartins.basket.domain.support.OrderSupport.defaultOrder;
import static org.mockito.Mockito.*;

public class BasketServiceTest {

    private BasketService service;
    private OrderDispatcher dispatcher;

    @BeforeEach
    public void setup(){
        this.dispatcher = mock(OrderDispatcher.class);
        this.service = new BasketService(this.dispatcher);
    }

    @Test
    @DisplayName("Given Order When Create Then Return Created Order")
    public void givenOrderWhenCreateThenReturnCreatedOrder(){
        var order = defaultOrder().build();

        when(this.dispatcher.dispatch(any(Order.class))).thenAnswer(Mono::just);

        this.service.create(order)
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();

        verify(this.dispatcher, times(1)).dispatch(any(Order.class));
    }


}
