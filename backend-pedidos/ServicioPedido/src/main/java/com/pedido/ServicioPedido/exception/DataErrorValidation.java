
package com.pedido.ServicioPedido.exception;

import org.springframework.validation.FieldError;

public class DataErrorValidation {
    private String field;
    private String message;

    public DataErrorValidation(FieldError fieldError) {
        this.field = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }

    // Getters y Setters
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
