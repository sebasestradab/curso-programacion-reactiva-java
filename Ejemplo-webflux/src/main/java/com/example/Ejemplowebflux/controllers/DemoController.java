package com.example.Ejemplowebflux.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("demo")
public class DemoController {
//	@GetMapping
//	public Flux demoGet() {
//		return Flux.fromIterable();
//	}
	
	@GetMapping
	public Mono demoGetMono() {
		return Mono.just("Hello");
	}
}
