package com.pedido.ServicioPedido.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoPedidoDTO {

    @NotNull(message = "El id del producto es requerido")
    private Long productoId;

    private Double precio;

    private String nombreProducto;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

}
