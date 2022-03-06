package br.gasmartins.orders.orderservice.infra.persistence.mapper;

import br.gasmartins.orders.orderservice.domain.Order.OrderLog;
import br.gasmartins.orders.orderservice.domain.enums.OrderStatus;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderLogEntity;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderLogEntity.OrderLogEntityId;
import br.gasmartins.orders.orderservice.infra.persistence.entity.enums.OrderStatusData;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderLogPersistenceMapper {

    public OrderLog mapToDomain(OrderLogEntity orderLogEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderLogEntity, OrderLog>() {
            @Override
            protected void configure() {
                using((Converter<OrderLogEntityId, LocalDateTime>) context -> context.getSource().getDatetime())
                      .map(this.source.getId(), this.destination.getDatetime());
                using((Converter<OrderLogEntity, OrderStatus>) context -> context.getSource().getStatus().getSource())
                      .map(this.source, this.destination.getStatus());
            }
        });
        return mapper.map(orderLogEntity, OrderLog.class);
    }

    public OrderLogEntity mapToEntity(OrderLog orderLog){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderLog, OrderLogEntity>() {
            @Override
            protected void configure() {
                using((Converter<LocalDateTime, OrderLogEntityId>) context -> OrderLogEntityId.builder()
                        .withDatetime(context.getSource())
                        .build())
                      .map(this.source.getDatetime(), this.destination.getId());
                using((Converter<OrderStatus, OrderStatusData>) context -> OrderStatusData.fromSource(context.getSource()))
                      .map(this.source.getStatus(), this.destination.getStatus());
            }
        });
        return mapper.map(orderLog, OrderLogEntity.class);
    }

}
