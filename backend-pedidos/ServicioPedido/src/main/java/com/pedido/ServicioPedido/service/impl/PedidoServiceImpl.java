package com.pedido.ServicioPedido.service.impl;

import com.pedido.ServicioPedido.clients.ClienteServiceWebClient;
import com.pedido.ServicioPedido.clients.ProductoServiceWebClient;
import com.pedido.ServicioPedido.dto.Cliente;
import com.pedido.ServicioPedido.dto.PedidoDTO;
import com.pedido.ServicioPedido.dto.Producto;
import com.pedido.ServicioPedido.dto.ProductoPedido;
import com.pedido.ServicioPedido.model.Pedido;
import com.pedido.ServicioPedido.repository.PedidoRepository;
import com.pedido.ServicioPedido.service.PedidoService;
import com.pedido.ServicioPedido.utils.PedidoUtils;
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
    public Flux<PedidoDTO> obtenerPedidos() {
        return pedidoRepository.findAll()
                .map(this::convertToDTO);
    }

    @Transactional
    @Override
    public Mono<PedidoDTO> crearPedido(PedidoDTO pedidoDTO) {

        log.info("pepepepepe");
        Mono<Cliente> clienteMono = clienteService.obtenerClientePorId(pedidoDTO.getClienteId())
                .doOnNext(cliente -> log.info("Cliente: {}", cliente));

        Flux<Producto> productosFlux = Flux.fromIterable(pedidoDTO.getProductos())
                .flatMap(productoPedido -> productoService.findById(productoPedido.getProductoId())
                        .doOnNext(producto -> {
                            log.info("Producto: {}", producto);
                            productoPedido.setPrecio(producto.getPrecio()); // Establecer el precio
                        }));

        return Mono.zip(clienteMono, productosFlux.collectList())
                .flatMap(result -> {
                    Cliente cliente = result.getT1();
                    List<Producto> productos = result.getT2();

                    Pedido pedido = convertToEntity(pedidoDTO);
                    pedido.setClienteId(cliente.getId());
                    pedido.setProductos(pedidoDTO.getProductos());
                    pedido.setTotal(PedidoUtils.calcularTotal(productos, pedidoDTO.getProductos()));;

                    return pedidoRepository.save(pedido)
                            .flatMap(savedPedido -> {
                                Flux<Void> stockUpdates = Flux.fromIterable(pedidoDTO.getProductos())
                                        .flatMap(productoPedido -> productoService.actualizarStock(productoPedido.getProductoId(), productoPedido.getCantidad()));

                                return stockUpdates.then(Mono.just(convertToDTO(savedPedido)));
                            });
                });

    }

    @Override
    public Mono<PedidoDTO> obtenerPedido(String id) {
        return pedidoRepository.findById(id)
                .map(this::convertToDTO);  // Convertir a PedidoDTO
    }

    private PedidoDTO convertToDTO(Pedido pedido) {
        return new PedidoDTO(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getProductos(),
                pedido.getTotal()
        );
    }

    private Pedido convertToEntity(PedidoDTO pedidoDTO) {
        return new Pedido(
                pedidoDTO.getId(),
                pedidoDTO.getClienteId(),
                pedidoDTO.getProductos(),
                pedidoDTO.getTotal()
        );
    }
}