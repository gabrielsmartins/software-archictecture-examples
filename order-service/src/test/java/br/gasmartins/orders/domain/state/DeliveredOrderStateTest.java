package br.gasmartins.orders.domain.state;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.domain.exception.IllegalOrderStateException;
import br.gasmartins.orders.domain.support.OrderSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeliveredOrderStateTest {

    private DeliveredOrderState state;
    private Order order;

    @BeforeEach
    public void setup(){
        this.order = OrderSupport.defaultOrder().build();
        this.state = new DeliveredOrderState(order);
    }

    @Test
    @DisplayName("Given Order When Advance State Then Return Finished State")
    public void givenOrderWhenAdvanceStateThenReturnFinishedState(){
        var state = this.state.next(order);
        assertThat(state).isInstanceOf(FinishedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Reject Then Throw Exception")
    public void givenOrderWhenRejectThenThrowException(){
        var order = OrderSupport.defaultOrder().build();
        assertThrows(IllegalOrderStateException.class, () -> this.state.reject(order));
    }

    @Test
    @DisplayName("Given Order State When Get Status Then Return Delivered Status")
    public void givenOrderStateWhenGetStatusThenReturnDeliveredStatus(){
        var order = OrderSupport.defaultOrder().withState(this.state).build();
        var state = order.getState();
        assertThat(state.getStatus()).isEqualTo(OrderStatus.DELIVERED);
    }


}
