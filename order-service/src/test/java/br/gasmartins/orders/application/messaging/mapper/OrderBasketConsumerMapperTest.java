package br.gasmartins.orders.application.messaging.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static br.gasmartins.orders.application.messaging.support.BasketMessageSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderBasketConsumerMapperTest {

    private OrderBasketConsumerMapper mapper;

    @BeforeEach
    public void setup(){
        var itemMapper = new OrderItemBasketConsumerMapper();
        var paymentMethodMapper = new OrderPaymentMethodBasketConsumerMapper();
        this.mapper = new OrderBasketConsumerMapper(itemMapper, paymentMethodMapper);
    }

    @Test
    @DisplayName("Given Message When Map Then Return Order")
    public void givenMessageWhenMapThenReturnOrder(){
        var orderItemMessage = defaultOrderItemMessage().build();
        var orderPaymentMethodMessage = defaultOrderPaymentMethodMessage().build();
        var message = defaultOrderMessage()
                .setItems(List.of(orderItemMessage))
                .setPaymentMethods(List.of(orderPaymentMethodMessage))
                .build();

        var order = this.mapper.mapToDomain(message);
        assertThat(order).hasNoNullFieldsOrPropertiesExcept("id", "finishedAt");
        assertThat(order.getCustomerId().toString()).isEqualTo(message.getCustomerId());
        assertThat(order.getCreatedAt()).isEqualTo(message.getCreatedAt());
        assertThat(order.getTotalAmount()).isEqualTo(message.getTotalAmount());
        assertThat(order.getTotalDiscount()).isEqualTo(message.getTotalDiscount());
        assertThat(order.getItems().size()).isEqualTo(message.getItems().size());
        assertThat(order.getPaymentMethods().size()).isEqualTo(message.getPaymentMethods().size());
    }


}
