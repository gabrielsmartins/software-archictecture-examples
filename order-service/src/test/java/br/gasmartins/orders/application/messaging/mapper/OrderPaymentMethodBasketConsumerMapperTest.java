package br.gasmartins.orders.application.messaging.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.application.messaging.support.BasketMessageSupport.defaultOrderPaymentMethodMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderPaymentMethodBasketConsumerMapperTest {

    private OrderPaymentMethodBasketConsumerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderPaymentMethodBasketConsumerMapper();
    }

    @Test
    @DisplayName("Given Message When Map Then Return Order Payment Method")
    public void givenMessageWhenMapThenReturnOrderPaymentMethod(){
        var message = defaultOrderPaymentMethodMessage().build();
        var orderPaymentMethod = this.mapper.mapToDomain(message);

        assertThat(orderPaymentMethod).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderPaymentMethod.getPaymentType().name()).isEqualTo(message.getPaymentType().name());
        assertThat(orderPaymentMethod.getAmount()).isEqualTo(message.getAmount());
    }
}
