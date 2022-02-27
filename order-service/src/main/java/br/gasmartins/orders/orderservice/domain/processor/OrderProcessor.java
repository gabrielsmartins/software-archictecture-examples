package br.gasmartins.orders.orderservice.domain.processor;

import br.gasmartins.orders.orderservice.domain.Order;

public interface OrderProcessor {

    void process(Order order);

}
