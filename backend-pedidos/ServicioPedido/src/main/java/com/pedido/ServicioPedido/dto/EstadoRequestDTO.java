package com.pedido.ServicioPedido.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pedido.ServicioPedido.enums.EstadoOrden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoRequestDTO {

    private EstadoOrden estado;

}
