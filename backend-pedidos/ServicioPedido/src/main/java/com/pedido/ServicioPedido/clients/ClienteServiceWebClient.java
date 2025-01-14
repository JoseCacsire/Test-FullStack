package com.pedido.ServicioPedido.clients;

import com.pedido.ServicioPedido.dto.Cliente;
import com.pedido.ServicioPedido.exception.ModelNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ClienteServiceWebClient {
    private final WebClient webClient;

    public ClienteServiceWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    public Mono<Cliente> obtenerClientePorId(Long id) {
        log.info("plplpl");
        return webClient.get()
                .uri("/clientes/{id}", id)
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND, clientResponse ->
                        Mono.error(new ModelNotFoundException("Cliente no encontrado con ID: " + id)))
                .bodyToMono(Cliente.class)
                .doOnNext(cliente -> log.info("Cliente encontrado: {}", cliente))
                .doOnError(error -> log.error("Error al obtener cliente: {}", error.getMessage()));
    }
}
