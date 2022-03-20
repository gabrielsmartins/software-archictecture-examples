package br.gasmartins.orders.infra.messaging.mapper.validated;

import br.gasmartins.orders.domain.Order.OrderItem;
import br.gasmartins.schemas.orders.order_validated.Item;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderValidatedItemProducerMapper {

    public Item mapToMessage(OrderItem orderItem){
        var mapper = new ModelMapper();
        return mapper.map(orderItem, Item.class);
    }

}
