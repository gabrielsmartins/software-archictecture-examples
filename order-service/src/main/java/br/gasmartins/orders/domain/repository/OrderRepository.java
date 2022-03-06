package br.gasmartins.orders.domain.repository;

import br.gasmartins.orders.domain.Order;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(Long id);
}
