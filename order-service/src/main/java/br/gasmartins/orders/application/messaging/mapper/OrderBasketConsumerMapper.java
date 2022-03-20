package br.gasmartins.orders.application.messaging.mapper;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.schemas.basket.order_submitted.OrderSubmitted;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderBasketConsumerMapper {

    private final OrderItemBasketConsumerMapper itemMapper;
    private final OrderPaymentMethodBasketConsumerMapper paymentMethodMapper;

    public Order mapToDomain(OrderSubmitted message){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderSubmitted, Order>() {
            @Override
            protected void configure() {
                skip(this.destination.getId());
                using((Converter<String, UUID>) context -> context.getSource() == null ? null : UUID.fromString(context.getSource()));
            }
        });
        var order = mapper.map(message, Order.class);
        message.getItems().stream().map(this.itemMapper::mapToDomain).forEach(order::addItem);
        message.getPaymentMethods().stream().map(this.paymentMethodMapper::mapToDomain).forEach(order::addPaymentMethod);
        return order;
    }
}
