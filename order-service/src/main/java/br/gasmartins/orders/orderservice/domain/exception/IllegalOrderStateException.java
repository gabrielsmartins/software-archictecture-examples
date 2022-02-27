package br.gasmartins.orders.orderservice.domain.exception;

public class IllegalOrderStateException extends RuntimeException{

    public IllegalOrderStateException(String message){
        super(message);
    }

}
