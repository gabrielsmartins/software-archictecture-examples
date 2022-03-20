package br.gasmartins.orders.infra.messaging.mapper.rejected;

import br.gasmartins.orders.domain.Order.OrderPaymentMethod;
import br.gasmartins.schemas.orders.order_rejected.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderRejectedPaymentMethodProducerMapper {

    public PaymentMethod mapToMessage(OrderPaymentMethod paymentMethod){
        var mapper = new ModelMapper();
        return mapper.map(paymentMethod, PaymentMethod.class);
    }

}
