package com.pedido.ServicioPedido.controller;

import com.pedido.ServicioPedido.dto.PedidoDTO;
import com.pedido.ServicioPedido.model.Pedido;
import com.pedido.ServicioPedido.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public Flux<PedidoDTO> obtenerTodosLosPedidos() {
        return pedidoService.obtenerPedidos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PedidoDTO> crearPedido(@RequestBody PedidoDTO pedido) {
        return pedidoService.crearPedido(pedido);
    }

    // Obtener un pedido por su ID
    @GetMapping("/{id}")
    public Mono<PedidoDTO> obtenerPedido(@PathVariable Long id) {
        return pedidoService.obtenerPedido(String.valueOf(id));
    }


}
