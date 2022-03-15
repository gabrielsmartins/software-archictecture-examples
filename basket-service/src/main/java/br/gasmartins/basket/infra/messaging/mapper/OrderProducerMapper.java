package br.gasmartins.basket.infra.messaging.mapper;

import br.gasmartins.basket.domain.Order;
import br.gasmartins.schemas.basket.order_submitted.OrderSubmitted;
import br.gasmartins.schemas.orders.order_created.OrderCreated;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class OrderProducerMapper {

    private final OrderItemProducerMapper itemMapper;
    private final OrderPaymentMethodProducerMapper paymentMethodMapper;

    public OrderSubmitted mapToMessage(Order order){
        var itemsMessage = order.getItems()
                                           .stream()
                                           .map(this.itemMapper::mapToMessage)
                                           .collect(Collectors.toList());


        var paymentMethodsMessage = order.getPaymentMethods()
                                                           .stream()
                                                           .map(this.paymentMethodMapper::mapToMessage)
                                                           .collect(Collectors.toList());

        return OrderSubmitted.newBuilder()
                            .setCreatedAt(order.getCreatedAt())
                            .setTotalAmount(order.getTotalAmount())
                            .setTotalDiscount(order.getTotalDiscount())
                            .setCustomerId(order.getCustomerId().toString())
                            .setItems(itemsMessage)
                            .setPaymentMethods(paymentMethodsMessage)
                            .build();
    }

    public Order mapToDomain(OrderCreated message){
        var mapper = new ModelMapper();
        return mapper.map(message, Order.class);
    }

}
