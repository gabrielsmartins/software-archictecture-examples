package br.gasmartins.orders.infra.persistence.service;

import br.gasmartins.orders.infra.persistence.entity.OrderEntity;
import br.gasmartins.orders.infra.persistence.repository.OrderEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderPersistenceService implements IOrderPersistenceService {

    private final OrderEntityRepository repository;

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return this.repository.saveAndFlush(orderEntity);
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
        return this.repository.findById(id);
    }
}
