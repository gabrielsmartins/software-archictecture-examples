package br.gasmartins.orders.infra.persistence.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.infra.persistence.support.OrderEntitySupport.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderEntityTest {


    @Test
    @DisplayName("Given Order When Add Log Then Return Logs Size")
    public void givenOrderWhenAddLogThenReturnLogsSize(){

        var orderEntity = defaultOrderEntity().build();

        var orderLogEntity = defaultOrderLogEntity().build();
        var logSize = orderEntity.addLog(orderLogEntity);

        assertThat(logSize).isEqualTo(1);
    }

    @Test
    @DisplayName("Given Order When Add Item Then Return Items Size")
    public void givenOrderWhenAddItemThenReturnItemsSize(){

        var orderEntity = defaultOrderEntity().build();

        var itemEntity = defaultOrderItemEntity().build();
        var itemsSize = orderEntity.addItem(itemEntity);

        assertThat(itemsSize).isEqualTo(1);
    }


    @Test
    @DisplayName("Given Order When Add Payment Method Then Return Payment Methods Size")
    public void givenOrderWhenAddPaymentMethodThenReturnPaymentMethodsSize(){

        var orderEntity = defaultOrderEntity().build();

        var paymentMethodEntity = defaultOrderPaymentMethodEntity().build();
        var paymentMethodsSize = orderEntity.addPaymentMethod(paymentMethodEntity);

        assertThat(paymentMethodsSize).isEqualTo(1);
    }

}
