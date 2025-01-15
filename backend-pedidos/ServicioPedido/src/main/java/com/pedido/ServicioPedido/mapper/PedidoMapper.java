package com.pedido.ServicioPedido.mapper;

import com.pedido.ServicioPedido.dto.PedidoDTO;
import com.pedido.ServicioPedido.dto.PedidoResponseDTO;
import com.pedido.ServicioPedido.dto.Producto;
import com.pedido.ServicioPedido.enums.EstadoOrden;
import com.pedido.ServicioPedido.model.Pedido;
import com.pedido.ServicioPedido.utils.PedidoUtils;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoMapper {

    public static PedidoResponseDTO toDTO(Pedido pedido) {
        return PedidoResponseDTO.builder()
                .id(pedido.getId())
                .productos(pedido.getProductos())
                .fechaCreacion(pedido.getFechaCreacion())
                .estado(pedido.getEstado())
                .total(pedido.getTotal())
                .estado(pedido.getEstado())
                .build();
    }

    public static Pedido toEntity(PedidoDTO pedidoDTO, List<Producto> productos) {
        return Pedido.builder()
                .clienteId(pedidoDTO.getClienteId())
                .productos(pedidoDTO.getProductos())
                .total(pedidoDTO.getTotal())
                .fechaCreacion(LocalDateTime.now())
                .estado(EstadoOrden.PENDIENTE)
                .productos(pedidoDTO.getProductos())
                .total(PedidoUtils.calcularTotal(productos, pedidoDTO.getProductos()))
                .build();
    }




}
