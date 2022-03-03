package br.gasmartins.orders.orderservice.infra.persistence.support;

import br.gasmartins.orders.orderservice.infra.persistence.entity.OrderEntity;
import br.gasmartins.orders.orderservice.infra.persistence.entity.enums.OrderStatusData;
import br.gasmartins.orders.orderservice.infra.persistence.entity.enums.PaymentTypeData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderEntitySupport {

    public static OrderEntity.OrderEntityBuilder defaultOrderEntity(){
        return OrderEntity.builder()
                    .withId(1L)
                    .withCustomerId(UUID.randomUUID())
                    .withCreatedAt(LocalDateTime.now())
                    .withTotalAmount(BigDecimal.valueOf(1500.50))
                    .withTotalDiscount(BigDecimal.valueOf(500));
        }

    public static OrderEntity.OrderLogEntity.OrderLogEntityBuilder defaultOrderLogEntity(){
        return OrderEntity.OrderLogEntity.builder()
                .withId(OrderEntity.OrderLogEntity.OrderLogEntityId.builder()
                        .withDatetime(LocalDateTime.now())
                        .build())
                .withStatus(OrderStatusData.NEW);
    }

    public static OrderEntity.OrderItemEntity.OrderItemEntityBuilder defaultOrderItemEntity(){
        return OrderEntity.OrderItemEntity.builder()
                                          .withId(OrderEntity.OrderItemEntity.OrderItemEntityId.builder()
                                                .withProductId(UUID.randomUUID())
                                                .build())
                                          .withAmount(BigDecimal.valueOf(1500.50))
                                          .withQuantity(1);
    }

    public static OrderEntity.OrderPaymentMethodEntity.OrderPaymentMethodEntityBuilder defaultOrderPaymentMethodEntity(){
        return OrderEntity.OrderPaymentMethodEntity.builder()
                                                    .withId(OrderEntity.OrderPaymentMethodEntity.OrderPaymentMethodEntityId.builder()
                                                            .withPaymentType(PaymentTypeData.CASH)
                                                            .build())
                                                    .withAmount(BigDecimal.valueOf(1500.50));
    }
}
