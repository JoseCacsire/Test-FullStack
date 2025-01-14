package com.producto.ServicioProducto.controller;

import com.producto.ServicioProducto.dto.ProductoDTO;
import com.producto.ServicioProducto.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producto")
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    public Flux<ProductoDTO> findAll() {
        return productoService.obtenerProductos();
    }

    @GetMapping("/{id}")
    public Mono<ProductoDTO> findById(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping
    public Mono<ProductoDTO> create(@RequestBody ProductoDTO productoDTO) {
        return productoService.crearProducto(productoDTO);
    }

    // Endpoint para actualizar el stock de un producto
    @PatchMapping("/{id}/stock")
    public Mono<ResponseEntity<ProductoDTO>> actualizarStock(@PathVariable Long id, @RequestBody int cantidad) {
        return productoService.actualizarStock(id, cantidad)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }



}
