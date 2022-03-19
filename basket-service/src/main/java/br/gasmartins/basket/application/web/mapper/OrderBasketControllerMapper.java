package br.gasmartins.basket.application.web.mapper;

import br.gasmartins.basket.application.web.dto.OrderDto;
import br.gasmartins.basket.domain.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderBasketControllerMapper {

    private final OrderItemBasketControllerMapper itemMapper;
    private final OrderPaymentMethodBasketControllerMapper paymentMethodMapper;

    public Order mapToDomain(OrderDto orderDTO){
        var mapper = new ModelMapper();
        var order = mapper.map(orderDTO, Order.class);
        orderDTO.getItems().stream().map(this.itemMapper::mapToDomain).forEach(order::addItem);
        orderDTO.getPaymentMethods().stream().map(this.paymentMethodMapper::mapToDomain).forEach(order::addPaymentMethod);
        return order;
    }

    public OrderDto mapToDto(Order order){
        var mapper = new ModelMapper();
        var orderDto = mapper.map(order, OrderDto.class);
        order.getItems().stream().map(this.itemMapper::mapToDto).forEach(orderDto::addItem);
        order.getPaymentMethods().stream().map(this.paymentMethodMapper::mapToDto).forEach(orderDto::addPaymentMethod);
        return orderDto;
    }
}
