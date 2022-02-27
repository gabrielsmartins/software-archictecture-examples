package br.gasmartins.orders.orderservice.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    NEW("NEW"),
    SUBMITTED("SUBMITTED"),
    VALIDATED("VALIDATED"),
    CONFIRMED("CONFIRMED"),
    DELIVERED("DELIVERED"),
    REJECTED("REJECTED"),
    FINISHED("FINISHED");

    private final String description;

}
