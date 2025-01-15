package com.pedido.ServicioPedido.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO {


    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    private String nombreCliente;

    @NotEmpty(message = "La lista de productos no puede estar vacía")
    @Valid
    private List<ProductoPedidoDTO> productos;

    @Min(value = 0, message = "El total no puede ser negativo")
    private double total;

}
