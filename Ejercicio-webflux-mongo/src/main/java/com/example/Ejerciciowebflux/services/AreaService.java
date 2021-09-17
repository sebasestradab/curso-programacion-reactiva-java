package com.example.Ejerciciowebflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ejerciciowebflux.repositories.AreaRepository;

import reactor.core.publisher.Flux;

@Service
public class AreaService {

	@Autowired
	private AreaRepository repository;
	
	public Flux getAreas() {
		return repository.findAll();
	}
}
