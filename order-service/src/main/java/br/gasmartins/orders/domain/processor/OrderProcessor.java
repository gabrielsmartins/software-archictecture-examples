package br.gasmartins.orders.domain.processor;

import br.gasmartins.orders.domain.Order;

public interface OrderProcessor {

    void process(Order order);

}
