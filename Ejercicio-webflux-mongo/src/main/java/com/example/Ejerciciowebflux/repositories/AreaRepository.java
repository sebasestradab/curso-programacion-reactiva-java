package com.example.Ejerciciowebflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.Ejerciciowebflux.models.Area;

public interface AreaRepository extends ReactiveMongoRepository<Area, String> {

}
