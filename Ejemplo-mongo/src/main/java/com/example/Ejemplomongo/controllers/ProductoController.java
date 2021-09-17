package com.example.Ejemplomongo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ejemplomongo.models.Producto;
import com.example.Ejemplomongo.services.ProductoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("productos")
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	@GetMapping
	public Flux getProductos() {
		return service.getProductos();
	}
	
	@PostMapping
	public Mono<Producto> crearProducto(@RequestBody Producto producto){
		return service.crearProducto(producto);
	}
}
