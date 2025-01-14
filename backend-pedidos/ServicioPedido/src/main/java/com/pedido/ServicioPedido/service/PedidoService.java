package com.pedido.ServicioPedido.service;

import com.pedido.ServicioPedido.dto.PedidoDTO;
import com.pedido.ServicioPedido.model.Pedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PedidoService {

    Mono<PedidoDTO> crearPedido(PedidoDTO pedido);
    Mono<PedidoDTO> obtenerPedido(String id);
    Flux<PedidoDTO> obtenerPedidos();

}
