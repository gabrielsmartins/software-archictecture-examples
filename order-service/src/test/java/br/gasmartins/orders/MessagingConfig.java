package br.gasmartins.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.gasmartins.orders.orderservice.infra.messaging.*", "br.gasmartins.orders.application.messaging.*"})
public class MessagingConfig {

    public static void main(String[] args) {
        SpringApplication.run(MessagingConfig.class);
    }
}
