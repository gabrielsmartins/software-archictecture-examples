package br.gasmartins.orders.domain;

import br.gasmartins.orders.domain.enums.OrderStatus;
import br.gasmartins.orders.domain.enums.PaymentType;
import br.gasmartins.orders.domain.state.NewOrderState;
import br.gasmartins.orders.domain.state.OrderState;
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
public class Order {

    private Long id;
    private UUID customerId;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;

    @Builder.Default
    private OrderState state = new NewOrderState();

    @Getter(AccessLevel.NONE)
    @Builder.Default
    private final List<OrderLog> logs = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Builder.Default
    private final List<OrderItem> items = new LinkedList<>();

    @Getter(AccessLevel.NONE)
    @Builder.Default
    private final List<OrderPaymentMethod> paymentMethods = new LinkedList<>();

    public List<OrderLog> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<OrderPaymentMethod> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    public Integer addLog(OrderLog orderLog) {
        orderLog.setOrder(this);
        this.logs.add(orderLog);
        return this.logs.size();
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

    public OrderState nextState(){
        this.state = this.state.next(this);
        return this.state;
    }

    public OrderState reject(){
        this.state = this.state.reject(this);
        return this.state;
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
    public static class OrderLog {

        private Order order;
        private LocalDateTime datetime;
        private OrderStatus status;

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
