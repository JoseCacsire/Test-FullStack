package com.cliente.ServicioCliente.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="cliente")
public class Cliente {

    @Id
    private Long id;
    private String nombre;
    private String correo;
}
