package br.gasmartins.orders.orderservice.infra.persistence.adapter;

import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.repository.OrderRepository;
import br.gasmartins.orders.orderservice.infra.persistence.mapper.OrderPersistenceMapper;
import br.gasmartins.orders.orderservice.infra.persistence.service.IOrderPersistenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryPersistenceAdapter implements OrderRepository {

    private final OrderPersistenceMapper mapper;
    private final IOrderPersistenceService service;

    @Override
    public Order save(Order order) {
        log.info("Mapping order: {}", kv("order", order));
        var orderEntity = this.mapper.mapToEntity(order);
        log.info("Saving order entity: {}", kv("order_entity", orderEntity));
        var savedOrderEntity = this.service.save(orderEntity);
        log.info("Mapping order entity: {}", kv("order_entity", savedOrderEntity));
        return this.mapper.mapToDomain(savedOrderEntity);
    }

    @Override
    public Optional<Order> findById(Long id) {
        log.info("Searching by id: {}", kv("id", id));
        var optionalOrderEntity = this.service.findById(id);
        log.info("Query result: {}", kv("result", optionalOrderEntity));
        return optionalOrderEntity.map(this.mapper::mapToDomain);
    }
}
