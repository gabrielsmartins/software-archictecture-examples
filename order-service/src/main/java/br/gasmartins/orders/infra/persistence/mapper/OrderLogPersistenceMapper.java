package br.gasmartins.orders.infra.persistence.mapper;

import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.infra.persistence.entity.OrderEntity;
import br.gasmartins.orders.infra.persistence.entity.enums.OrderStatusData;
import br.gasmartins.orders.domain.Order.OrderLog;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderLogPersistenceMapper {

    public OrderLog mapToDomain(OrderEntity.OrderLogEntity orderLogEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderEntity.OrderLogEntity, OrderLog>() {
            @Override
            protected void configure() {
                using((Converter<OrderEntity.OrderLogEntity.OrderLogEntityId, LocalDateTime>) context -> context.getSource().getDatetime())
                      .map(this.source.getId(), this.destination.getDatetime());
                using((Converter<OrderEntity.OrderLogEntity, OrderStatus>) context -> context.getSource().getStatus().getSource())
                      .map(this.source, this.destination.getStatus());
            }
        });
        return mapper.map(orderLogEntity, OrderLog.class);
    }

    public OrderEntity.OrderLogEntity mapToEntity(OrderLog orderLog){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderLog, OrderEntity.OrderLogEntity>() {
            @Override
            protected void configure() {
                using((Converter<LocalDateTime, OrderEntity.OrderLogEntity.OrderLogEntityId>) context -> OrderEntity.OrderLogEntity.OrderLogEntityId.builder()
                        .withDatetime(context.getSource())
                        .build())
                      .map(this.source.getDatetime(), this.destination.getId());
                using((Converter<OrderStatus, OrderStatusData>) context -> OrderStatusData.fromSource(context.getSource()))
                      .map(this.source.getStatus(), this.destination.getStatus());
            }
        });
        return mapper.map(orderLog, OrderEntity.OrderLogEntity.class);
    }

}
