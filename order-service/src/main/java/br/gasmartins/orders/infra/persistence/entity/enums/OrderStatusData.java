package br.gasmartins.orders.infra.persistence.entity.enums;

import br.gasmartins.orders.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum OrderStatusData {

    NEW(0, OrderStatus.NEW),
    SUBMITTED(1, OrderStatus.SUBMITTED),
    VALIDATED(2, OrderStatus.VALIDATED),
    CONFIRMED(3, OrderStatus.CONFIRMED),
    DELIVERED(4, OrderStatus.DELIVERED),
    REJECTED(5, OrderStatus.REJECTED),
    FINISHED(6, OrderStatus.FINISHED);

    private final Integer code;
    private final OrderStatus source;

    public static OrderStatusData fromCode(Integer code) {
        return Stream.of(OrderStatusData.values())
                .filter(orderStatusData -> orderStatusData.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public static OrderStatusData fromSource(OrderStatus source){
        return Stream.of(OrderStatusData.values())
                .filter(orderStatusData -> orderStatusData.getSource().equals(source))
                .findFirst()
                .orElse(null);
    }

}
