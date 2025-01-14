package com.producto.ServicioProducto.repository;

import com.producto.ServicioProducto.model.Producto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<Producto,Long> {
}
