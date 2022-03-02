package br.gasmartins.orders.orderservice.application.service;

import br.gasmartins.orders.orderservice.application.OrderService;
import br.gasmartins.orders.orderservice.domain.repository.OrderRepository;
import br.gasmartins.orders.orderservice.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static br.gasmartins.orders.orderservice.domain.support.OrderSupport.defaultOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    private OrderService service;
    private OrderRepository repository;

    @BeforeEach
    public void setUp() {
        this.repository = mock(OrderRepository.class);
        this.service = new OrderServiceImpl(this.repository);
    }

    @Test
    @DisplayName("Given Id When Exists Then Return Order")
    public void givenIdWhenExistsThenReturnOrder(){
        var order = defaultOrder().build();
        when(this.repository.findById(anyLong())).thenReturn(Optional.of(order));

        var optionalOrder = this.service.findById(order.getId());
        assertThat(optionalOrder).isPresent();
    }

    @Test
    @DisplayName("Given Order When Create Then Return Submitted Order")
    public void givenOrderWhenCreateThenReturnSubmittedOrder(){
        var order = defaultOrder().build();
        var submittedOrder = this.service.submit(order);
        assertThat(submittedOrder.getState()).isInstanceOf(SubmittedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Validate Then Return Validated Order")
    public void givenOrderWhenValidateThenReturnValidatedOrder(){
        var order = defaultOrder().build();
        var submittedOrder = this.service.submit(order);
        var validatedOrder = this.service.validate(submittedOrder);
        assertThat(validatedOrder.getState()).isInstanceOf(ValidatedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Confirm Then Return Confirmed Order")
    public void givenOrderWhenConfirmThenReturnConfirmedOrder(){
        var order = defaultOrder().build();
        var submittedOrder = this.service.submit(order);
        var validatedOrder = this.service.validate(submittedOrder);
        var confirmedOrder = this.service.confirm(validatedOrder);
        assertThat(confirmedOrder.getState()).isInstanceOf(ConfirmedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Deliver Then Return Delivered Order")
    public void givenOrderWhenDeliverThenReturnDeliveredOrder(){
        var order = defaultOrder().build();
        var submittedOrder = this.service.submit(order);
        var validatedOrder = this.service.validate(submittedOrder);
        var confirmedOrder = this.service.confirm(validatedOrder);
        var deliveredOrder = this.service.deliver(confirmedOrder);
        assertThat(deliveredOrder.getState()).isInstanceOf(DeliveredOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Finish Then Return Finished Order")
    public void givenOrderWhenFinishThenReturnFinishedOrder(){
        var order = defaultOrder().build();
        var submittedOrder = this.service.submit(order);
        var validatedOrder = this.service.validate(submittedOrder);
        var confirmedOrder = this.service.confirm(validatedOrder);
        var deliveredOrder = this.service.deliver(confirmedOrder);
        var finishedOrder = this.service.finish(deliveredOrder);
        assertThat(finishedOrder.getState()).isInstanceOf(FinishedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Reject Then Return Rejected Order")
    public void givenOrderWhenRejectThenReturnRejectedOrder(){
        var order = defaultOrder().build();
        var submittedOrder = this.service.submit(order);
        var validatedOrder = this.service.validate(submittedOrder);
        var rejectedOrder = this.service.reject(validatedOrder);;
        assertThat(rejectedOrder.getState()).isInstanceOf(RejectedOrderState.class);
    }
}