package br.gasmartins.basket.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum PaymentType {

    CREDIT_CARD("CREDIT CARD"),
    CASH("CASH"),
    INTERNET_BANKING("INTERNET BANKING"),
    PAYPAL("PAYPAL");

    private final String description;

    public static PaymentType fromDescription(String paymentTypeDescription) {
        return Stream.of(PaymentType.values())
                .filter(paymentType -> paymentType.getDescription().equalsIgnoreCase(paymentTypeDescription))
                .findFirst()
                .orElse(null);
    }

}