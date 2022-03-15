package br.gasmartins.basket.infra.messaging.mapper;

import br.gasmartins.basket.domain.Order.OrderItem;
import br.gasmartins.schemas.basket.order_submitted.Item;
import org.springframework.stereotype.Component;

@Component
public class OrderItemProducerMapper {

    public Item mapToMessage(OrderItem orderItem){
        return Item.newBuilder()
                   .setProductId(orderItem.getProductId().toString())
                   .setAmount(orderItem.getAmount())
                   .setQuantity(orderItem.getQuantity())
                   .build();
    }

}
