package br.gasmartins.orders.infra.persistence.entity;

import br.gasmartins.orders.infra.persistence.entity.enums.OrderStatusData;
import br.gasmartins.orders.infra.persistence.entity.enums.PaymentTypeData;
import br.gasmartins.orders.infra.persistence.entity.enums.converter.OrderStatusDataConverter;
import br.gasmartins.orders.infra.persistence.entity.enums.converter.PaymentTypeDataConverter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "orders")
@Entity
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint", nullable = false)
    private Long id;

    @Column(name = "customer_id", columnDefinition = "uuid", nullable = false)
    private UUID customerId;

    @Column(name = "created_at", columnDefinition = "datetime", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "finished_at", columnDefinition = "datetime")
    private LocalDateTime finishedAt;

    @Column(name = "total_amount", columnDefinition = "numeric(15,2)", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "total_discount", columnDefinition = "numeric(15,2)", nullable = false)
    private BigDecimal totalDiscount;

    @Column(name = "status", columnDefinition = "int", nullable = false)
    @Convert(converter = OrderStatusDataConverter.class)
    private OrderStatusData status;

    @Getter(AccessLevel.NONE)
    @Builder.Default
    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<OrderLogEntity> logs = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Builder.Default
    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<OrderItemEntity> items = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Builder.Default
    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<OrderPaymentMethodEntity> paymentMethods = new LinkedList<>();

    public List<OrderLogEntity> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    public List<OrderItemEntity> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<OrderPaymentMethodEntity> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    public Integer addLog(OrderLogEntity orderLog) {
        orderLog.getId().setOrder(this);
        this.logs.add(orderLog);
        return this.logs.size();
    }

    public Integer addItem(OrderItemEntity item) {
        item.getId().setOrder(this);
        this.items.add(item);
        return this.items.size();
    }

    public Integer addPaymentMethod(OrderPaymentMethodEntity paymentMethod) {
        paymentMethod.getId().setOrder(this);
        this.paymentMethods.add(paymentMethod);
        return this.paymentMethods.size();
    }


    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "order_items")
    @Entity
    public static class OrderItemEntity implements Serializable {

        private static final long serialVersionUID = 1L;

        @EmbeddedId
        private OrderItemEntityId id;

        @Column(name = "quantity", columnDefinition = "int", nullable = false)
        private Integer quantity;

        @Column(name = "amount", columnDefinition = "numeric(15,2)", nullable = false)
        private BigDecimal amount;

        @Getter
        @Setter
        @ToString
        @Builder(setterPrefix = "with")
        @NoArgsConstructor
        @AllArgsConstructor
        @Embeddable
        public static class OrderItemEntityId  implements Serializable {

            private static final long serialVersionUID = 1L;

            @ManyToOne
            @JoinColumn(name = "order_id", referencedColumnName = "id", columnDefinition = "bigint", nullable = false)
            private OrderEntity order;

            @Column(name = "product_id", columnDefinition = "uuid", nullable = false)
            private UUID productId;

        }

    }

    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "order_logs")
    @Entity
    public static class OrderLogEntity implements Serializable {

        private static final long serialVersionUID = 1L;

        @EmbeddedId
        private OrderLogEntityId id;

        @Column(name = "status", columnDefinition = "int", nullable = false)
        @Convert(converter = OrderStatusDataConverter.class)
        private OrderStatusData status;

        @Getter
        @Setter
        @ToString
        @Builder(setterPrefix = "with")
        @NoArgsConstructor
        @AllArgsConstructor
        @Embeddable
        public static class OrderLogEntityId implements Serializable{

            private static final long serialVersionUID = 1L;

            @ManyToOne
            @JoinColumn(name = "order_id", referencedColumnName = "id", columnDefinition = "bigint", nullable = false)
            private OrderEntity order;

            @Column(name = "datetime", columnDefinition = "datetime", nullable = false)
            private LocalDateTime datetime;

        }

    }

    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "order_payment_methods")
    @Entity
    public static class OrderPaymentMethodEntity implements Serializable {

        private static final long serialVersionUID = 1L;

        @EmbeddedId
        private OrderPaymentMethodEntityId id;

        @Column(name = "amount", columnDefinition = "numeric(15,2)", nullable = false)
        private BigDecimal amount;

        @Getter
        @Setter
        @ToString
        @Builder(setterPrefix = "with")
        @NoArgsConstructor
        @AllArgsConstructor
        @Embeddable
        public static class OrderPaymentMethodEntityId implements Serializable {

            private static final long serialVersionUID = 1L;

            @ManyToOne
            @JoinColumn(name = "order_id", referencedColumnName = "id", columnDefinition = "bigint", nullable = false)
            private OrderEntity order;

            @Column(name = "payment_type", columnDefinition = "int", nullable = false)
            @Convert(converter = PaymentTypeDataConverter.class)
            private PaymentTypeData paymentType;

        }

    }
}
