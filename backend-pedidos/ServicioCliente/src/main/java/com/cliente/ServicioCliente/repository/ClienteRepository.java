package com.cliente.ServicioCliente.repository;

import com.cliente.ServicioCliente.model.Cliente;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClienteRepository extends ReactiveCrudRepository<Cliente,Long> {

    Mono<Boolean> existsByNombre(String nombre);Mono<Boolean>

    existsByCorreo(String correo);
}
