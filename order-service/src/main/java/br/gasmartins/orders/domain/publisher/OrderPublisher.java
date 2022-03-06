package br.gasmartins.orders.domain.publisher;

import br.gasmartins.orders.domain.Order;

public interface OrderPublisher {

    void publish(Order order);

}
