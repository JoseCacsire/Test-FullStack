package com.pedido.ServicioPedido.model;

import com.pedido.ServicioPedido.dto.ProductoPedidoDTO;
import com.pedido.ServicioPedido.enums.EstadoOrden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "pedido")
public class Pedido {

    @Id
    private String id;
    private Long clienteId;
    private List<ProductoPedidoDTO> productos;
    private double total;

    private EstadoOrden estado;

    @Indexed
    private LocalDateTime fechaCreacion;


    }
