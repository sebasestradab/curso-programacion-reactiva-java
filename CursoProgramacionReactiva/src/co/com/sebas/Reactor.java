package co.com.sebas;

import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Reactor {
	public static void main(String[] args) {
//		Mono<String> mono = Mono.just("String");
//		Mono.just(new Persona("Nombre", 12)).subscribe(System.out::println);
//		
//		mono.subscribe(x -> System.out.println("Esto tiene el Mono: " + x));
//		Mono.just(Arrays.asList("", "", "")).subscribe(s -> System.out.println("Esta es la lista en un Mono: " + s));
//		Flux.fromIterable(Arrays.asList("String 1", "String 2", "String 3")).subscribe(x -> System.out.println("Estos son los datos en el Flux: " + x));
		
		List<String> datos = Arrays.asList("Juan", "Pedro", "Ana", "Lucia");
		Flux<String> flux = Flux.fromIterable(datos);
		Flux<String> flux2 = flux.map(s -> s + " - " + s.length());
		
		flux2.subscribe(System.out::println);
		flux.subscribe(System.out::println);
		
		Flux<Persona> flux3 = flux.map(s -> new Persona(s, s.length()));
		flux3.subscribe(System.out::println);
		
		System.out.println("\n-------------------------------------");
		flux.map(s -> new Persona(s, s.length())).filter(p -> p.edad > 3).groupBy(p -> p.nombre).flatMap(f -> f.collectList()).subscribe(System.out::println);
		
		Flux.merge(flux, flux2).filter(s -> s.contains("Juan")).map(s -> "Esto contiene la palabra: " + s).subscribe(System.out::println);
		System.out.println("\n-------------------------------------");
		Flux.zip(flux, flux2, (s1, s2) -> String.format("Flux 1: %s - Flux 2: %s", s1, s2)).subscribe(System.out::println);
		System.out.println("\n-------------------------------------");
		Flux<String> flux4 = Flux.fromIterable(Arrays.asList("Cad 1", "Cad 2"));
		Flux.zip(flux, flux4, (s1, s2) -> String.format("Flux 1: %s - Flux 2: %s", s1, s2)).subscribe(System.out::println); // Genera un flux con el menor tamaño
		
		Flux.zip(flux, flux4, (s1, s2) -> new Persona(s1, s2.length())).subscribe(System.out::println);
		
		
	}
}
