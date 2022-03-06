package br.gasmartins.orders.domain.state;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;

public class SubmittedOrderState extends OrderState {

    public SubmittedOrderState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.SUBMITTED;
    }

    @Override
    public OrderState next(Order order) {
        return new ValidatedOrderState(order);
    }

    @Override
    public OrderState reject(Order order) {
        return new RejectedOrderState(order);
    }

}