package br.gasmartins.orders.infra.async.event;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import lombok.Getter;

@Getter
public class OrderValidatedEvent extends OrderEvent {

    public OrderValidatedEvent(Object source, Order order) {
        super(source, order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.VALIDATED;
    }
}
