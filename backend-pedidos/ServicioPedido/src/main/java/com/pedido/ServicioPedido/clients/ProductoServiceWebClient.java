package com.pedido.ServicioPedido.clients;

import com.pedido.ServicioPedido.dto.Producto;
import com.pedido.ServicioPedido.exception.ModelNotFoundException;
import com.pedido.ServicioPedido.exception.NotStockException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceWebClient {

    private final WebClient webClient;

    public ProductoServiceWebClient(WebClient.Builder webClientBuilder) {
        // Configura la base URL del microservicio de productos
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    public Mono<Void> actualizarStock(Long id, int cantidad) {
        return webClient.patch()
                .uri("/producto/{id}/stock", id)
                .bodyValue(cantidad)  // El cuerpo de la solicitud puede ser la cantidad a actualizar
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND, clientResponse ->
                        Mono.error(new ModelNotFoundException("Producto no encontrado con ID: " + id)))  // Manejo de error si el producto no se encuentra
                .onStatus(status -> status == HttpStatus.BAD_REQUEST, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(errorMessage ->
                                Mono.error(new NotStockException(errorMessage))))  // Manejo de error si no hay suficiente stock
                .bodyToMono(Void.class);
    }

    public Mono<Producto> findById(Long id) {
        return webClient.get()
                .uri("/producto/{id}", id)
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND, clientResponse ->
                        Mono.error(new ModelNotFoundException("Producto no encontrado con ID: " + id))) // Captura solo el error 404
                .bodyToMono(Producto.class);
    }

    public Flux<Producto> findAll(List<Long> ids) {
        String idsParam = ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/producto")
                        .queryParam("ids", idsParam)
                        .build())
                .retrieve()
                .bodyToFlux(Producto.class);
    }
}
