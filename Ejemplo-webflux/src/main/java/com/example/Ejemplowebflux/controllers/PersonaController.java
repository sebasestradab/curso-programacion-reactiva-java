package com.example.Ejemplowebflux.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ejemplowebflux.entities.Persona;
import com.example.Ejemplowebflux.services.PersonaService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("personas")
public class PersonaController {
	@Autowired
	private PersonaService service;
	
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Persona> listarPersonas(){
		return Flux.create(sink -> {
			List<Persona> personas = service.getPersonas();
			for (Persona persona : personas) {
				sink.next(persona);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {}
			}
			
			sink.complete();
		});
	}
}
