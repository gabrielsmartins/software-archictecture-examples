package br.gasmartins.orders.orderservice.infra.messaging.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "topics")
@Getter
@Setter
@ToString
public class TopicProperties {

    public static final String BASKET_TOPIC = "basket-orders";
    public static final String SHIPPING_TOPIC = "shipping-orders";
    public static final String FRAUD_TOPIC = "fraud-orders";
    public static final String PAYMENT_TOPIC = "payment-orders";
    public static final String INVENTORY_TOPIC = "inventory-orders";

    private Map<String, String> input;
    private Map<String, String> output;

    public String getInputTopic(String topicName) {
        return input.entrySet().stream()
                .filter(t -> t.getKey().equals(topicName))
                .findFirst()
                .orElse(Map.entry("", ""))
                .getValue();
    }

    public String getOutputTopic(String topicName) {
        return output.entrySet().stream()
                .filter(t -> t.getKey().equals(topicName))
                .findFirst()
                .orElse(Map.entry("", ""))
                .getValue();
    }

}
