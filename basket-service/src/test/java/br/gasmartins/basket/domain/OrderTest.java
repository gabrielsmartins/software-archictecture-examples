package br.gasmartins.basket.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.basket.domain.support.OrderSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {


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
