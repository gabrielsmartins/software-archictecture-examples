package br.gasmartins.orders.infra.messaging.mapper.finished;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.infra.messaging.mapper.OrderProducerMapper;
import br.gasmartins.schemas.orders.order_finished.OrderFinished;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderFinishedProducerMapper implements OrderProducerMapper<OrderFinished> {

    private final OrderFinishedItemProducerMapper itemMapper;
    private final OrderFinishedPaymentMethodProducerMapper paymentMethodMapper;

    @Override
    public OrderFinished mapToMessage(Order order) {
        var mapper = new ModelMapper();
        var message = mapper.map(order, OrderFinished.class);
        var items = order.getItems().stream().map(this.itemMapper::mapToMessage).collect(Collectors.toList());
        var paymentMethods = order.getPaymentMethods().stream().map(this.paymentMethodMapper::mapToMessage).collect(Collectors.toList());
        message.setItems(items);
        message.setPaymentMethods(paymentMethods);
        return message;
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.FINISHED;
    }

}
