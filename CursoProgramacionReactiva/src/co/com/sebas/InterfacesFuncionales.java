package co.com.sebas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class Persona{
	public int edad;
	public String nombre;

	public Persona(String nombre, int edad) {
		this.edad = edad;
		this.nombre = nombre;
	}
	
	public Persona(String nombre) {
		this.edad = nombre.length();
		this.nombre = nombre;
	}
	
	Persona(Map<String, Object> obj) {
		this.nombre = (String) obj.get("nombre");
		this.edad = (int) obj.get("edad");
	}
	
	public String toString() {
		return nombre + " -> " + edad;
	}
	
}

public class InterfacesFuncionales {
	
	public static void main(String[] args) {
		//usoPredicate();
		usoConsumer();
		//usoSupplier();
		//usoFunction();
		//usoBiFunction();
		//usoUnaryOperator();
		//usoBinaryOperator();
	}

	private static void usoBinaryOperator() {
		BinaryOperator<String> binOp = (s, t) -> s + t;
		System.out.println(binOp.apply("Hola!", " mundo"));
		
		BinaryOperator<String> reemplazar = (texto, palabra) -> texto.replace(palabra, "-_-");
		String frase = "Tres tristes tigres comian trigo en un trigal";
		String aReemplazar = "tr";
		System.out.println(reemplazar.apply(frase, aReemplazar));
		
		BinaryOperator<Double> potencia = (x, y) -> Math.pow(x, y);
		System.out.println(potencia.apply(5.0, 3.0));
		
		String cad1 = "ABC";
		String cad2 = "DEF";
		
		BinaryOperator<String> menor = BinaryOperator.minBy(String::compareToIgnoreCase);
		System.out.println(menor.apply(cad1, cad2));
		
		BinaryOperator<String> mayor = BinaryOperator.maxBy(String::compareToIgnoreCase);
		System.out.println(mayor.apply(cad1, cad2));
	}

	private static void usoUnaryOperator() {
		UnaryOperator<Integer> doble = x -> x * 2;
		System.out.println(doble.apply(5));
		
		UnaryOperator<Persona> actualizarEdad = persona -> new Persona(persona.nombre, persona.edad* 2);
		System.out.println(actualizarEdad.apply(new Persona("Juan", 20)));
		
		UnaryOperator<String> op = s -> s.toUpperCase();
		
		List<String> lista = Arrays.asList("Pedro", "Ana", "Juan", "Maria");
		System.out.println(ejecutarUnaryOp(op, lista));
		System.out.println(ejecutarUnaryOp(s -> s.toLowerCase(), lista));
		ejecutarUnaryOp(s -> s.toUpperCase() + " -> Esta es en mayusculas", lista).forEach(System.out::println);
		ejecutarUnaryOp(s -> s.toUpperCase() + " -> Esta es en mayusculas", lista).forEach(InterfacesFuncionales::imprimirSubstring);
	}

	private static List<String> ejecutarUnaryOp(UnaryOperator<String> unaryOp, List<String> lista){
		List<String> nuevaLista = new ArrayList<String>();
		lista.forEach(el -> nuevaLista.add(unaryOp.apply(el)));
		return nuevaLista;
	}
	
	private static void imprimirSubstring(String str) {
		System.out.println(str.substring(0, 3));
	}

	private static void usoBiFunction() {
		BiFunction<Integer, String, String> bifunction = (x, s) -> x + ": " + s;
		System.out.println(bifunction.apply(10, "Este es el numero..."));
		
		BiFunction<Integer, Integer, Integer> mayor = (x, y) -> x.compareTo(y) >= 0 ? x : y;
		System.out.println(mayor.apply(100, 12));
	}

	private static void usoFunction() {
		Function<String, Integer> longitud = s -> s.length();
		System.out.println(longitud.apply("Hola"));
		
		Function<Integer, Integer> doble = x -> x * 2;
		System.out.println(doble.apply(12));
		
		Function<Integer, String> cadena = x -> "Esta es la respuesta " + x;
		System.out.println(cadena.apply(1232));
		
		System.out.println(longitud.andThen(doble).apply("1234567"));
//		doble.andThen(longitud).apply(100) Error por tipos
		
		System.out.println(longitud.andThen(doble).andThen(cadena).apply("1234567"));
		
	}

	private static void usoSupplier() {
		Supplier<String> saludo = () -> "Hola!";
		System.out.println(saludo.get());
		ejecutarSupplier(saludo);
		
		Supplier<LocalDate> getFecha = () -> LocalDate.now();
		ejecutarSupplier(getFecha);
	}
	
	private static <T> void ejecutarSupplier(Supplier<T> supplier) {
		System.out.println(supplier.get());
	}
	
	private static void usoPredicate() {
        Predicate<Integer> esPositivo = x -> x >= 0;
        // <T> -> boolean
        System.out.println(esPositivo.test(10));
        Predicate<Persona> esMayor = persona -> persona.edad >= 18;
        System.out.println("Es mayor de edad: " + esMayor.test(new Persona("Juan", 20)));
        System.out.println("Es mayor de edad: " + esMayor.test(new Persona("Ricardo", 10)));

        Predicate<Integer> menorADiez = x -> x < 10;
        boolean positivoMenorADiez = esPositivo.and(menorADiez).test(15);
        System.out.println("Es Positivo y Menor a Diez: " + positivoMenorADiez);

        Predicate<Integer> menorAMenos10 = x -> x < -10;
        boolean positivoOMenorAMenos10 = esPositivo.and(menorADiez).or(menorAMenos10).test(-2);
        System.out.println("Positivo o Menor a menos -10: " + positivoOMenorAMenos10);

        System.out.println("Es positivo Negado: " + esPositivo.negate().test(10));
    }

    private static void usoConsumer() {
        Consumer<String> consumer = s -> System.out.println("Esta es el consumer de " + s);
//        consumer.accept("CUALQUIER COSA");
//
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 10);
        Consumer<Integer> imprimir = x -> System.out.println("Este es el entero: " + x);
//        lista.forEach(imprimir);
//        lista.forEach(x -> System.out.println("El cuadrado es: " + x * x));
//
//        Consumer<Map<String, Integer>> consumerMap = m -> System.out.println("Valor en el mapa" + m.get("otra"));
//        Map<String, Integer> map = new HashMap<>();
//        map.put("key", 100);
//        consumerMap.accept(map);
//
        Consumer<String> adios = s -> System.out.println(". Adi?s " + s);
        consumer.andThen(adios).andThen(consumer).accept("PRUEBA");

        pasarConsumer(lista, imprimir);
        pasarConsumer(lista, x -> System.out.println("El cuadrado es: " + x * x));
        pasarConsumer(lista, x -> System.out.println("El cubo es: " + x * x * x));
    }

    private static <T> void pasarConsumer(List<T> lista, Consumer<T> consumer) {
        for (T o: lista) {
            consumer.accept(o);
        }
    }
	
}
