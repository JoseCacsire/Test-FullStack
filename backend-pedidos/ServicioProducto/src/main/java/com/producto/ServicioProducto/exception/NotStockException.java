package com.producto.ServicioProducto.exception;

public class NotStockException extends RuntimeException{
    public NotStockException(String message) {
        super(message);
    }
}
