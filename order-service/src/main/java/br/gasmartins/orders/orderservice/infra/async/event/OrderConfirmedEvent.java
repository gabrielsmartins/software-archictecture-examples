package br.gasmartins.orders.orderservice.infra.async.event;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.enums.OrderStatus;
import lombok.Getter;

@Getter
public class OrderConfirmedEvent extends OrderEvent {

    public OrderConfirmedEvent(Object source, Order order) {
        super(source, order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.CONFIRMED;
    }
}
