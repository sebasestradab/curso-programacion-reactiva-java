package com.example.Ejemplomongo.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.Ejemplomongo.models.Producto;

public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {

}
