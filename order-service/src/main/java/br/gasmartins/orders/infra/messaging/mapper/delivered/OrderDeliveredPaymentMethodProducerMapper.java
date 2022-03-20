package br.gasmartins.orders.infra.messaging.mapper.delivered;

import br.gasmartins.orders.domain.Order.OrderPaymentMethod;
import br.gasmartins.schemas.orders.order_delivered.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDeliveredPaymentMethodProducerMapper {

    public PaymentMethod mapToMessage(OrderPaymentMethod paymentMethod){
        var mapper = new ModelMapper();
        return mapper.map(paymentMethod, PaymentMethod.class);
    }

}
