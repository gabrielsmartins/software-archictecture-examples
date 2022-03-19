package br.gasmartins.orders.infra.messaging.mapper;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderProducerMapperFactory {

    private final Map<OrderStatus, OrderProducerMapper> mapperMap;

    public OrderProducerMapperFactory(List<OrderProducerMapper> mappers){
        this.mapperMap = this.build(mappers);
    }

    private Map<OrderStatus, OrderProducerMapper> build(List<OrderProducerMapper> mappers){
        return mappers.stream()
                      .collect(Collectors.toMap(OrderProducerMapper::getStatus, Function.identity()));
    }

    public OrderProducerMapper createMapper(Order order){
        return this.mapperMap.get(order.getState().getStatus());
    }
}
