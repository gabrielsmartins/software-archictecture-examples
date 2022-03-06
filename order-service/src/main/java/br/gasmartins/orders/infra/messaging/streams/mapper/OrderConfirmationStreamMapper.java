package br.gasmartins.orders.infra.messaging.streams.mapper;

import br.gasmartins.schemas.inventories.inventory_allocated.InventoryAllocated;
import br.gasmartins.schemas.payments.payment_accepted.PaymentAccepted;
import br.gasmartins.schemas.shippings.shipping_ready.Item;
import br.gasmartins.schemas.shippings.shipping_ready.ShippingReady;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class OrderConfirmationStreamMapper implements ValueJoiner<PaymentAccepted, InventoryAllocated, ShippingReady> {

    @Override
    public ShippingReady apply(PaymentAccepted paymentAccepted, InventoryAllocated inventoryAllocated) {
        var shippingItems = inventoryAllocated.getItems()
                                                        .stream()
                                                        .map(it -> {
                                                            var item = new Item();
                                                            item.setProductId(it.getProductId());
                                                            item.setQuantity(it.getAllocatedQuantity());
                                                            return item;
                                                        })
                                                        .collect(Collectors.toList());
        return ShippingReady.newBuilder()
                            .setOrderId(paymentAccepted.getOrderId())
                            .setConfirmedAt(paymentAccepted.getCreatedAt())
                            .setCreatedAt(LocalDateTime.now())
                            .setPaymentId(paymentAccepted.getId())
                            .setCustomerId(paymentAccepted.getCustomerId())
                            .setItems(shippingItems)
                            .build();
    }
}
