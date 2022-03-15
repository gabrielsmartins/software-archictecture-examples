package br.gasmartins.basket.domain.service;

import br.gasmartins.basket.domain.Order;
import br.gasmartins.basket.domain.dispatcher.OrderDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BasketService implements IBasketService {

    private final OrderDispatcher dispatcher;

    @Override
    public Mono<Order> create(Order order) {
        return this.dispatcher.dispatch(order);
    }

}
