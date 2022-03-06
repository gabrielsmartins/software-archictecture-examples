package br.gasmartins.orders.infra.persistence.entity.enums;

import br.gasmartins.orders.domain.enums.PaymentType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum PaymentTypeData {

    CREDIT_CARD(1, PaymentType.CREDIT_CARD),
    CASH(2, PaymentType.CASH),
    INTERNET_BANKING(3, PaymentType.INTERNET_BANKING),
    PAYPAL(4, PaymentType.PAYPAL);

    private final Integer code;
    private final PaymentType source;

    public static PaymentTypeData fromCode(Integer code) {
        return Stream.of(PaymentTypeData.values())
                .filter(paymentTypeData -> paymentTypeData.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public static PaymentTypeData fromSource(PaymentType paymentType) {
        return Stream.of(PaymentTypeData.values())
                .filter(paymentTypeData -> paymentTypeData.getSource().equals(paymentType))
                .findFirst()
                .orElse(null);
    }

}