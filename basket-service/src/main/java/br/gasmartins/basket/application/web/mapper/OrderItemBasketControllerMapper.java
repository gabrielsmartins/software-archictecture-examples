package br.gasmartins.basket.application.web.mapper;

import br.gasmartins.basket.application.web.dto.OrderDto.OrderItemDto;
import br.gasmartins.basket.domain.Order.OrderItem;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class OrderItemBasketControllerMapper {


    public OrderItemDto mapToDto(OrderItem orderItem){
        var mapper = new ModelMapper();
        return mapper.map(orderItem, OrderItemDto.class);
    }

    public OrderItem mapToDomain(OrderItemDto orderItemDto){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OrderItemDto, OrderItem>() {
            @Override
            protected void configure() {
              skip(this.destination.getOrder());
            }
        });
        return mapper.map(orderItemDto, OrderItem.class);
    }
}
