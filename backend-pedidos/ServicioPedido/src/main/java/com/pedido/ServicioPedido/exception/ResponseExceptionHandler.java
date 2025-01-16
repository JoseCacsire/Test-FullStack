package com.pedido.ServicioPedido.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorRecord> handleModelNotFoundException(ModelNotFoundException ex, ServerWebExchange exchange) {
        log.error("ModelNotFoundException");
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(), exchange.getRequest().getURI().toString());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotStockException.class)
    public ResponseEntity<CustomErrorRecord> handleNotStockException(NotStockException ex, ServerWebExchange exchange) {
        CustomErrorRecord errorResponse = new CustomErrorRecord(
                LocalDateTime.now(),
                ex.getMessage(),
                exchange.getRequest().getURI().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EstadoInvalidoException.class)
    public ResponseEntity<CustomErrorRecord> handleEstadoInvalidoException(EstadoInvalidoException ex, ServerWebExchange exchange) {
        CustomErrorRecord errorResponse = new CustomErrorRecord(
                LocalDateTime.now(),
                ex.getMessage(),
                exchange.getRequest().getURI().toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected Mono<ResponseEntity<Object>> handleWebExchangeBindException(WebExchangeBindException ex, HttpHeaders headers, HttpStatusCode status, ServerWebExchange exchange) {
        Map<String, String> detalles = ex.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("message", "Error de validación");
        responseBody.put("details", detalles);

        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody));
    }
}
