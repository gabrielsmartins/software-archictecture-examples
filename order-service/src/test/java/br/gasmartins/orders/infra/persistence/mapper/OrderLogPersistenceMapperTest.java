package br.gasmartins.orders.infra.persistence.mapper;

import br.gasmartins.orders.domain.support.OrderSupport;
import br.gasmartins.orders.infra.persistence.support.OrderEntitySupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderLogPersistenceMapperTest {

    private OrderLogPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderLogPersistenceMapper();
    }

    @Test
    @DisplayName("Given Order Log When Map Then Return Order Log Entity")
    public void givenOrderLogWhenMapThenReturnOrderLogEntity(){
        var orderLog = OrderSupport.defaultOrderLog().build();
        var orderLogEntity = this.mapper.mapToEntity(orderLog);
        Assertions.assertThat(orderLogEntity).hasNoNullFieldsOrProperties();
        Assertions.assertThat(orderLogEntity.getId()).hasNoNullFieldsOrPropertiesExcept("order");
        Assertions.assertThat(orderLogEntity.getId().getDatetime()).isEqualTo(orderLog.getDatetime());
        Assertions.assertThat(orderLogEntity.getStatus().getSource()).isEqualTo(orderLog.getStatus());
    }

    @Test
    @DisplayName("Given Order Item Entity When Map Then Return Order Item")
    public void givenOrderItemEntityWhenMapThenReturnOrderItem(){
        var orderLogEntity = OrderEntitySupport.defaultOrderLogEntity().build();
        var orderLog = this.mapper.mapToDomain(orderLogEntity);
        Assertions.assertThat(orderLog).hasNoNullFieldsOrPropertiesExcept("order");
        Assertions.assertThat(orderLog.getDatetime()).isEqualTo(orderLogEntity.getId().getDatetime());
        Assertions.assertThat(orderLog.getStatus()).isEqualTo(orderLogEntity.getStatus().getSource());
    }
}
