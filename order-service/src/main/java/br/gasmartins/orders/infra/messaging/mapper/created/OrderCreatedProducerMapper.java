package br.gasmartins.orders.infra.messaging.mapper.created;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.infra.messaging.mapper.OrderProducerMapper;
import br.gasmartins.schemas.orders.order_created.OrderCreated;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderCreatedProducerMapper implements OrderProducerMapper {

    private final OrderCreatedItemProducerMapper itemMapper;
    private final OrderCreatedPaymentMethodProducerMapper paymentMethodMapper;

    @Override
    public SpecificRecord mapToMessage(Order order) {
        var mapper = new ModelMapper();
        var message = mapper.map(order, OrderCreated.class);
        var items = order.getItems().stream().map(this.itemMapper::mapToMessage).collect(Collectors.toList());
        var paymentMethods = order.getPaymentMethods().stream().map(this.paymentMethodMapper::mapToMessage).collect(Collectors.toList());
        message.setItems(items);
        message.setPaymentMethods(paymentMethods);
        return message;
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.CREATED;
    }

}
