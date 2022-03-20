package br.gasmartins.orders.infra.messaging.mapper.finished;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.domain.support.OrderSupport.defaultOrderItem;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderFinishedItemProducerMapperTest {

    private OrderFinishedItemProducerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderFinishedItemProducerMapper();
    }

    @Test
    @DisplayName("Given Order Item When Map Then Return Message")
    public void givenOrderItemWhenMapThenReturnMessage(){
       var orderItem = defaultOrderItem().build();
        var message = this.mapper.mapToMessage(orderItem);
        assertThat(message).hasNoNullFieldsOrProperties();
        assertThat(message.getProductId()).isEqualTo(orderItem.getProductId().toString());
        assertThat(message.getQuantity()).isEqualTo(orderItem.getQuantity());
        assertThat(message.getAmount()).isEqualTo(orderItem.getAmount());
    }
}
