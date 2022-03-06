package br.gasmartins.orders.infra.persistence.mapper;

import br.gasmartins.orders.domain.support.OrderSupport;
import br.gasmartins.orders.infra.persistence.support.OrderEntitySupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        var orderPaymentMethod = OrderSupport.defaultOrderPaymentMethod().build();
        var orderPaymentMethodEntity = this.mapper.mapToEntity(orderPaymentMethod);
        assertThat(orderPaymentMethodEntity).hasNoNullFieldsOrProperties();
        assertThat(orderPaymentMethodEntity.getId()).hasNoNullFieldsOrPropertiesExcept("order");
        Assertions.assertThat(orderPaymentMethodEntity.getId().getPaymentType().getSource()).isEqualTo(orderPaymentMethod.getPaymentType());
        assertThat(orderPaymentMethodEntity.getAmount()).isEqualTo(orderPaymentMethod.getAmount());
    }

    @Test
    @DisplayName("Given Order Payment Method Entity When Map Then Return Order Payment Method")
    public void givenOrderPaymentMethodEntityWhenMapThenReturnOrderPaymentMethod(){
        var orderPaymentMethodEntity = OrderEntitySupport.defaultOrderPaymentMethodEntity().build();
        var orderPaymentMethod = this.mapper.mapToDomain(orderPaymentMethodEntity);
        Assertions.assertThat(orderPaymentMethod).hasNoNullFieldsOrPropertiesExcept("order");
        Assertions.assertThat(orderPaymentMethod.getPaymentType()).isEqualTo(orderPaymentMethodEntity.getId().getPaymentType().getSource());
        Assertions.assertThat(orderPaymentMethod.getAmount()).isEqualTo(orderPaymentMethodEntity.getAmount());
    }
}
