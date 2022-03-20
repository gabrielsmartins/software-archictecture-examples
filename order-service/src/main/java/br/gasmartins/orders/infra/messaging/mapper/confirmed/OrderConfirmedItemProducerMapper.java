package br.gasmartins.orders.infra.messaging.mapper.confirmed;

import br.gasmartins.orders.domain.Order.OrderItem;
import br.gasmartins.schemas.orders.order_confirmed.Item;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderConfirmedItemProducerMapper {

    public Item mapToMessage(OrderItem orderItem){
        var mapper = new ModelMapper();
        return mapper.map(orderItem, Item.class);
    }

}
