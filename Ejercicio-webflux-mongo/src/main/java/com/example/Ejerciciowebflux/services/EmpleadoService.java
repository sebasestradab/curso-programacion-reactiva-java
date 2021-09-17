package com.example.Ejerciciowebflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ejerciciowebflux.models.Empleado;
import com.example.Ejerciciowebflux.repositories.EmpleadoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmpleadoService {
	
	@Autowired
	public EmpleadoRepository repository;
	
	public Flux getEmpleados(){
		return repository.findAll();
	}
	
	public Mono<Empleado> crearEmpleado(Empleado empleado){
		return repository.save(empleado);
	}
	
	public Mono<Empleado> actualizarEmpleado(Empleado empleado){
		return repository.save(empleado);
	}
	
	public Mono<Void> borrarEmpleado(Empleado empleado){
		return repository.delete(empleado);
	}
	
	public Mono<Void> borrarEmpleadoById(String id){
		return repository.deleteById(id);
	}
}
