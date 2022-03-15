package br.gasmartins.basket.domain.dispatcher;

import br.gasmartins.basket.domain.Order;
import reactor.core.publisher.Mono;

public interface OrderDispatcher {

    Mono<Order> dispatch(Order order);

}
