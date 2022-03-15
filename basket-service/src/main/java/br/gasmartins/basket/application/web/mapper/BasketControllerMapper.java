package br.gasmartins.basket.application.web.mapper;

import br.gasmartins.basket.application.web.dto.OrderDto;
import br.gasmartins.basket.domain.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BasketControllerMapper {

    public Order mapToDomain(OrderDto orderDTO){
        var mapper = new ModelMapper();
        return mapper.map(orderDTO, Order.class);
    }

    public OrderDto mapToDto(Order order){
        var mapper = new ModelMapper();
        return mapper.map(order, OrderDto.class);
    }
}
