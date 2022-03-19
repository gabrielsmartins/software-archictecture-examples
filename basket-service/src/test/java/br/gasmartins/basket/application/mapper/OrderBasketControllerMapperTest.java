package br.gasmartins.basket.application.mapper;

import br.gasmartins.basket.application.web.mapper.OrderBasketControllerMapper;
import br.gasmartins.basket.application.web.mapper.OrderItemBasketControllerMapper;
import br.gasmartins.basket.application.web.mapper.OrderPaymentMethodBasketControllerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.basket.application.support.OrderDtoSupport.defaultOrderDto;
import static br.gasmartins.basket.domain.support.OrderSupport.defaultOrder;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderBasketControllerMapperTest {

    private OrderBasketControllerMapper mapper;

    @BeforeEach
    public void setup(){
        var itemMapper = new OrderItemBasketControllerMapper();
        var paymentMethodMapper = new OrderPaymentMethodBasketControllerMapper();
        this.mapper = new OrderBasketControllerMapper(itemMapper, paymentMethodMapper);
    }

    @Test
    @DisplayName("Given Order Dto When Map Then Return Order")
    public void givenOrderDtoWhenMapThenReturnOrder(){
        var orderDto = defaultOrderDto().build();
        var order = this.mapper.mapToDomain(orderDto);

        assertThat(order).hasNoNullFieldsOrProperties();
        assertThat(order.getId()).isEqualTo(orderDto.getId());
        assertThat(order.getCustomerId()).isEqualTo(orderDto.getCustomerId());
        assertThat(order.getCreatedAt()).isEqualTo(orderDto.getCreatedAt());
        assertThat(order.getTotalAmount()).isEqualTo(orderDto.getTotalAmount());
        assertThat(order.getTotalDiscount()).isEqualTo(orderDto.getTotalDiscount());
        assertThat(order.getItems().size()).isEqualTo(orderDto.getItems().size());
        assertThat(order.getPaymentMethods().size()).isEqualTo(orderDto.getPaymentMethods().size());
    }

    @Test
    @DisplayName("Given Order When Map Then Return Order Dto")
    public void givenOrderWhenMapThenReturnOrderDto(){
        var order = defaultOrder().build();
        var orderDto = this.mapper.mapToDto(order);

        assertThat(orderDto).hasNoNullFieldsOrProperties();
        assertThat(orderDto.getId()).isEqualTo(order.getId());
        assertThat(orderDto.getCustomerId()).isEqualTo(order.getCustomerId());
        assertThat(orderDto.getCreatedAt()).isEqualTo(order.getCreatedAt());
        assertThat(orderDto.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(orderDto.getTotalDiscount()).isEqualTo(order.getTotalDiscount());
        assertThat(orderDto.getItems().size()).isEqualTo(order.getItems().size());
        assertThat(orderDto.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
    }
}
