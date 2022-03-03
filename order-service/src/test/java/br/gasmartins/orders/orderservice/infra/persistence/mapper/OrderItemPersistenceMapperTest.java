package br.gasmartins.orders.orderservice.infra.persistence.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.orderservice.domain.support.OrderSupport.defaultOrderItem;
import static br.gasmartins.orders.orderservice.infra.persistence.support.OrderEntitySupport.defaultOrderItemEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemPersistenceMapperTest {

    private OrderItemPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderItemPersistenceMapper();
    }

    @Test
    @DisplayName("Given Order Item When Map Then Return Order Item Entity")
    public void givenOrderItemWhenMapThenReturnOrderItemEntity(){
        var orderItem = defaultOrderItem().build();
        var orderItemEntity = this.mapper.mapToEntity(orderItem);
        assertThat(orderItemEntity).hasNoNullFieldsOrProperties();
        assertThat(orderItemEntity.getId()).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderItemEntity.getId().getProductId()).isEqualTo(orderItem.getProductId());
        assertThat(orderItemEntity.getAmount()).isEqualTo(orderItem.getAmount());
        assertThat(orderItemEntity.getQuantity()).isEqualTo(orderItem.getQuantity());
    }

    @Test
    @DisplayName("Given Order Item Entity When Map Then Return Order Item")
    public void givenOrderItemEntityWhenMapThenReturnOrderItem(){
        var orderItemEntity = defaultOrderItemEntity().build();
        var orderItem = this.mapper.mapToDomain(orderItemEntity);
        assertThat(orderItem).hasNoNullFieldsOrProperties();
        assertThat(orderItem.getProductId()).isEqualTo(orderItemEntity.getId().getProductId());
        assertThat(orderItem.getAmount()).isEqualTo(orderItemEntity.getAmount());
        assertThat(orderItem.getQuantity()).isEqualTo(orderItemEntity.getQuantity());
    }
}
