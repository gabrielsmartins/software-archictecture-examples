package br.gasmartins.orders.orderservice.infra.persistence.mapper;


import br.gasmartins.orders.orderservice.domain.Order.OrderItem;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderItemEntity;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderItemEntity.OrderItemEntityId;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderItemPersistenceMapper {

    public OrderItem mapToDomain(OrderItemEntity orderItemEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderItemEntity, OrderItem>() {
            @Override
            protected void configure() {
                using((Converter<OrderItemEntityId, UUID>) context -> context.getSource().getProductId()).map(this.source.getId(), this.destination.getProductId());
            }
        });
        return mapper.map(orderItemEntity, OrderItem.class);
    }

    public OrderItemEntity mapToEntity(OrderItem orderItem){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderItem, OrderItemEntity>() {
            @Override
            protected void configure() {
                using((Converter<UUID, OrderItemEntityId>) context -> OrderItemEntityId.builder()
                                                                                       .withProductId(context.getSource())
                                                                                       .build())
                      .map(this.source.getProductId(), this.destination.getId());
            }
        });
        return mapper.map(orderItem, OrderItemEntity.class);
    }

}
