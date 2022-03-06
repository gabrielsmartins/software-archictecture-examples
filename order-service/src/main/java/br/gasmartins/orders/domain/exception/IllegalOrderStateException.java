package br.gasmartins.orders.domain.exception;

public class IllegalOrderStateException extends RuntimeException{

    public IllegalOrderStateException(String message){
        super(message);
    }

}
