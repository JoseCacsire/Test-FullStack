package com.pedido.ServicioPedido.repository;

import com.pedido.ServicioPedido.model.Pedido;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PedidoRepository extends ReactiveMongoRepository<Pedido, String> {
}
