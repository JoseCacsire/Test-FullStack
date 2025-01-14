package com.producto.ServicioProducto.service;

import com.producto.ServicioProducto.dto.ProductoDTO;
import com.producto.ServicioProducto.model.Producto;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductoService {
    Flux<ProductoDTO> obtenerProductos();
    Mono<ProductoDTO> crearProducto(ProductoDTO producto);
    Mono<ProductoDTO> obtenerProductoPorId(Long id);

    Mono<ProductoDTO> actualizarStock(Long productoId, int cantidadVendida);
}
