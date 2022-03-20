package br.gasmartins.orders.infra.messaging.mapper.finished;

import br.gasmartins.orders.domain.Order.OrderPaymentMethod;
import br.gasmartins.schemas.orders.order_finished.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderFinishedPaymentMethodProducerMapper {

    public PaymentMethod mapToMessage(OrderPaymentMethod paymentMethod){
        var mapper = new ModelMapper();
        return mapper.map(paymentMethod, PaymentMethod.class);
    }

}
