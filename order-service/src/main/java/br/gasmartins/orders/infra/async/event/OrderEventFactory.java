package br.gasmartins.orders.infra.async.event;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderEventFactory {

    private final Map<OrderStatus, OrderEvent> eventsMap;

    public OrderEventFactory(List<OrderEvent> events){
        this.eventsMap = new LinkedHashMap<>();
        events.forEach(e -> eventsMap.put(e.getStatus(), e));
    }

    public OrderEvent getEvent(Order order){
        var state = order.getState();
        return this.eventsMap.get(state.getStatus());
    }
}
