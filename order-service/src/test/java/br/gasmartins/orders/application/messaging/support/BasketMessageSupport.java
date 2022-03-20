package br.gasmartins.orders.application.messaging.support;

import br.gasmartins.schemas.basket.order_submitted.Item;
import br.gasmartins.schemas.basket.order_submitted.OrderSubmitted;
import br.gasmartins.schemas.basket.order_submitted.PaymentMethod;
import br.gasmartins.schemas.basket.order_submitted.PaymentType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BasketMessageSupport {

    public static OrderSubmitted.Builder defaultOrderMessage(){
        return OrderSubmitted.newBuilder()
                .setCustomerId(UUID.randomUUID().toString())
                .setCreatedAt(LocalDateTime.now())
                .setTotalAmount(BigDecimal.valueOf(1000.00))
                .setTotalDiscount(BigDecimal.valueOf(500.00));
    }

    public static Item.Builder defaultOrderItemMessage(){
        return Item.newBuilder()
                .setQuantity(1)
                .setProductId(UUID.randomUUID().toString())
                .setAmount(BigDecimal.valueOf(500.00));
    }

    public static PaymentMethod.Builder defaultOrderPaymentMethodMessage(){
        return PaymentMethod.newBuilder()
                .setPaymentType(PaymentType.CASH)
                .setAmount(BigDecimal.valueOf(500.00));
    }
}
