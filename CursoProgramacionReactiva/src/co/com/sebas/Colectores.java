package co.com.sebas;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Colectores {
	public static void main(String[] args) {
		//colectoresBasicos();
		//colectoresColeccion();
		colectoresAgrupamiento();
	}
	
	private static void colectoresBasicos() {
		Random random = new Random();
		List<Integer> numeros = random.ints(0, 1000).limit(20).boxed().collect(Collectors.toList());
		
		System.out.println(numeros);
		IntSummaryStatistics resumen = numeros.stream().filter(x -> x < 500).collect(Collectors.summarizingInt(Integer::intValue)); // summarizingLong, summarizingDouble, summarizingInt
		System.out.println("------------------------- Resultados --------------------------");
		System.out.println("Mayor: " + resumen.getMax());
		System.out.println("Menor: " + resumen.getMin());
		System.out.println("Promedio: " + resumen.getAverage());
		System.out.println("Suma: " + resumen.getSum());
		System.out.println("Conteo: " + resumen.getCount());
		
		List<String> cadenas = Arrays.asList("Cad 1", "Cad 2");
		cadenas.stream().collect(Collectors.joining());
	}
	
	private static void colectoresColeccion() {
		Empleado juan = new Empleado("Juan", 2000, "Sistemas");
		Empleado lucia = new Empleado("Lucia", 3000, "Ventas");
		Empleado ana = new Empleado("Ana", 3500, "Compras");
		Empleado pedro = new Empleado("Pedro", 4000, "Compras");
		
		List<Empleado> empleados = Arrays.asList(juan, lucia, ana, pedro, lucia, pedro, ana, juan);
		System.out.println(empleados);
		
		System.out.println("------------------------- Collectors --------------------------");
		System.out.println(empleados.stream().filter(e -> e.getSalario() > 3000).collect(Collectors.toList()));
		System.out.println(empleados.stream().filter(e -> e.getSalario() > 3000).collect(Collectors.toSet()));
		
		System.out.println(empleados.stream().distinct().filter(e -> e.getSalario() > 3000).collect(Collectors.toMap(Empleado::getNombre, Empleado::getSalario)));
		
	}
	
	private static void colectoresAgrupamiento() {
		Empleado juan = new Empleado("Juan", 2000, "Sistemas");
		Empleado lucia = new Empleado("Lucia", 3000, "Ventas");
		Empleado ana = new Empleado("Ana", 3500, "Compras");
		Empleado pedro = new Empleado("Pedro", 4000, "Compras");
		Empleado pablo = new Empleado("Pablo", 4000, "Ventas");
		
		List<Empleado> empleados = Arrays.asList(juan, lucia, ana, pedro, pablo);
		// partitioningBy --> Genera una mapa copn 2 claves, con true las que cumplen la condicion y false la que no
		System.out.println(empleados.stream().collect(Collectors.partitioningBy(e -> e.getSalario() > 3000)));
		
		// groupingBy es mas general que partitioningBy
		System.out.println(empleados.stream().collect(Collectors.groupingBy(e -> e.getSalario() > 3000)));
		System.out.println(empleados.stream().collect(Collectors.groupingBy(e -> e.getSalario())));
		System.out.println(empleados.stream().collect(Collectors.groupingBy(Empleado::getArea)));
		
		System.out.println(empleados.stream().collect(Collectors.partitioningBy(e -> e.getSalario() > 3000, Collectors.groupingBy(Empleado::getArea))));
		
		System.out.println(empleados.stream().collect(Collectors.partitioningBy(e -> e.getSalario() > 3000, Collectors.groupingBy(Empleado::getArea, Collectors.counting()))));
	}
}

class Empleado{
	String nombre;
	double salario;
	String area;
	
	@Override
	public String toString() {
		return nombre + "(" + salario + "," + area + ")";
	}
	
	public Empleado(String nombre, double salario, String area) {
		super();
		this.nombre = nombre;
		this.salario = salario;
		this.area = area;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
}