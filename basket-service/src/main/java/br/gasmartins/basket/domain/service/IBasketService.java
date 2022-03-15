package br.gasmartins.basket.domain.service;

import br.gasmartins.basket.domain.Order;
import reactor.core.publisher.Mono;

public interface IBasketService {

    Mono<Order> create(Order order);


}
