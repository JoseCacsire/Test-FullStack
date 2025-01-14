package com.cliente.ServicioCliente.exception;

import java.time.LocalDateTime;


public record CustomErrorRecord(
        LocalDateTime datetime,
        String message,
        String details
) {
}
