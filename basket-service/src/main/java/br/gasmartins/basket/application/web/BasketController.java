package br.gasmartins.basket.application.web;

import br.gasmartins.basket.application.web.dto.OrderDto;
import br.gasmartins.basket.application.web.mapper.BasketControllerMapper;
import br.gasmartins.basket.domain.service.IBasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController
@RequestMapping("/orders/v1")
@RequiredArgsConstructor
@Validated
@Slf4j
public class BasketController {

    private final BasketControllerMapper mapper;
    private final IBasketService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<OrderDto> create(@RequestBody @Valid OrderDto orderDTO){
        log.info("Receiving order request: {}", kv("request", orderDTO));

        log.info("Mapping order request: {}", kv("request", orderDTO));
        var order = this.mapper.mapToDomain(orderDTO);
        log.info("Order was mapped successfully: {}", kv("order", order));

        log.info("Creating order: {}", kv("order", order));
        var orderMono = this.service.create(order);
        return orderMono.doOnSuccess(o -> log.info("Order create successfully: {}", kv("order", order)))
                        .doOnError(throwable -> log.error("Error saving order", throwable))
                        .map(this.mapper::mapToDto);
    }

}
