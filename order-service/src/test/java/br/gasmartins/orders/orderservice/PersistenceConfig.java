package br.gasmartins.orders.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.gasmartins.orders.orderservice.infra.persistence.*"})
public class PersistenceConfig {

    public static void main(String[] args) {
        SpringApplication.run(PersistenceConfig.class);
    }
}
