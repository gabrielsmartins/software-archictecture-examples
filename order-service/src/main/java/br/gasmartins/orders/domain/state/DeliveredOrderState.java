package br.gasmartins.orders.domain.state;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.domain.exception.IllegalOrderStateException;

public class DeliveredOrderState extends OrderState {

    public DeliveredOrderState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.DELIVERED;
    }

    @Override
    public OrderState next(Order order) {
        return new FinishedOrderState(order);
    }

    @Override
    public OrderState reject(Order order) {
        throw new IllegalOrderStateException("Order is already delivered");
    }
}
