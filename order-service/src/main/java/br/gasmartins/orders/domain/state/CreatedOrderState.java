package br.gasmartins.orders.domain.state;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;

public class CreatedOrderState extends OrderState {

    public CreatedOrderState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.CREATED;
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
