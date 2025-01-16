package com.pedido.ServicioPedido.service;

import com.pedido.ServicioPedido.dto.EstadoRequestDTO;
import com.pedido.ServicioPedido.dto.PedidoDTO;
import com.pedido.ServicioPedido.dto.PedidoResponseDTO;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PedidoService {

    Mono<PedidoResponseDTO> crearPedido(PedidoDTO pedido);
    Mono<PedidoResponseDTO> obtenerPedido(String id);
    Flux<PedidoResponseDTO> obtenerPedidos();
    Mono<Void> eliminarPedido(String id);

    @Transactional
    Mono<PedidoResponseDTO> actualizarEstadoPedido(String id, EstadoRequestDTO nuevoEstado);
}
