package com.producto.ServicioProducto.service.impl;

import com.producto.ServicioProducto.dto.ProductoDTO;
import com.producto.ServicioProducto.exception.ModelNotFoundException;
import com.producto.ServicioProducto.exception.NotStockException;
import com.producto.ServicioProducto.model.Producto;
import com.producto.ServicioProducto.repository.ProductoRepository;
import com.producto.ServicioProducto.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public Flux<ProductoDTO> obtenerProductos() {
        return productoRepository.findAll().map(product-> {
            return ProductoDTO.builder()
                    .id(product.getId())
                    .nombre(product.getNombre())
                    .precio(product.getPrecio())
                    .stock(product.getStock())
                    .descripcion(product.getDescripcion())
                    .build();
        });
    }

    @Override
    @Transactional
    public Mono<ProductoDTO> crearProducto(ProductoDTO productoDTO) {
        return Mono.just(productoDTO)
                .flatMap(dto-> validarProducto(productoDTO))
                .map(dto -> Producto.builder()
                        .nombre(dto.getNombre())
                        .precio(dto.getPrecio())
                        .descripcion(dto.getDescripcion())
                        .stock(dto.getStock())
                        .build()
                )
                .flatMap(producto -> productoRepository.save(producto))
                .map(producto -> ProductoDTO.builder()
                        .nombre(producto.getNombre())
                        .precio(producto.getPrecio())
                        .descripcion(producto.getDescripcion())
                        .stock(producto.getStock())
                        .build()
                )
                ;
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ProductoDTO> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.error(new ModelNotFoundException("Producto no encontrado con id: " + id))) // Maneja el caso donde no existe
                .map(producto -> ProductoDTO.builder()
                        .id(producto.getId())
                        .nombre(producto.getNombre())
                        .precio(producto.getPrecio())
                        .descripcion(producto.getDescripcion())
                        .stock(producto.getStock())
                        .build()
                );
    }

    @Transactional
    @Override
    public Mono<ProductoDTO> actualizarStock(Long productoId, int cantidadVendida) {
        return productoRepository.findById(productoId)
                .flatMap(producto -> {
                    if (producto.getStock() >= cantidadVendida) {
                        producto.setStock(producto.getStock() - cantidadVendida);
                        return productoRepository.save(producto);
                    } else {
                        return Mono.error(new NotStockException("Stock insuficiente para el producto con ID: " + productoId));
                    }
                })
                .map(producto -> ProductoDTO.builder()
                        .id(producto.getId())
                        .nombre(producto.getNombre())
                        .precio(producto.getPrecio())
                        .descripcion(producto.getDescripcion())
                        .stock(producto.getStock())
                        .build()
                );
    }

    private Mono<ProductoDTO> validarProducto(ProductoDTO dto) {
        if (dto.getPrecio() == null || dto.getPrecio().compareTo(BigDecimal.TEN) < 0) {
            return Mono.error(new IllegalArgumentException("El precio debe ser al menos 10"));
        }
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            return Mono.error(new IllegalArgumentException("El nombre no puede estar vacío"));
        }
        if (dto.getDescripcion() == null || dto.getDescripcion().isBlank()) {
            return Mono.error(new IllegalArgumentException("La descripción no puede estar vacía"));
        }
        return Mono.just(dto);
    }
}
