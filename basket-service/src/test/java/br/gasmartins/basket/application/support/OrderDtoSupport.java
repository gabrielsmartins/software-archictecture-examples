package br.gasmartins.basket.application.support;

import br.gasmartins.basket.application.web.dto.OrderDto;
import br.gasmartins.basket.domain.enums.PaymentType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDtoSupport {

    public static OrderDto.OrderDtoBuilder defaultOrderDto(){
        return OrderDto.builder().withId(1L)
                .withCreatedAt(LocalDateTime.now())
                .withCustomerId(UUID.randomUUID())
                .withTotalAmount(BigDecimal.valueOf(100.00))
                .withTotalDiscount(BigDecimal.valueOf(50.00));
    }

    public static OrderDto.OrderItemDto.OrderItemDtoBuilder defaultOrderItemDto(){
        return OrderDto.OrderItemDto.builder()
                .withAmount(BigDecimal.valueOf(1500.50))
                .withProductId(UUID.randomUUID())
                .withQuantity(1);
    }

    public static OrderDto.OrderPaymentMethodDto.OrderPaymentMethodDtoBuilder defaultOrderPaymentMethodDto(){
        return OrderDto.OrderPaymentMethodDto.builder()
                .withAmount(BigDecimal.valueOf(1500.50))
                .withPaymentType(PaymentType.CASH.getDescription());
    }
}
