package br.gasmartins.basket.infra.messaging.mapper;

import br.gasmartins.basket.infra.messaging.mapper.OrderItemProducerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.basket.domain.support.OrderSupport.defaultOrderItem;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemProducerMapperTest {

    private OrderItemProducerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderItemProducerMapper();
    }

    @Test
    @DisplayName("Given Order Item When Map Then Return Order Item Message")
    public void givenOrderItemWhenMapThenReturnOrderItemMessage(){
        var orderItem = defaultOrderItem().build();
        var message = this.mapper.mapToMessage(orderItem);

        assertThat(message).hasNoNullFieldsOrProperties();
        assertThat(message.getProductId()).isEqualTo(orderItem.getProductId().toString());
        assertThat(message.getQuantity()).isEqualTo(orderItem.getQuantity());
        assertThat(message.getAmount()).isEqualTo(orderItem.getAmount());
    }
}
