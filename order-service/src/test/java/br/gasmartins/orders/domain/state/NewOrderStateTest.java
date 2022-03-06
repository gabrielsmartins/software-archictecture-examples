package br.gasmartins.orders.domain.state;

import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.domain.exception.IllegalOrderStateException;
import br.gasmartins.orders.domain.support.OrderSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NewOrderStateTest {

    private NewOrderState state;

    @BeforeEach
    public void setup(){
        this.state = new NewOrderState();
    }

    @Test
    @DisplayName("Given Order When Advance State Then Return Submitted State")
    public void givenOrderWhenAdvanceStateThenReturnSubmittedState(){
        var order = OrderSupport.defaultOrder().build();
        var state = this.state.next(order);
        assertThat(state).isInstanceOf(SubmittedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Reject Then Throw Exception")
    public void givenOrderWhenRejectThenThrowException(){
        var order = OrderSupport.defaultOrder().build();
        assertThrows(IllegalOrderStateException.class, () -> this.state.reject(order));
    }

    @Test
    @DisplayName("Given Order State When Get Status Then Return New Status")
    public void givenOrderStateWhenGetStatusThenReturnNewStatus(){
        var order = OrderSupport.defaultOrder().build();
        var state = order.getState();
        assertThat(state.getStatus()).isEqualTo(OrderStatus.NEW);
    }


}
