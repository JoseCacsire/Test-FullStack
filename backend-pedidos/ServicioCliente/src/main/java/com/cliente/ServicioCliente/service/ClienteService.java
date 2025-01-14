package com.cliente.ServicioCliente.service;

import com.cliente.ServicioCliente.dto.ClienteDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ClienteService {

    // Obtener todos los clientes
    Flux<ClienteDTO> obtenerClientes();

    // Crear un nuevo cliente
    Mono<ClienteDTO> crearCliente(ClienteDTO clienteDTO);

    // Obtener un cliente por su ID
    Mono<ClienteDTO> obtenerClientePorId(Long id);

    Mono<Boolean> existeClientePorNombre(String nombre);

    Mono<Boolean> existeClientePorCorreo(String correo);

}
