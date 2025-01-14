package com.producto.ServicioProducto;

import com.producto.ServicioProducto.model.Producto;
import com.producto.ServicioProducto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@SpringBootApplication
@RequiredArgsConstructor
public class ServicioProductoApplication implements CommandLineRunner {
	private final ProductoRepository productoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServicioProductoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}
}
