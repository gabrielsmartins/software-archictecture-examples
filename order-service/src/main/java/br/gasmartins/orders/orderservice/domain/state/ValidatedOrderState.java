package br.gasmartins.orders.orderservice.domain.state;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.enums.OrderStatus;

public class ValidatedOrderState extends OrderState {

    public ValidatedOrderState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.VALIDATED;
    }

    @Override
    public OrderState next(Order order) {
        return new ConfirmedOrderState(order);
    }

    @Override
    public OrderState reject(Order order) {
        return new RejectedOrderState(order);
    }
}
