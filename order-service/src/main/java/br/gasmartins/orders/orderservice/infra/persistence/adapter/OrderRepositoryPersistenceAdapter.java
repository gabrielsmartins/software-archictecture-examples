package br.gasmartins.orders.orderservice.infra.persistence.adapter;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.repository.OrderRepository;
import br.gasmartins.orders.orderservice.infra.persistence.mapper.OrderPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryPersistenceAdapter implements OrderRepository {

    private final OrderPersistenceMapper mapper;

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }
}
