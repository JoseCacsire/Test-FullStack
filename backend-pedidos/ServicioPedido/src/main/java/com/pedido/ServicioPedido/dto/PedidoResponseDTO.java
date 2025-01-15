package com.pedido.ServicioPedido.dto;
import com.pedido.ServicioPedido.enums.EstadoOrden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponseDTO {

    private String id;

    private String nombreCliente;

    private List<ProductoPedidoDTO> productos;

    private LocalDateTime fechaCreacion;

    private EstadoOrden estado;

    private double total;



}
