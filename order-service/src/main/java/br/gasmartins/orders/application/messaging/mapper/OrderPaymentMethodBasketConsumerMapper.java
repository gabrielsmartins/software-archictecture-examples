package br.gasmartins.orders.application.messaging.mapper;

import br.gasmartins.orders.domain.Order.OrderPaymentMethod;
import br.gasmartins.schemas.basket.order_submitted.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentMethodBasketConsumerMapper {

    public OrderPaymentMethod mapToDomain(PaymentMethod message){
        var mapper = new ModelMapper();
        return mapper.map(message, OrderPaymentMethod.class);
    }
}
