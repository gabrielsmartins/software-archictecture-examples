package br.gasmartins.orders.infra.persistence.mapper;


import br.gasmartins.orders.infra.persistence.entity.OrderEntity;
import br.gasmartins.orders.domain.Order.OrderItem;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderItemPersistenceMapper {

    public OrderItem mapToDomain(OrderEntity.OrderItemEntity orderItemEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderEntity.OrderItemEntity, OrderItem>() {
            @Override
            protected void configure() {
                using((Converter<OrderEntity.OrderItemEntity.OrderItemEntityId, UUID>) context -> context.getSource().getProductId()).map(this.source.getId(), this.destination.getProductId());
            }
        });
        return mapper.map(orderItemEntity, OrderItem.class);
    }

    public OrderEntity.OrderItemEntity mapToEntity(OrderItem orderItem){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderItem, OrderEntity.OrderItemEntity>() {
            @Override
            protected void configure() {
                using((Converter<UUID, OrderEntity.OrderItemEntity.OrderItemEntityId>) context -> OrderEntity.OrderItemEntity.OrderItemEntityId.builder()
                                                                                       .withProductId(context.getSource())
                                                                                       .build())
                      .map(this.source.getProductId(), this.destination.getId());
            }
        });
        return mapper.map(orderItem, OrderEntity.OrderItemEntity.class);
    }

}
