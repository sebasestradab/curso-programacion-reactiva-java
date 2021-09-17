package com.example.Ejerciciowebflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.Ejerciciowebflux.models.Empleado;

public interface EmpleadoRepository extends ReactiveMongoRepository<Empleado, String> {

}
