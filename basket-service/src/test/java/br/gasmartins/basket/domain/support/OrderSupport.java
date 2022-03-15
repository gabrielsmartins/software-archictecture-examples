package br.gasmartins.basket.domain.support;


import br.gasmartins.basket.domain.Order;
import br.gasmartins.basket.domain.enums.PaymentType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderSupport {

    public static Order.OrderBuilder defaultOrder(){
        return Order.builder()
                    .withId(1L)
                    .withCustomerId(UUID.randomUUID())
                    .withCreatedAt(LocalDateTime.now())
                    .withTotalAmount(BigDecimal.valueOf(1500.50))
                    .withTotalDiscount(BigDecimal.valueOf(500));
        }

    public static Order.OrderItem.OrderItemBuilder defaultOrderItem(){
        return Order.OrderItem.builder()
                .withAmount(BigDecimal.valueOf(1500.50))
                .withProductId(UUID.randomUUID())
                .withQuantity(1);
    }

    public static Order.OrderPaymentMethod.OrderPaymentMethodBuilder defaultOrderPaymentMethod(){
        return Order.OrderPaymentMethod.builder()
                .withAmount(BigDecimal.valueOf(1500.50))
                .withPaymentType(PaymentType.CASH);
    }
}
