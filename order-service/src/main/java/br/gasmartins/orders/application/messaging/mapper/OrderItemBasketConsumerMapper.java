package br.gasmartins.orders.application.messaging.mapper;

import br.gasmartins.orders.domain.Order.OrderItem;
import br.gasmartins.schemas.basket.order_submitted.Item;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderItemBasketConsumerMapper {

    public OrderItem mapToDomain(Item message){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<Item, OrderItem>() {
            @Override
            protected void configure() {
                using((Converter<String, UUID>) context -> context.getSource() == null ? null : UUID.fromString(context.getSource())).map(this.source.getProductId(), this.destination.getProductId());
            }
        });
        return mapper.map(message, OrderItem.class);
    }
}
