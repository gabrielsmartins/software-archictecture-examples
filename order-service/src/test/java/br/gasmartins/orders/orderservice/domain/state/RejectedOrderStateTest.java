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

public class RejectedOrderStateTest {

    private RejectedOrderState state;
    private Order order;

    @BeforeEach
    public void setup(){
        this.order = defaultOrder().build();
        this.state = new RejectedOrderState(order);
    }

    @Test
    @DisplayName("Given Order When Advance State Then Throw Exception")
    public void givenOrderWhenAdvanceStateThenThrowException(){
        assertThrows(IllegalOrderStateException.class, () -> this.state.next(order));
    }

    @Test
    @DisplayName("Given Order When Reject Then Throw Exception")
    public void givenOrderWhenRejectThenThrowException(){
        var order = defaultOrder().build();
        assertThrows(IllegalOrderStateException.class, () -> this.state.reject(order));
    }

    @Test
    @DisplayName("Given Order State When Get Status Then Return Rejected Status")
    public void givenOrderStateWhenGetStatusThenReturnRejectedStatus(){
        var order = defaultOrder().withState(this.state).build();
        var state = order.getState();
        assertThat(state.getStatus()).isEqualTo(OrderStatus.REJECTED);
    }


}
