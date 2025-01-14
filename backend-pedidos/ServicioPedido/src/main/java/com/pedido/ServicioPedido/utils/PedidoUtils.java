package com.pedido.ServicioPedido.utils;

import com.pedido.ServicioPedido.dto.Producto;
import com.pedido.ServicioPedido.dto.ProductoPedido;

import java.util.List;

public class PedidoUtils {

    public static Double calcularTotal(List<Producto> productos, List<ProductoPedido> productosPedido) {
        return productos.stream()
                .mapToDouble(producto -> {
                    ProductoPedido productoPedido = productosPedido.stream()
                            .filter(pp -> pp.getProductoId().equals(producto.getId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado en el pedido"));
                    return producto.getPrecio() * productoPedido.getCantidad();
                })
                .sum();
    }
}