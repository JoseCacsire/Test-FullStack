package com.pedido.ServicioPedido.enums;

import com.pedido.ServicioPedido.exception.ModelNotFoundException;

public enum EstadoOrden {

    PENDIENTE("pendiente"),
    CANCELADO("cancelado"),
    ENTREGADO("entregado");

    private final String estado;

    EstadoOrden(String estado) {
        this.estado = estado;
    }

    public String getValor() {
        return estado;
    }

    public static EstadoOrden fromValor(String valor) {
        for (EstadoOrden estado : values()) {
            if (estado.getValor().equalsIgnoreCase(valor)) {
                return estado;
            }
        }
        throw new ModelNotFoundException("Estado no válido: " + valor);
    }


}
