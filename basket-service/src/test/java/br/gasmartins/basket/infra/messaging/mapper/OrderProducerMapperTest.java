package br.gasmartins.basket.infra.messaging.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.basket.domain.support.OrderSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderProducerMapperTest {

    private OrderProducerMapper mapper;

    @BeforeEach
    public void setup(){
        var itemMapper = new OrderItemProducerMapper();
        var paymentMethodMapper = new OrderPaymentMethodProducerMapper();
        this.mapper = new OrderProducerMapper(itemMapper, paymentMethodMapper);
    }

    @Test
    @DisplayName("Given Order When Map Then Return Order Message")
    public void givenOrderWhenMapThenReturnOrderMessage(){
        var orderItem = defaultOrderItem().build();
        var orderPaymentMethod = defaultOrderPaymentMethod().build();

        var order = defaultOrder().build();
        order.addItem(orderItem);
        order.addPaymentMethod(orderPaymentMethod);

        var message = this.mapper.mapToMessage(order);

        assertThat(message).hasNoNullFieldsOrProperties();
        assertThat(message.getCustomerId()).isEqualTo(order.getCustomerId().toString());
        assertThat(message.getCreatedAt()).isEqualTo(order.getCreatedAt());
        assertThat(message.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(message.getTotalDiscount()).isEqualTo(order.getTotalDiscount());
        assertThat(message.getItems().size()).isEqualTo(order.getItems().size());
        assertThat(message.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
    }
}
