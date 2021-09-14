package co.com.sebas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class OrdenSuperior {
	Function<String, String> mayusculas = s -> s.toUpperCase();
	Function<String, String> minusculas = s -> s.toLowerCase();
	
	public Function<String, String> devolver(String cad){
		//return mayusculas;
		
		return (s) -> s + " --> " + cad;	
	}
	
	public void filtrar(List<String> lista, Predicate<String> criterio, Consumer<String> consumer) {
//		List<String> filtrados = new ArrayList<String>();
//		
//		for (String s : lista) {
//			if (criterio.test(s)) {
//				filtrados.add(s);
//			}
//		}
		
//		System.out.println(filtrados);
		
		lista.stream().filter(criterio).forEach(consumer);
		lista.stream().filter(s -> s.length() <= 10).forEach(System.out::println);
	}
}
