package br.gasmartins.orders.infra.messaging.mapper.rejected;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.domain.support.OrderSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderRejectedProducerMapperTest {

    private OrderRejectedProducerMapper mapper;

    @BeforeEach
    public void setup(){
        var itemMapper  = new OrderRejectedItemProducerMapper() ;
        var paymentMethodMapper = new OrderRejectedPaymentMethodProducerMapper() ;
        this.mapper = new OrderRejectedProducerMapper(itemMapper, paymentMethodMapper);
    }

    @Test
    @DisplayName("Given Order When Map Then Return Message")
    public void givenOrderWhenMapThenReturnMessage(){
        var order = defaultOrder().build();
        var orderItem = defaultOrderItem().build();
        var orderPaymentMethod = defaultOrderPaymentMethod().build();

        order.addItem(orderItem);
        order.addPaymentMethod(orderPaymentMethod);

        var message = this.mapper.mapToMessage(order);
        assertThat(message).hasNoNullFieldsOrProperties();
        assertThat(message.getId()).isEqualTo(order.getId());
        assertThat(message.getCustomerId()).isEqualTo(order.getCustomerId().toString());
        assertThat(message.getCreatedAt()).isEqualTo(order.getCreatedAt());
        assertThat(message.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(message.getTotalDiscount()).isEqualTo(order.getTotalDiscount());
        assertThat(message.getItems().size()).isEqualTo(order.getItems().size());
        assertThat(message.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
    }
}
