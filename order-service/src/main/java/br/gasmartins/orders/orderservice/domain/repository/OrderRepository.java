package br.gasmartins.orders.orderservice.domain.repository;

import br.gasmartins.orders.orderservice.domain.Order;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(Long id);
}
