package com.pedido.ServicioPedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoPedido {

    private Long productoId;
    private Double precio; // Precio del producto
    private int cantidad; // Cantidad del producto solicitada



}
