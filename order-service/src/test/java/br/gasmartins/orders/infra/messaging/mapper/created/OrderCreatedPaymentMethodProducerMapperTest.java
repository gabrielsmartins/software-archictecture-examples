package br.gasmartins.orders.infra.messaging.mapper.created;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.domain.support.OrderSupport.defaultOrderPaymentMethod;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderCreatedPaymentMethodProducerMapperTest {

    private OrderCreatedPaymentMethodProducerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderCreatedPaymentMethodProducerMapper();
    }

    @Test
    @DisplayName("Given Order Payment Method When Map Then Return Message")
    public void givenOrderPaymentMethodWhenMapThenReturnMessage(){
        var orderPaymentMethod = defaultOrderPaymentMethod().build();
        var message = this.mapper.mapToMessage(orderPaymentMethod);
        assertThat(message).hasNoNullFieldsOrProperties();
        assertThat(message.getPaymentType().toString()).isEqualTo(orderPaymentMethod.getPaymentType().getDescription());
        assertThat(message.getAmount()).isEqualTo(orderPaymentMethod.getAmount());
    }
}
