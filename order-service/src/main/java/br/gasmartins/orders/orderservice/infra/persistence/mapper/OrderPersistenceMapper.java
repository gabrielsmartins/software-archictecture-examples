package br.gasmartins.orders.orderservice.infra.persistence.mapper;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.Order.OrderItem;
import br.gasmartins.orders.orderservice.domain.Order.OrderLog;
import br.gasmartins.orders.orderservice.domain.Order.OrderPaymentMethod;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderItemEntity;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderLogEntity;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity.OrderPaymentMethodEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderPersistenceMapper {

    private final OrderItemPersistenceMapper itemMapper;
    private final OrderLogPersistenceMapper logMapper;
    private final OrderPaymentMethodPersistenceMapper paymentMethodMapper;

    public Order mapToDomain(OrderEntity orderEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderEntity, Order>() {
            @Override
            protected void configure() {
                using((Converter<List<OrderItemEntity>, List<OrderItem>>) context -> context.getSource()
                                                                                            .stream()
                                                                                            .map(itemMapper::mapToDomain)
                                                                                            .collect(Collectors.toList()))
                                                                                            .map(this.source.getItems(), this.destination.getItems());

                using((Converter<List<OrderLogEntity>, List<OrderLog>>) context -> context.getSource()
                                                                                            .stream()
                                                                                            .map(logMapper::mapToDomain)
                                                                                            .collect(Collectors.toList()))
                                                                                            .map(this.source.getLogs(), this.destination.getLogs());

                using((Converter<List<OrderPaymentMethodEntity>, List<OrderPaymentMethod>>) context -> context.getSource()
                                                                                           .stream()
                                                                                           .map(paymentMethodMapper::mapToDomain)
                                                                                           .collect(Collectors.toList()))
                                                                                           .map(this.source.getLogs(), this.destination.getLogs());
            }
        });
        return mapper.map(orderEntity, Order.class);
    }

    public OrderEntity mapToEntity(Order order){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<Order, OrderEntity>() {
            @Override
            protected void configure() {
                using((Converter<List<OrderItem>, List<OrderItemEntity>>) context -> context.getSource()
                        .stream()
                        .map(itemMapper::mapToEntity)
                        .collect(Collectors.toList()))
                        .map(this.source.getItems(), this.destination.getItems());

                using((Converter<List<OrderLog>, List<OrderLogEntity>>) context -> context.getSource()
                        .stream()
                        .map(logMapper::mapToEntity)
                        .collect(Collectors.toList()))
                        .map(this.source.getLogs(), this.destination.getLogs());

                using((Converter<List<OrderPaymentMethod>, List<OrderPaymentMethodEntity>>) context -> context.getSource()
                                                                                                                .stream()
                                                                                                                .map(paymentMethodMapper::mapToEntity)
                                                                                                                .collect(Collectors.toList()))
                                                                                                                .map(this.source.getLogs(), this.destination.getLogs());
            }
        });
        return mapper.map(order, OrderEntity.class);
    }

}
