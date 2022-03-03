package br.gasmartins.orders.orderservice.infra.persistence.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gasmartins.orders.orderservice.domain.support.OrderSupport.defaultOrderLog;
import static br.gasmartins.orders.orderservice.infra.persistence.support.OrderEntitySupport.defaultOrderLogEntity;
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
        var orderLog = defaultOrderLog().build();
        var orderLogEntity = this.mapper.mapToEntity(orderLog);
        assertThat(orderLogEntity).hasNoNullFieldsOrProperties();
        assertThat(orderLogEntity.getId()).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderLogEntity.getId().getDatetime()).isEqualTo(orderLog.getDatetime());
        assertThat(orderLogEntity.getStatus().getSource()).isEqualTo(orderLog.getStatus());
    }

    @Test
    @DisplayName("Given Order Item Entity When Map Then Return Order Item")
    public void givenOrderItemEntityWhenMapThenReturnOrderItem(){
        var orderLogEntity = defaultOrderLogEntity().build();
        var orderLog = this.mapper.mapToDomain(orderLogEntity);
        assertThat(orderLog).hasNoNullFieldsOrProperties();
        assertThat(orderLog.getDatetime()).isEqualTo(orderLogEntity.getId().getDatetime());
        assertThat(orderLog.getStatus()).isEqualTo(orderLogEntity.getStatus().getSource());
    }
}
