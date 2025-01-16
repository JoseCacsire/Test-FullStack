package com.pedido.ServicioPedido.controller;

import com.pedido.ServicioPedido.dto.EstadoRequestDTO;
import com.pedido.ServicioPedido.dto.PedidoDTO;
import com.pedido.ServicioPedido.dto.PedidoResponseDTO;
import com.pedido.ServicioPedido.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@RestController
@RequestMapping("/pedido")
@Validated
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public Flux<PedidoResponseDTO> obtenerPedidos() {
        return pedidoService.obtenerPedidos()
                .sort(Comparator.comparing(PedidoResponseDTO::getFechaCreacion).reversed());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PedidoResponseDTO> crearPedido( @RequestBody @Valid PedidoDTO pedido) {
        return pedidoService.crearPedido(pedido);
    }

    @GetMapping("/{id}")
    public Mono<PedidoResponseDTO> obtenerPedido(@PathVariable String id) {
        return pedidoService.obtenerPedido(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado")));
    }

    @PatchMapping("/{id}/estado")
    public Mono<ResponseEntity<PedidoResponseDTO>> actualizarEstadoPedido(
            @PathVariable String id,
            @RequestBody EstadoRequestDTO nuevoEstado) {
        return pedidoService.actualizarEstadoPedido(id, nuevoEstado)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarPedido(@PathVariable String id) {
        return pedidoService.eliminarPedido(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }



}
