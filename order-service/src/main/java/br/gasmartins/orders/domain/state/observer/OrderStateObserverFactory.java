package br.gasmartins.orders.domain.state.observer;


import java.util.List;

public class OrderStateObserverFactory {

    private static List<OrderStateSubscriber> SUBSCRIBERS;

    private OrderStateObserverFactory(List<OrderStateSubscriber> subscribers){
        SUBSCRIBERS = subscribers;
    }

    public static <T extends OrderStateSubscriber> T getSubscriber(Class<T> subscriberClass) {
       return (T) SUBSCRIBERS.stream()
                             .filter(o -> o.getClass().isAssignableFrom(subscriberClass))
                             .findFirst()
                             .orElseThrow(() -> new IllegalArgumentException("Subscriber not found for class: " + subscriberClass));
    }

    public static List<OrderStateSubscriber> getSubscribers() {
        return SUBSCRIBERS;
    }
}
