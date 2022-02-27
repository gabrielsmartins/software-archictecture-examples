package br.gasmartins.orders.orderservice.infra.messaging.in.mapper;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.schemas.orders.order_submitted.OrderSubmitted;
import org.modelmapper.ModelMapper;

public class OrderConsumerMapper {

    public Order map(OrderSubmitted message){
        var mapper = new ModelMapper();
        return mapper.map(message, Order.class);
    }
}
