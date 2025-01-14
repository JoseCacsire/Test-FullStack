package com.cliente.ServicioCliente.service.impl;

import com.cliente.ServicioCliente.dto.ClienteDTO;

import com.cliente.ServicioCliente.exception.ModelNotFoundException;
import com.cliente.ServicioCliente.model.Cliente;
import com.cliente.ServicioCliente.repository.ClienteRepository;
import com.cliente.ServicioCliente.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ClienteDTO> obtenerClientes() {
        // Obtener todos los clientes de la base de datos
        return clienteRepository.findAll()
                .map(this::convertirAClienteDTO);
    }

    @Override
    @Transactional
    public Mono<ClienteDTO> crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(null, clienteDTO.getNombre(), clienteDTO.getCorreo());
        return clienteRepository.save(cliente)
                .map(this::convertirAClienteDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ClienteDTO> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .switchIfEmpty(Mono.error(new ModelNotFoundException("Cliente no encontrado con id: " + id)))
                .map(this::convertirAClienteDTO);
    }

    private ClienteDTO convertirAClienteDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getCorreo());
    }


    @Override
    public Mono<Boolean> existeClientePorNombre(String nombre) {
        return clienteRepository.existsByNombre(nombre);
    }

    @Override
    public Mono<Boolean> existeClientePorCorreo(String correo) {
        return clienteRepository.existsByCorreo(correo);
    }

}