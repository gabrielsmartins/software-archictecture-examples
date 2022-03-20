package br.gasmartins.orders.application.messaging.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.application.messaging.support.BasketMessageSupport.defaultOrderItemMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemBasketConsumerMapperTest {

    private OrderItemBasketConsumerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderItemBasketConsumerMapper();
    }

    @Test
    @DisplayName("Given Message When Map Then Return Order Item")
    public void givenMessageWhenMapThenReturnOrderItem(){
        var message = defaultOrderItemMessage().build();
        var orderItem = this.mapper.mapToDomain(message);

        assertThat(orderItem).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderItem.getProductId().toString()).isEqualTo(message.getProductId());
        assertThat(orderItem.getQuantity()).isEqualTo(message.getQuantity());
        assertThat(orderItem.getAmount()).isEqualTo(message.getAmount());
    }
}
