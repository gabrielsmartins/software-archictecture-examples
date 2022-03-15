package br.gasmartins.orders.application;

import br.gasmartins.orders.domain.Order;

import java.util.Optional;

public interface OrderService {

    Order create(Order order);

    Order validate(Order order);

    Order confirm(Order order);

    Order deliver(Order order);

    Order reject(Order order);

    Order finish(Order order);

    Optional<Order> findById(Long id);

}
