package br.gasmartins.basket.infra.messaging.mapper;

import br.gasmartins.basket.domain.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.basket.domain.support.OrderSupport.defaultOrderPaymentMethod;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderPaymentMethodProducerMapperTest {

    private OrderPaymentMethodProducerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderPaymentMethodProducerMapper();
    }

    @Test
    @DisplayName("Given Order Payment Method When Map Then Return Order Payment Method Message")
    public void givenOrderPaymentMethodWhenMapThenReturnOrderPaymentMethodMessage(){
        var orderPaymentMethod = defaultOrderPaymentMethod().build();
        var message = this.mapper.mapToMessage(orderPaymentMethod);

        assertThat(message).hasNoNullFieldsOrProperties();
        assertThat(message.getAmount()).isEqualTo(orderPaymentMethod.getAmount());
        assertThat(PaymentType.fromDescription(message.getPaymentType().name())).isEqualTo(orderPaymentMethod.getPaymentType());
    }
}
