package br.gasmartins.orders.orderservice.application.service;

import br.gasmartins.orders.orderservice.application.OrderService;
import br.gasmartins.orders.orderservice.domain.Order;
import br.gasmartins.orders.orderservice.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public Order create(Order order) {
        log.info("Searching for existing order id: {}", order.getId());
        var optionalOrder = this.repository.findById(order.getId());
        return optionalOrder.orElseGet(() -> {
            log.info("Creating order: {}", order);
            var orderState = order.nextState();
            log.info("Order was created successfully: {}", order);
            return orderState.getOrder();
        });
    }

    @Override
    public Order validate(Order order) {
        var orderState = order.nextState();
        return orderState.getOrder();
    }

    @Override
    public Order confirm(Order order) {
        var orderState = order.nextState();
        return orderState.getOrder();
    }

    @Override
    public Order deliver(Order order) {
        var orderState = order.nextState();
        return orderState.getOrder();
    }

    @Override
    public Order reject(Order order) {
        var orderState = order.reject();
        return orderState.getOrder();
    }

    @Override
    public Order finish(Order order) {
        var orderState = order.nextState();
        return orderState.getOrder();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return this.repository.findById(id);
    }

}
