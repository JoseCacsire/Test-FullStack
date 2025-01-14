package com.pedido.ServicioPedido.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private String id;
    private Long clienteId; // Referencia al cliente
    private List<ProductoPedido> productos; // Lista de productos con cantidades
    private double total;

}
