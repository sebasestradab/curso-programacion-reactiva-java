package com.example.Ejemplowebflux.controllers;

import org.springframework.http.MediaType;
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
	
	@GetMapping("/numero")
	public Integer numero() {
		return 5;
	}
	
	@GetMapping("/numero-mono")
	public Mono<Integer> numeroMono(){
		return Mono.just(1000);
	}
	
	@GetMapping("/numeros")
	public Integer[] numeros() throws InterruptedException {
		Integer[] numeros = new Integer[10];
		for (int i = 0; i < numeros.length; i++) {
			numeros[i] = i + 1;
			Thread.sleep(1000);
		}
		return numeros;
	}
	
	@GetMapping(value = "/numeros-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Integer> numerosFlux(){
		Flux<Integer> flux = Flux.create(sink -> {
			for (int i = 0; i < 100; i++) {
				sink.next(i + 1);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					
				}
			}
			sink.complete();
		});
		
		return flux;
	}
}
