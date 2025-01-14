package com.pedido.ServicioPedido;

import com.pedido.ServicioPedido.model.Pedido;
import com.pedido.ServicioPedido.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ServicioPedidoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ServicioPedidoApplication.class, args);
	}

	private final PedidoRepository pedidoRepository;

	@Override
	public void run(String... args) throws Exception {

		log.info("Iniciando ServicioPedidoApplication");
	}
}