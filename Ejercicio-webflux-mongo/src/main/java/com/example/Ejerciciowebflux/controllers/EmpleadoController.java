package com.example.Ejerciciowebflux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ejerciciowebflux.models.Empleado;
import com.example.Ejerciciowebflux.services.EmpleadoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("empleados")
public class EmpleadoController {
	
	@Autowired
	public EmpleadoService service;
	
	@GetMapping
	public Flux getEmpleados(){
		return service.getEmpleados();
	}
	
	@PostMapping
	public Mono<Empleado> crearEmpleado(@RequestBody Empleado empleado){
		return service.crearEmpleado(empleado);
	}
	
	@PutMapping
	public Mono<Empleado> actualizarEmpleado(@RequestBody Empleado empleado){
		return service.actualizarEmpleado(empleado);
	}
	
	@DeleteMapping
	public Mono<Void> borrarEmpleado(@RequestBody Empleado empleado){
		return service.borrarEmpleado(empleado);
	}
	
	@DeleteMapping
	@RequestMapping("/{id}")
	public Mono<Void> borrarEmpleadoById(@PathVariable String id){
		return service.borrarEmpleadoById(id);
	}
}
