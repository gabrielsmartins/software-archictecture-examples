package br.gasmartins.orders.infra.messaging.mapper.rejected;

import br.gasmartins.orders.domain.Order.OrderItem;
import br.gasmartins.schemas.orders.order_rejected.Item;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderRejectedItemProducerMapper {

    public Item mapToMessage(OrderItem orderItem){
        var mapper = new ModelMapper();
        return mapper.map(orderItem, Item.class);
    }

}
