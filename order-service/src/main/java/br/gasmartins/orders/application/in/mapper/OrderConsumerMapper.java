package br.gasmartins.orders.application.in.mapper;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.schemas.basket.order_submitted.OrderSubmitted;
import org.modelmapper.ModelMapper;

public class OrderConsumerMapper {

    public Order map(OrderSubmitted message){
        var mapper = new ModelMapper();
        return mapper.map(message, Order.class);
    }
}
