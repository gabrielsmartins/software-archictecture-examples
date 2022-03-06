package br.gasmartins.orders.domain.state;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.domain.exception.IllegalOrderStateException;

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
