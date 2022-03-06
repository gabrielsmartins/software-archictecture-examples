package br.gasmartins.orders.orderservice.infra.persistence.mapper;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.state.OrderState;
import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity;
import br.gasmartins.orders.orderservice.infra.persistence.entity.enums.OrderStatusData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPersistenceMapper {

    private final OrderItemPersistenceMapper itemMapper;
    private final OrderLogPersistenceMapper logMapper;
    private final OrderPaymentMethodPersistenceMapper paymentMethodMapper;

    public Order mapToDomain(OrderEntity orderEntity){
        var mapper = new ModelMapper();
        var order = mapper.map(orderEntity, Order.class);
        orderEntity.getItems()
                .stream()
                .map(itemMapper::mapToDomain)
                .forEach(order::addItem);

        orderEntity.getLogs()
                .stream()
                .map(logMapper::mapToDomain)
                .forEach(order::addLog);

        orderEntity.getPaymentMethods()
                .stream()
                .map(paymentMethodMapper::mapToDomain)
                .forEach(order::addPaymentMethod);
        return order;
    }

    public OrderEntity mapToEntity(Order order){
        var mapper = new ModelMapper();

        mapper.addMappings(new PropertyMap<Order, OrderEntity>() {
            @Override
            protected void configure() {
                using((Converter<OrderState, OrderStatusData>) context -> OrderStatusData.fromSource(context.getSource().getStatus()))
                        .map(this.source.getState(), this.destination.getStatus());
            }
        });

        var orderEntity = mapper.map(order, OrderEntity.class);

        order.getItems()
             .stream()
             .map(itemMapper::mapToEntity)
             .forEach(orderEntity::addItem);

        order.getLogs()
             .stream()
             .map(logMapper::mapToEntity)
             .forEach(orderEntity::addLog);

        order.getPaymentMethods()
             .stream()
             .map(paymentMethodMapper::mapToEntity)
             .forEach(orderEntity::addPaymentMethod);

        return orderEntity;
    }

}
