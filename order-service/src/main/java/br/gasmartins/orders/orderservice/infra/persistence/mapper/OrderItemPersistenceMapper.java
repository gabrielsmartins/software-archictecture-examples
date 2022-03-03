package br.gasmartins.orders.orderservice.infra.persistence.mapper;


import br.gasmartins.orders.orderservice.domain.Order.OrderItem;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderItemEntity;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderItemEntity.OrderItemEntityId;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderItemPersistenceMapper {

    public OrderItem mapToDomain(OrderItemEntity orderItemEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderItemEntity, OrderItem>() {
            @Override
            protected void configure() {
                using(new Converter<OrderItemEntityId, UUID>() {
                    @Override
                    public UUID convert(MappingContext<OrderItemEntityId, UUID> context) {
                        return context.getSource().getProductId();
                    }
                }).map();
            }
        });
        return mapper.map(orderItemEntity, OrderItem.class);
    }

    public OrderItemEntity mapToEntity(OrderItem orderItem){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderItem, OrderItemEntity>() {
            @Override
            protected void configure() {
                using((Converter<OrderItem, UUID>) context -> context.getSource().getProductId())
                        .map(this.source.getProductId(), this.destination.getId().getProductId());
               this.destination.getId().setProductId(this.source.getProductId());
            }
        });
        return mapper.map(orderItem, OrderItemEntity.class);
    }

}
