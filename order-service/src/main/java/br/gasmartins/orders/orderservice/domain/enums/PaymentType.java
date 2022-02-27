package br.gasmartins.orders.orderservice.domain.enums;

import java.util.stream.Stream;

public enum PaymentType {

    CREDIT_CARD("CREDIT CARD"),
    CASH("CASH"),
    INTERNET_BANKING("INTERNET BANKING"),
    PAYPAL("PAYPAL");

    private String description;

    PaymentType(String description) {
        this.description = description;
    }

    public static PaymentType fromDescription(String paymentTypeDescription) {
        return Stream.of(PaymentType.values())
                .filter(paymentType -> paymentType.getDescription().equalsIgnoreCase(paymentTypeDescription))
                .findFirst()
                .orElse(null);
    }

    public String getDescription() {
        return description;
    }
}