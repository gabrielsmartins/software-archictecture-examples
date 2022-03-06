package br.gasmartins.orders.orderservice.infra.persistence.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.orderservice.domain.support.OrderSupport.defaultOrderPaymentMethod;
import static br.gasmartins.orders.orderservice.infra.persistence.support.OrderEntitySupport.defaultOrderPaymentMethodEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderPaymentMethodPersistenceMapperTest {

    private OrderPaymentMethodPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderPaymentMethodPersistenceMapper();
    }

    @Test
    @DisplayName("Given Order Payment Method When Map Then Return Order Payment Method Entity")
    public void givenOrderPaymentMethodWhenMapThenReturnOrderPaymentMethodEntity(){
        var orderPaymentMethod = defaultOrderPaymentMethod().build();
        var orderPaymentMethodEntity = this.mapper.mapToEntity(orderPaymentMethod);
        assertThat(orderPaymentMethodEntity).hasNoNullFieldsOrProperties();
        assertThat(orderPaymentMethodEntity.getId()).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderPaymentMethodEntity.getId().getPaymentType().getSource()).isEqualTo(orderPaymentMethod.getPaymentType());
        assertThat(orderPaymentMethodEntity.getAmount()).isEqualTo(orderPaymentMethod.getAmount());
    }

    @Test
    @DisplayName("Given Order Payment Method Entity When Map Then Return Order Payment Method")
    public void givenOrderPaymentMethodEntityWhenMapThenReturnOrderPaymentMethod(){
        var orderPaymentMethodEntity = defaultOrderPaymentMethodEntity().build();
        var orderPaymentMethod = this.mapper.mapToDomain(orderPaymentMethodEntity);
        assertThat(orderPaymentMethod).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderPaymentMethod.getPaymentType()).isEqualTo(orderPaymentMethodEntity.getId().getPaymentType().getSource());
        assertThat(orderPaymentMethod.getAmount()).isEqualTo(orderPaymentMethodEntity.getAmount());
    }
}
