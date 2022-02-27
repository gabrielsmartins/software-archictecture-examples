package br.gasmartins.orders.orderservice.domain.state;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.enums.OrderStatus;
import br.gasmartins.orders.orderservice.domain.exception.IllegalOrderStateException;

public class RejectedOrderState extends OrderState {

    public RejectedOrderState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.REJECTED;
    }

    @Override
    public OrderState next(Order order) {
        throw new IllegalOrderStateException("Order is already rejected");
    }

    @Override
    public OrderState reject(Order order) {
        throw new IllegalOrderStateException("Order is already rejected");
    }
}
