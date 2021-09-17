package com.example.Ejemplomongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ejemplomongo.models.Producto;
import com.example.Ejemplomongo.repositories.ProductoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {
	@Autowired
	private ProductoRepository repository;
	
	public Flux getProductos() {
		return repository.findAll();
	}
	
	public Mono<Producto> crearProducto(Producto producto) {
		return repository.save(producto);
	}
}
