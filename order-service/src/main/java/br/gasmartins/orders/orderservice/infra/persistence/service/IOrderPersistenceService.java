package br.gasmartins.orders.orderservice.infra.persistence.service;

import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity;

import java.util.Optional;

public interface IOrderPersistenceService {

    OrderEntity save(OrderEntity orderEntity);

    Optional<OrderEntity> findById(Long id);

}
