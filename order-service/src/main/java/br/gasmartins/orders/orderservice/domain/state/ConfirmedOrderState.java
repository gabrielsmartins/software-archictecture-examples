package br.gasmartins.orders.orderservice.domain.state;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.enums.OrderStatus;
import br.gasmartins.orders.orderservice.domain.exception.IllegalOrderStateException;

public class ConfirmedOrderState extends OrderState {

    public ConfirmedOrderState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.CONFIRMED;
    }

    @Override
    public OrderState next(Order order) {
        return new DeliveredOrderState(order);
    }

    @Override
    public OrderState reject(Order order) {
        throw new IllegalOrderStateException("Order is already confirmed");
    }
}
