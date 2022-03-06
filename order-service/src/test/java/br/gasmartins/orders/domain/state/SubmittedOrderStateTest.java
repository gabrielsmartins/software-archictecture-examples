package br.gasmartins.orders.domain.state;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.domain.support.OrderSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubmittedOrderStateTest {

    private SubmittedOrderState state;
    private Order order;

    @BeforeEach
    public void setup(){
        this.order = OrderSupport.defaultOrder().build();
        this.state = new SubmittedOrderState(order);
    }

    @Test
    @DisplayName("Given Order When Advance State Then Return Validated State")
    public void givenOrderWhenAdvanceStateThenReturnValidatedState(){
        var state = this.state.next(order);
        assertThat(state).isInstanceOf(ValidatedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Reject Then Return Rejected State")
    public void givenOrderWhenRejectThenReturnRejectedState(){
        var state = this.state.reject(order);
        assertThat(state).isInstanceOf(RejectedOrderState.class);
    }

    @Test
    @DisplayName("Given Order State When Get Status Then Return Submitted Status")
    public void givenOrderStateWhenGetStatusThenReturnSubmittedStatus(){
        var order = OrderSupport.defaultOrder().withState(this.state).build();
        var state = order.getState();
        assertThat(state.getStatus()).isEqualTo(OrderStatus.SUBMITTED);
    }


}