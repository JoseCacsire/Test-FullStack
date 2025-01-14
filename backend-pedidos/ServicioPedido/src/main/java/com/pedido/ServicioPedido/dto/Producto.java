package com.pedido.ServicioPedido.dto;

import lombok.Data;

@Data
public class Producto {

    private Long id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private Integer stock;

}
