package br.gasmartins.orders.infra.messaging.mapper.confirmed;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.infra.messaging.mapper.OrderProducerMapper;
import br.gasmartins.schemas.orders.order_confirmed.OrderConfirmed;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConfirmedProducerMapper implements OrderProducerMapper<OrderConfirmed> {

    private final OrderConfirmedItemProducerMapper itemMapper;
    private final OrderConfirmedPaymentMethodProducerMapper paymentMethodMapper;

    @Override
    public OrderConfirmed mapToMessage(Order order) {
        var mapper = new ModelMapper();
        var message = mapper.map(order, OrderConfirmed.class);
        var items = order.getItems().stream().map(this.itemMapper::mapToMessage).collect(Collectors.toList());
        var paymentMethods = order.getPaymentMethods().stream().map(this.paymentMethodMapper::mapToMessage).collect(Collectors.toList());
        message.setItems(items);
        message.setPaymentMethods(paymentMethods);
        return message;
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.CONFIRMED;
    }

}
