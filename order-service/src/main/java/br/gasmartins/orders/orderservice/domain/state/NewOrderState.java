package br.gasmartins.orders.orderservice.domain.state;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.enums.OrderStatus;
import br.gasmartins.orders.orderservice.domain.exception.IllegalOrderStateException;

public class NewOrderState extends OrderState {

    public NewOrderState() {
        super();
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.NEW;
    }

    @Override
    public OrderState next(Order order) {
        return new SubmittedOrderState(order);
    }

    @Override
    public OrderState reject(Order order) {
        throw new IllegalOrderStateException("Order isn't submitted yet");
    }
}
