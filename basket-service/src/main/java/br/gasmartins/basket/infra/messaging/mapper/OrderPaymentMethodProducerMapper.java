package br.gasmartins.basket.infra.messaging.mapper;

import br.gasmartins.basket.domain.Order.OrderPaymentMethod;
import br.gasmartins.schemas.basket.order_submitted.PaymentMethod;
import br.gasmartins.schemas.basket.order_submitted.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentMethodProducerMapper {

    public PaymentMethod mapToMessage(OrderPaymentMethod orderPaymentMethod){
        return PaymentMethod.newBuilder()
                .setAmount(orderPaymentMethod.getAmount())
                .setPaymentType(PaymentType.valueOf(orderPaymentMethod.getPaymentType().getDescription()))
                .build();
    }

}
