package com.cliente.ServicioCliente.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorRecord> handleModelNotFoundException(ModelNotFoundException ex, ServerWebExchange exchange) {
        log.error("ModelNotFoundException");
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(), exchange.getRequest().getURI().toString());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

}
