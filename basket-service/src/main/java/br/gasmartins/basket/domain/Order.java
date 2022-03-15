package br.gasmartins.basket.domain;


import br.gasmartins.basket.domain.enums.PaymentType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
@ToString(exclude = {"items", "paymentMethods"})
@EqualsAndHashCode(of = "id")
public class Order {

    private Long id;
    private UUID customerId;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;

    @Getter(AccessLevel.NONE)
    @Builder.Default
    private final List<OrderItem> items = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Builder.Default
    private final List<OrderPaymentMethod> paymentMethods = new LinkedList<>();

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<OrderPaymentMethod> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    public Integer addItem(OrderItem item) {
        item.setOrder(this);
        this.items.add(item);
        return this.items.size();
    }

    public Integer addPaymentMethod(OrderPaymentMethod paymentMethod) {
        paymentMethod.setOrder(this);
        this.paymentMethods.add(paymentMethod);
        return this.paymentMethods.size();
    }


    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {

        private Order order;
        private UUID productId;
        private Integer quantity;
        private BigDecimal amount;

    }


    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderPaymentMethod {

        private Order order;
        private PaymentType paymentType;
        private BigDecimal amount;

    }
}
