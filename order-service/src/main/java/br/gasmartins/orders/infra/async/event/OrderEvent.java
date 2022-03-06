package br.gasmartins.orders.infra.async.event;

import br.gasmartins.orders.domain.Order;
import br.gasmartins.orders.domain.enums.OrderStatus;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class OrderEvent extends ApplicationEvent {

    protected final Order order;

    public OrderEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public abstract OrderStatus getStatus();

}
