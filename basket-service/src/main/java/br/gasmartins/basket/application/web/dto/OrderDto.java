package br.gasmartins.basket.application.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private UUID customerId;

    @JsonProperty(value = "created_at", required = true, access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty(value = "total_amount", required = true)
    @NotNull
    private BigDecimal totalAmount;

    @JsonProperty(value = "total_discount", required = true)
    @NotNull
    private BigDecimal totalDiscount;

    @Getter(AccessLevel.NONE)
    @Builder.Default
    @NotEmpty
    private final List<OrderItemDto> items = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Builder.Default
    @NotEmpty
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
