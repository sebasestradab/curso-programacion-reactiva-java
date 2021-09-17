package com.example.Ejerciciowebflux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ejerciciowebflux.services.AreaService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("areas")
public class AreaController {
	
	@Autowired
	private AreaService service;
	
	@GetMapping
	public Flux getAreas() {
		return service.getAreas();
	}
	
	
	
}
