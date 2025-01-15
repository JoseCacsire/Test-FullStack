package com.pedido.ServicioPedido.service.impl;

import com.pedido.ServicioPedido.clients.ClienteServiceWebClient;
import com.pedido.ServicioPedido.clients.ProductoServiceWebClient;
import com.pedido.ServicioPedido.dto.*;
import com.pedido.ServicioPedido.exception.ModelNotFoundException;
import com.pedido.ServicioPedido.mapper.PedidoMapper;
import com.pedido.ServicioPedido.model.Pedido;
import com.pedido.ServicioPedido.repository.PedidoRepository;
import com.pedido.ServicioPedido.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteServiceWebClient clienteService;
    private final ProductoServiceWebClient productoService;

    @Override
    @Transactional(readOnly = true)
    public Flux<PedidoResponseDTO> obtenerPedidos() {
        return pedidoRepository.findAll()
                .flatMap(this::mapPedidoToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PedidoResponseDTO> obtenerPedido(String id) {
        return pedidoRepository.findById(id)
                .flatMap(this::mapPedidoToDTO);
    }

    @Transactional
    @Override
    public Mono<PedidoResponseDTO> crearPedido(PedidoDTO pedidoDTO) {
        log.info("Creando pedido para cliente con ID: {}", pedidoDTO.getClienteId());

        return Mono.zip(
                clienteService.obtenerClientePorId(pedidoDTO.getClienteId()),
                cargarProductosConDetalles(pedidoDTO.getProductos())
        ).flatMap(tuple -> {
            Cliente cliente = tuple.getT1();
            List<Producto> productos = tuple.getT2();

            Pedido pedido = PedidoMapper.toEntity(pedidoDTO, productos);
            return guardarPedidoYActualizarStock(pedidoDTO, pedido, cliente);
        });
    }

    @Transactional
    @Override
    public Mono<PedidoResponseDTO> actualizarEstadoPedido(String id, EstadoRequestDTO nuevoEstado) {
        return pedidoRepository.findById(id)
                .switchIfEmpty(Mono.error(new ModelNotFoundException("Pedido no encontrado con id : " + id)))
                .flatMap(pedido -> {
                    pedido.setEstado(nuevoEstado.getEstado());
                    return pedidoRepository.save(pedido);
                })
                .flatMap(this::mapPedidoToDTO);
    }


    private Mono<PedidoResponseDTO> guardarPedidoYActualizarStock(PedidoDTO pedidoDTO, Pedido pedido, Cliente cliente) {
        return pedidoRepository.save(pedido)
                .flatMap(savedPedido -> actualizarStockProductos(pedidoDTO.getProductos())
                        .then(Mono.just(PedidoMapper.toDTO(savedPedido))
                                .doOnNext(dto -> dto.setNombreCliente(cliente.getNombre()))));
    }

    private Flux<Void> actualizarStockProductos(List<ProductoPedidoDTO> productosPedido) {
        return Flux.fromIterable(productosPedido)
                .flatMap(producto -> productoService.actualizarStock(producto.getProductoId(), producto.getCantidad()));
    }

    private Mono<PedidoResponseDTO> mapPedidoToDTO(Pedido pedido) {
        return Mono.zip(
                clienteService.obtenerClientePorId(pedido.getClienteId()),
                cargarProductosConDetalles(pedido.getProductos())
        ).map(tuple -> {
            Cliente cliente = tuple.getT1();
            PedidoResponseDTO dto = PedidoMapper.toDTO(pedido);
            dto.setNombreCliente(cliente.getNombre());
            return dto;
        });
    }

    private Mono<List<Producto>> cargarProductosConDetalles(List<ProductoPedidoDTO> productosPedido) {
        return Flux.fromIterable(productosPedido)
                .flatMap(productoPedido -> productoService.findById(productoPedido.getProductoId())
                        .doOnNext(producto -> {
                            productoPedido.setPrecio(producto.getPrecio());
                            productoPedido.setNombreProducto(producto.getNombre());
                        }))
                .collectList();
    }


}