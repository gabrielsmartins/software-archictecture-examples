package br.gasmartins.orders.infra.persistence.mapper;

import br.gasmartins.orders.domain.support.OrderSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.infra.persistence.support.OrderEntitySupport.defaultOrderEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderPersistenceMapperTest {

    private OrderPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        var itemMapper = new OrderItemPersistenceMapper();
        var logMapper = new OrderLogPersistenceMapper();
        var paymentMethodMapper = new OrderPaymentMethodPersistenceMapper();
        this.mapper = new OrderPersistenceMapper(itemMapper, logMapper, paymentMethodMapper);
    }

    @Test
    @DisplayName("Given Order When Map Then Return Order Entity")
    public void givenOrderWhenMapThenReturnOrderEntity(){
        var order = OrderSupport.defaultOrder().build();
        var orderEntity = this.mapper.mapToEntity(order);
        assertThat(orderEntity).hasNoNullFieldsOrProperties();
        assertThat(orderEntity.getId()).isEqualTo(order.getId());
        assertThat(orderEntity.getCustomerId()).isEqualTo(order.getCustomerId());
        assertThat(orderEntity.getCreatedAt()).isEqualTo(order.getCreatedAt());
        assertThat(orderEntity.getFinishedAt()).isEqualTo(order.getFinishedAt());
        assertThat(orderEntity.getTotalAmount()).isEqualTo(order.getTotalAmount());
        assertThat(orderEntity.getTotalDiscount()).isEqualTo(order.getTotalDiscount());
        assertThat(orderEntity.getLogs().size()).isEqualTo(order.getLogs().size());
        assertThat(orderEntity.getItems().size()).isEqualTo(order.getItems().size());
        assertThat(orderEntity.getPaymentMethods().size()).isEqualTo(order.getPaymentMethods().size());
    }

    @Test
    @DisplayName("Given Order Entity When Map Then Return Order")
    public void givenOrderEntityWhenMapThenReturnOrder(){
        var orderEntity = defaultOrderEntity().build();
        var order = this.mapper.mapToDomain(orderEntity);
        assertThat(order).hasNoNullFieldsOrProperties();
        assertThat(order.getId()).isEqualTo(orderEntity.getId());
        assertThat(order.getCustomerId()).isEqualTo(orderEntity.getCustomerId());
        assertThat(order.getCreatedAt()).isEqualTo(orderEntity.getCreatedAt());
        assertThat(order.getFinishedAt()).isEqualTo(orderEntity.getFinishedAt());
        assertThat(order.getTotalAmount()).isEqualTo(orderEntity.getTotalAmount());
        assertThat(order.getTotalDiscount()).isEqualTo(orderEntity.getTotalDiscount());
        assertThat(order.getLogs().size()).isEqualTo(orderEntity.getLogs().size());
        assertThat(order.getItems().size()).isEqualTo(orderEntity.getItems().size());
        assertThat(order.getPaymentMethods().size()).isEqualTo(orderEntity.getPaymentMethods().size());
    }


}
