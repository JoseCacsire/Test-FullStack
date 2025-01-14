package com.pedido.ServicioPedido.exception;

public class NotStockException extends RuntimeException{
    public NotStockException(String message) {
        super(message);
    }
}
