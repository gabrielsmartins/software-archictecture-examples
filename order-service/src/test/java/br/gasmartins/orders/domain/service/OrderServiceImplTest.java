package br.gasmartins.orders.domain.service;

import br.gasmartins.orders.application.OrderService;
import br.gasmartins.orders.domain.repository.OrderRepository;
import br.gasmartins.orders.domain.state.*;
import br.gasmartins.orders.domain.support.OrderSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
        var order = OrderSupport.defaultOrder().build();
        when(this.repository.findById(anyLong())).thenReturn(Optional.of(order));

        var optionalOrder = this.service.findById(order.getId());
        Assertions.assertThat(optionalOrder).isPresent();
    }

    @Test
    @DisplayName("Given Order When Create Then Return Created Order")
    public void givenOrderWhenCreateThenReturnCreatedOrder(){
        var order = OrderSupport.defaultOrder().build();
        var createdOrder = this.service.create(order);
        assertThat(createdOrder.getState()).isInstanceOf(CreatedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Validate Then Return Validated Order")
    public void givenOrderWhenValidateThenReturnValidatedOrder(){
        var order = OrderSupport.defaultOrder().build();
        var createdOrder = this.service.create(order);
        var validatedOrder = this.service.validate(createdOrder);
        assertThat(validatedOrder.getState()).isInstanceOf(ValidatedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Confirm Then Return Confirmed Order")
    public void givenOrderWhenConfirmThenReturnConfirmedOrder(){
        var order = OrderSupport.defaultOrder().build();
        var createdOrder = this.service.create(order);
        var validatedOrder = this.service.validate(createdOrder);
        var confirmedOrder = this.service.confirm(validatedOrder);
        assertThat(confirmedOrder.getState()).isInstanceOf(ConfirmedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Deliver Then Return Delivered Order")
    public void givenOrderWhenDeliverThenReturnDeliveredOrder(){
        var order = OrderSupport.defaultOrder().build();
        var createdOrder = this.service.create(order);
        var validatedOrder = this.service.validate(createdOrder);
        var confirmedOrder = this.service.confirm(validatedOrder);
        var deliveredOrder = this.service.deliver(confirmedOrder);
        assertThat(deliveredOrder.getState()).isInstanceOf(DeliveredOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Finish Then Return Finished Order")
    public void givenOrderWhenFinishThenReturnFinishedOrder(){
        var order = OrderSupport.defaultOrder().build();
        var createdOrder = this.service.create(order);
        var validatedOrder = this.service.validate(createdOrder);
        var confirmedOrder = this.service.confirm(validatedOrder);
        var deliveredOrder = this.service.deliver(confirmedOrder);
        var finishedOrder = this.service.finish(deliveredOrder);
        assertThat(finishedOrder.getState()).isInstanceOf(FinishedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Reject Then Return Rejected Order")
    public void givenOrderWhenRejectThenReturnRejectedOrder(){
        var order = OrderSupport.defaultOrder().build();
        var createdOrder = this.service.create(order);
        var validatedOrder = this.service.validate(createdOrder);
        var rejectedOrder = this.service.reject(validatedOrder);
        assertThat(rejectedOrder.getState()).isInstanceOf(RejectedOrderState.class);
    }
}