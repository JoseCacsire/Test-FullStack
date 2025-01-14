package com.pedido.ServicioPedido.model;

import com.pedido.ServicioPedido.dto.ProductoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pedido")
public class Pedido {

    @Id
    private String id;
    private Long clienteId;
    private List<ProductoPedido> productos;
    private double total;

}
