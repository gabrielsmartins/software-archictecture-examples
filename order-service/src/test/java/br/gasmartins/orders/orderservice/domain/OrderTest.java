package br.gasmartins.orders.orderservice.domain;

import br.gasmartins.orders.orderservice.domain.state.RejectedOrderState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.orderservice.domain.support.OrderSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {

    @Test
    @DisplayName("Given Order When Advance State Then Return Next State")
    public void givenOrderWhenAdvanceStateThenReturnNextState(){

        var order = defaultOrder().build();

        var oldState = order.getState();
        var newState = order.nextState();

        assertThat(newState).isNotEqualTo(oldState);
    }


    @Test
    @DisplayName("Given Order When Reject Then Return Rejected State")
    public void givenOrderWhenRejectThenReturnRejectedState(){

        var order = defaultOrder().build();

        var state = order.reject();

        assertThat(state).isInstanceOf(RejectedOrderState.class);
    }

    @Test
    @DisplayName("Given Order When Add Log Then Return Logs Size")
    public void givenOrderWhenAddLogThenReturnLogsSize(){

        var order = defaultOrder().build();

        var log = defaultOrderLog().build();
        var logSize = order.addLog(log);

        assertThat(logSize).isEqualTo(1);
    }

    @Test
    @DisplayName("Given Order When Add Item Then Return Items Size")
    public void givenOrderWhenAddItemThenReturnItemsSize(){

        var order = defaultOrder().build();

        var item = defaultOrderItem().build();
        var itemsSize = order.addItem(item);

        assertThat(itemsSize).isEqualTo(1);
    }


    @Test
    @DisplayName("Given Order When Add Payment Method Then Return Payment Methods Size")
    public void givenOrderWhenAddPaymentMethodThenReturnPaymentMethodsSize(){

        var order = defaultOrder().build();

        var paymentMethod = defaultOrderPaymentMethod().build();
        var paymentMethodsSize = order.addPaymentMethod(paymentMethod);

        assertThat(paymentMethodsSize).isEqualTo(1);
    }

}
