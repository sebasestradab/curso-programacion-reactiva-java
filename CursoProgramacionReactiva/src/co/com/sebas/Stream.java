package co.com.sebas;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Stream {
	static List<String> cadenas = Arrays.asList("Cadena 1", "Cadena 2", "Cadena 3123", "    ");
	static List<Integer> numeros = Arrays.asList(1, 4, 6, 9, 21, 55, 67, 999);
	static List<Persona> personas = Arrays.asList(
			new Persona("Sebas", 33),
			new Persona("Maria", 28),
			new Persona("Samuel", 1)
	);
	
	public static void main(String[] args) {
//		for (String cadena : lista) {
//			System.out.println(cadena);
//		}
//		
//		lista.stream().forEach(s -> System.out.println(s));
		
		//usoFilter();
//		usoMap();
//		usoReduce();
		usoMatch();
	}
	
	private static void usoMatch() {
		Predicate<String> condicion = s -> s.trim().length() <= 0;
//		boolean hayCadenaVacia = cadenas.stream().anyMatch(s -> s.length() <= 0);
		boolean hayCadenaVacia = cadenas.stream().anyMatch(condicion);
		System.out.println(hayCadenaVacia);
		
		// noneMatch
		boolean noHayMenores = personas.stream().noneMatch(p -> p.edad < 18);
		System.out.println(noHayMenores);
		
		// allMatch
		boolean sonMayores = personas.stream().allMatch(p -> p.edad >= 18);
		System.out.println(sonMayores);
		
		boolean todosCumplen = numeros.stream().allMatch(x -> x > 0);
		System.out.println(todosCumplen);
		
		boolean hayMenores = cadenas.stream().map(s -> convertirMap(s)).map(Persona::new).filter(p -> p.nombre.trim().length() > 0)
				.anyMatch(p -> p.edad < 3);
		System.out.println(hayMenores);
	}
	
	private static Map<String, Object> convertirMap(String cad){
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("nombre", cad);
		mapa.put("edad", cad.length());
		return mapa;
	}
	
	private static void usoReduce() {
//		int total = 0;
//		
//		for (Integer numero : numeros) {
//			total += numero;
//		}
//		
//		System.out.println(total);
		
		// Con stream() ------->
		Optional<Integer> total = numeros.stream().reduce((acum, numero) -> acum + numero);
		total.ifPresent(n -> System.out.println(n));
		
		cadenas.stream().reduce((acum, cad) -> acum + " <-> " + cad).ifPresent(System.out::println);
		
		cadenas.stream().map(String::length)
						.filter(x -> x <= 8)
						.reduce((acum, len) -> acum + len).ifPresent(s -> System.out.println(s));
	}

	private static void usoMap() {
		List<Integer> longitudes = cadenas.stream().map(String::length).collect(Collectors.toList());	// String::length === s -> s.length()
		imprimir(longitudes);
		
		List<Integer> edades = personas.stream().map(p -> p.edad).collect(Collectors.toList());
		imprimir(edades);
		
		List<Persona> nuevasPersonas = cadenas.stream().map(s -> new Persona(s, s.length())).collect(Collectors.toList());
		imprimir(nuevasPersonas);
		
		List<Persona> nuevasPersonas2 = cadenas.stream().map(Persona::new).collect(Collectors.toList());
		imprimir(nuevasPersonas2);
		
		List<String> otrasCadenas = cadenas.stream().map(s -> s + " ----> " + s.length()).collect(Collectors.toList());
		imprimir(otrasCadenas);
	}

	public static void usoFilter() {
		List<String> nueva = cadenas.stream().filter(s -> s.contains("Cad")).collect(Collectors.toList());
		imprimir(nueva);
		
		imprimir(numeros.stream().filter(n -> n > 30).collect(Collectors.toList()));
		
	}
	
	public static <T> void imprimir(List<T> lista) {
		System.out.println(lista);
	}
}

/*class Persona{
	public int edad;
	public String nombre;

	public Persona(String nombre, int edad) {
		this.edad = edad;
		this.nombre = nombre;
	}

	public String toString() {
		return nombre + " -> " + edad;
	}
	
}*/


