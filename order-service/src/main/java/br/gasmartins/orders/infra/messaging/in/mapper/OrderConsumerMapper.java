package br.gasmartins.orders.infra.messaging.in.mapper;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.schemas.orders.order_submitted.OrderSubmitted;
import org.modelmapper.ModelMapper;

public class OrderConsumerMapper {

    public Order map(OrderSubmitted message){
        var mapper = new ModelMapper();
        return mapper.map(message, Order.class);
    }
}
