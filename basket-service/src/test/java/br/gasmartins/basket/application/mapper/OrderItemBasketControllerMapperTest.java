package br.gasmartins.basket.application.mapper;

import br.gasmartins.basket.application.web.mapper.OrderItemBasketControllerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.basket.application.support.OrderDtoSupport.defaultOrderItemDto;
import static br.gasmartins.basket.domain.support.OrderSupport.defaultOrderItem;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemBasketControllerMapperTest {

    private OrderItemBasketControllerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderItemBasketControllerMapper();
    }

    @Test
    @DisplayName("Given Order Item When Map Then Return Order Item Dto")
    public void givenOrderItemWhenMapThenReturnOrderItemDto(){

        var orderItem = defaultOrderItem().build();

        var orderItemDto = this.mapper.mapToDto(orderItem);

        assertThat(orderItemDto).hasNoNullFieldsOrProperties();
        assertThat(orderItemDto.getProductId()).isEqualTo(orderItem.getProductId());
        assertThat(orderItemDto.getQuantity()).isEqualTo(orderItem.getQuantity());
        assertThat(orderItemDto.getAmount()).isEqualTo(orderItem.getAmount());
    }

    @Test
    @DisplayName("Given Order Item Dto When Map Then Return Order Item")
    public void givenOrderItemDtoWhenMapThenReturnOrderItem(){

        var orderItemDto = defaultOrderItemDto().build();

        var orderItem = this.mapper.mapToDomain(orderItemDto);

        assertThat(orderItem).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderItem.getProductId()).isEqualTo(orderItemDto.getProductId());
        assertThat(orderItem.getQuantity()).isEqualTo(orderItemDto.getQuantity());
        assertThat(orderItem.getAmount()).isEqualTo(orderItemDto.getAmount());
    }

}
