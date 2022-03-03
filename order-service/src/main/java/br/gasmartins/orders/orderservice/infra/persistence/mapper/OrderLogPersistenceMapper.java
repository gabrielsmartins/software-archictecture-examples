package br.gasmartins.orders.orderservice.infra.persistence.mapper;

import br.gasmartins.orders.orderservice.domain.Order.OrderLog;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderLogEntity;
import br.gasmartins.orders.orderservice.infra.persistence.entity.enums.OrderStatusData;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class OrderLogPersistenceMapper {

    public OrderLog mapToDomain(OrderLogEntity orderLogEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderLogEntity, OrderLog>() {
            @Override
            protected void configure() {
                this.destination.setDatetime(this.source.getId().getDatetime());
                this.destination.setStatus(this.source.getStatus().getSource());
            }
        });
        return mapper.map(orderLogEntity, OrderLog.class);
    }

    public OrderLogEntity mapToEntity(OrderLog orderLog){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderLog, OrderLogEntity>() {
            @Override
            protected void configure() {
                this.destination.getId().setDatetime(this.source.getDatetime());
                this.destination.setStatus(OrderStatusData.fromSource(this.source.getStatus()));
            }
        });
        return mapper.map(orderLog, OrderLogEntity.class);
    }

}
