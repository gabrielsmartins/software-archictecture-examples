package br.gasmartins.orders.orderservice.domain.publisher;

import br.gasmartins.orders.orderservice.domain.Order;

public interface OrderPublisher {

    void publish(Order order);

}
