package br.gasmartins.basket.application.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@ToString
@EqualsAndHashCode(of = "id")
public class OrderDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "customer_id", required = true)
    private UUID customerId;

    @JsonProperty(value = "created_at", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty(value = "finished_at", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishedAt;

    @JsonProperty(value = "total_amount", required = true)
    private BigDecimal totalAmount;

    @JsonProperty(value = "total_discount", required = true)
    private BigDecimal totalDiscount;

    @Getter(AccessLevel.NONE)
    @Builder.Default
    private final List<OrderItemDto> items = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Builder.Default
    private final List<OrderPaymentMethodDto> paymentMethods = new LinkedList<>();

    public List<OrderItemDto> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<OrderPaymentMethodDto> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    public Integer addItem(OrderItemDto item) {
        this.items.add(item);
        return this.items.size();
    }

    public Integer addPaymentMethod(OrderPaymentMethodDto paymentMethod) {
        this.paymentMethods.add(paymentMethod);
        return this.paymentMethods.size();
    }


    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemDto {

        @JsonProperty(value = "product_id", required = true)
        private UUID productId;

        @JsonProperty(value = "quantity", required = true)
        private Integer quantity;

        @JsonProperty(value = "amount", required = true)
        private BigDecimal amount;

    }


    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderPaymentMethodDto {

        @JsonProperty(value = "payment_type", required = true)
        private String paymentType;

        @JsonProperty(value = "amount", required = true)
        private BigDecimal amount;

    }
}
