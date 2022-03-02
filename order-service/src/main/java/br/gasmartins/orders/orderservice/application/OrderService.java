package br.gasmartins.orders.orderservice.application;

import br.gasmartins.orders.orderservice.domain.Order;

import java.util.Optional;

public interface OrderService {

    Order submit(Order order);

    Order validate(Order order);

    Order confirm(Order order);

    Order deliver(Order order);

    Order reject(Order order);

    Order finish(Order order);

    Optional<Order> findById(Long id);

}
