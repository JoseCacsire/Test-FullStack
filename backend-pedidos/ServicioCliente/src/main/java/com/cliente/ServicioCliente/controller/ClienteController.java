package com.cliente.ServicioCliente.controller;

import com.cliente.ServicioCliente.dto.ClienteDTO;
import com.cliente.ServicioCliente.service.ClienteService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private  ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public Flux<ClienteDTO> obtenerTodosLosClientes() {
        return clienteService.obtenerClientes();
    }

    @PostMapping
    public Mono<ClienteDTO> crearCliente(@RequestBody  ClienteDTO clienteDTO) {
        return clienteService.crearCliente(clienteDTO);
    }

    @GetMapping("/{id}")
    public Mono<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public Mono<Boolean> existeClientePorNombre(@PathVariable String nombre) {
        return clienteService.existeClientePorNombre(nombre);
    }

    @GetMapping("/correo/{correo}")
    public Mono<Boolean> existeClientePorCorreo(@PathVariable String correo) {
        return clienteService.existeClientePorCorreo(correo);
    }

}
