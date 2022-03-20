package br.gasmartins.orders.infra.messaging.mapper.confirmed;

import br.gasmartins.orders.domain.Order.OrderPaymentMethod;
import br.gasmartins.schemas.orders.order_confirmed.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderConfirmedPaymentMethodProducerMapper {

    public PaymentMethod mapToMessage(OrderPaymentMethod paymentMethod){
        var mapper = new ModelMapper();
        return mapper.map(paymentMethod, PaymentMethod.class);
    }

}
