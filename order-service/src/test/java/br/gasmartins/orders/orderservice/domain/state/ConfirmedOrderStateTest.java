package br.gasmartins.orders.orderservice.domain.state;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.enums.OrderStatus;
import br.gasmartins.orders.orderservice.domain.exception.IllegalOrderStateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.orderservice.domain.support.OrderSupport.defaultOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfirmedOrderStateTest {

    private ConfirmedOrderState state;
    private Order order;

    @BeforeEach
    public void setup(){
        this.order = defaultOrder().build();
        this.state = new ConfirmedOrderState(order);
    }

    @Test
    @DisplayName("Given Order When Advance State Then Return Delivered State")
    public void givenOrderWhenAdvanceStateThenReturnDeliveredState(){
        var state = this.state.next(order);
        assertThat(state).isInstanceOf(DeliveredOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Reject Then Throw Exception")
    public void givenOrderWhenRejectThenThrowException(){
        var order = defaultOrder().build();
        assertThrows(IllegalOrderStateException.class, () -> this.state.reject(order));
    }

    @Test
    @DisplayName("Given Order State When Get Status Then Return Submitted Status")
    public void givenOrderStateWhenGetStatusThenReturnSubmittedStatus(){
        var order = defaultOrder().build();
        var state = order.getState();
        assertThat(state.getStatus()).isEqualTo(OrderStatus.CONFIRMED);
    }


}
