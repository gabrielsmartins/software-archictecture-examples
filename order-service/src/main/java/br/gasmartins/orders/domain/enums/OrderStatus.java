package br.gasmartins.orders.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    NEW("NEW"),
    CREATED("CREATED"),
    VALIDATED("VALIDATED"),
    CONFIRMED("CONFIRMED"),
    DELIVERED("DELIVERED"),
    REJECTED("REJECTED"),
    FINISHED("FINISHED");

    private final String description;

}
