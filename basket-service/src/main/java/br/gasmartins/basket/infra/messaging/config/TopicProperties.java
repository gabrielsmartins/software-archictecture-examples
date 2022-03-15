package br.gasmartins.basket.infra.messaging.config;

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

    public static final String BASKET_TOPIC = "basket";
    public static final String ORDER_STATUS_TOPIC = "orders";

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
