package co.com.sebas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//calculadora();
		//diccionarioFrase();
		//listaCalificaciones();
		//asignaturasNotas();
		inmobiliara(120000);
	}
	
	// Punto 4 y 5
	private static void asignaturasNotas() {
		Map<String, List<Double>> notas = new HashMap<String, List<Double>>();
		notas.put("Matematicas", Arrays.asList(1.2, 4.5, 3.0, 4.9, 4.5));
		notas.put("Quimica", Arrays.asList(2.5,3.2, 4.0, 2.5, 1.9));
		notas.put("Religion", Arrays.asList(3.1, 3.5, 3.9, 4.9, 3.5));
		
		// Punto 4
		System.out.println("---------------------- Funcional - Punto 4 ------------------------");
		notas.forEach((k, v) -> System.out.println(k.toUpperCase() + " || " + v.stream().reduce((x, t) -> x = x + t).get() / v.size()));
		System.out.println("-------------------------------------------------------------------\n");
		
		// Punto 5
		System.out.println("---------------------- Funcional - Punto 5 ------------------------");
		//notas.forEach((k, v) -> System.out.println(v.stream().collect(Collectors.summarizingDouble(Double::doubleValue)).getAverage())); 
		notas.forEach((k, v) -> {
			double nota = v.stream().collect(Collectors.summarizingDouble(Double::doubleValue)).getAverage();
			if(nota >= 3.0) {
				System.out.println(k.toUpperCase() + " || " + nota);
			}
		});
		System.out.println("-------------------------------------------------------------------\n");
		
		/*
		Map<String, Double> calificaciones = new HashMap<String, Double>();
		Map<String, Double> aprobadas = new HashMap<String, Double>();
		for(Map.Entry<String, List<Double>> entry : notas. entrySet()) {
			double promedio = 0;
			for (Double nota : entry.getValue()) {
				promedio += nota;
			}
			
			System.out.println(entry.getKey().toUpperCase() + " || " + promedio/entry.getValue().size());
			calificaciones.put(entry.getKey().toUpperCase(), promedio/entry.getValue().size());
			if(promedio/entry.getValue().size() > 3) {
				aprobadas.put(entry.getKey().toUpperCase(), promedio/entry.getValue().size());
				System.out.println("Aprobada - " + entry.getKey().toUpperCase() + " || " + promedio/entry.getValue().size());
			}
		}
		
		*/
	}
	
	// Punto 1
	public static void calculadora() {
		Scanner sc = new Scanner(System.in);
		String continuar;
		double valorOperado;
		Integer valor;
		
		do {
			System.out.println("Ingrese el valor");
			valor = Integer.parseInt(sc.nextLine());
			
			System.out.println("Ingrese la funcion a ejecutar");
			System.out.println("* seno (S)");
			System.out.println("* coseno (C)");
			System.out.println("* tangente (T)");
			System.out.println("* exponencial (E)");
			System.out.println("* logaritmo neperiano (L)");
			
			String funcion = sc.nextLine();
			
			/*switch (funcion.toUpperCase()) {
			case "S":
				System.out.println("===== Seno (S) =====");
				for (int i = 1; i <= valor; i++) {
					valorOperado = Math.sin(i);
					System.out.println("| " + i + " || " + valorOperado + " |");
				}
				break;
				
			case "C":
				System.out.println("===== Coseno (C) =====");
				for (Integer i = 1; i <= valor; i++) {
					valorOperado = Math.cos(i);
					System.out.println(i.toString() + "||" + valorOperado);
				}
				break;
				
			case "T":
				System.out.println("===== Tangente (T) =====");
				for (Integer i = 1; i <= valor; i++) {
					valorOperado = Math.tan(i);
					System.out.println(i.toString() + "||" + valorOperado);
				}
				break;
				
			case "E":
				System.out.println("===== Exponencial (E) =====");
				for (Integer i = 1; i <= valor; i++) {
					valorOperado = Math.exp(i);
					System.out.println(i.toString() + "||" + valorOperado);
				}
				break;
				
			case "L":
				System.out.println("===== Logaritmo Neperiano (L) =====");
				for (Integer i = 1; i <= valor; i++) {
					valorOperado = Math.log(i);
					System.out.println(i.toString() + "||" + valorOperado);
				}
				break;
			default:
				System.out.println("Operaci?n invalida");
				break;
			}*/
			
			System.out.println("---------------------- Funcional - Punto 1 ------------------------");
			Map<String, Function<Integer, Double>> op = new HashMap<>();
			op.put("S", n -> Math.sin(n));
			op.put("C", n -> Math.cos(n));
			op.put("T", n -> Math.tan(n));
			op.put("E", n -> Math.exp(n));
			op.put("L", n -> Math.log(n));
			
			IntStream.range(1, valor + 1).forEach(n -> System.out.println(n + "||" + op.get(funcion.toUpperCase()).apply(n)));
			System.out.println("-------------------------------------------------------------------\n");
			
			System.out.println("\nContinuar operando S/N");
			continuar = sc.nextLine();
			
		} while (continuar.equalsIgnoreCase("S"));
				
		sc.close();
	}
	
	// Punto 2
	public static void diccionarioFrase() {
		/*Map<String, Integer> map = new HashMap<String, Integer>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese la frase");
		String frase = sc.nextLine();
		
		List<String> palabras = Arrays.asList(frase.split(" "));
		
		for (String palabra : palabras) {
			map.put(palabra, palabra.length());
			System.out.println(palabra + " || " + palabra.length());
		}
		
		return map;*/
		
		System.out.println("---------------------- Funcional - Punto 2 ------------------------");
		String frase = "Frase de prueba para taller";
		Arrays.asList(frase.split(" ")).forEach(p -> System.out.println(p + " || " + p.length()));
		System.out.println("-------------------------------------------------------------------\n");
		
		/*if (frase.trim().length() > 0) {
			if(frase.indexOf(" ") == 0) {
				map.put(frase, frase.length());
			} else {
				String palabra = "";
				int pos = frase.indexOf(" ");
				while(pos > 0) {
					palabra = frase.substring(0, pos);
					map.put(palabra, palabra.length());
					frase = frase.substring(pos+1);
					
					pos = frase.indexOf(" ");
				}
				
				map.put(frase, frase.length());
			}
			
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
			    System.out.println(entry.getKey() + "||" + entry.getValue());
			}
		}*/
	}
	
	// Punto 3
	public static List<Double> listaCalificaciones(){
		double[][] notas = {{2.5, 3.8, 4.0}, {3.5, 3.0, 2.4}, {3.5, 4.8, 1.5}, {2.6, 2.9, 3.8}};
		
		List<Double> calificaciones = new ArrayList<Double>();
		
		for (int i = 0; i < notas.length; i++) {
			double promedio = 0;
			for (int j = 0; j < notas[0].length; j++) {
				promedio += notas[i][j];
			}
			
			calificaciones.add(promedio/notas[0].length);
		}
		
		for (Double calificacion : calificaciones) {
			System.out.println(calificacion);
		}
		
		System.out.println("---------------------- Funcional - Punto 3 ------------------------");
		List<List<Double>> notasList = Arrays.asList(Arrays.asList(2.5, 3.8, 4.0), 
													 Arrays.asList(3.5, 3.0, 2.4), 
													 Arrays.asList(3.5, 4.8, 1.5), 
													 Arrays.asList(2.6, 2.9, 3.8));
		
		//notasList.forEach(n -> System.out.println(n.stream().reduce((x, t) -> x = x + t).get() / n.size()));
		notasList.forEach(n -> System.out.println(n.stream()
															.collect(Collectors.summarizingDouble(Double::doubleValue))
															.getAverage()));
		System.out.println("-------------------------------------------------------------------\n");
		
		return calificaciones;
	}
	
	// Punto 6
	public static void inmobiliara(double presupuesto) {
		List<Inmueble> inmuebles= new ArrayList<>();
		List<Inmueble> nuevosInmuebles= new ArrayList<>();
		
		inmuebles.add(new Inmueble(2000, 100, 3, true, "A"));
		inmuebles.add(new Inmueble(2012, 60, 2, true, "B"));
		inmuebles.add(new Inmueble(1980, 120, 4, false, "A"));
		inmuebles.add(new Inmueble(2005, 75, 3, true, "B"));
		inmuebles.add(new Inmueble(2015, 90, 2, false, "A"));
		
		for (Inmueble inmueble : inmuebles) {
			// Zona A: precio = (metros * 1000 + habitaciones * 5000+ garaje * 15000) * (1-antiguedad/100)
			// Zona B: precio = (metros * 1000 + habitaciones * 5000+ garaje * 15000) * (1-antiguedad/100) * 1.5
			double precio = (inmueble.getMt() * 1000 + inmueble.getHabitaciones() * 5000 + ((inmueble.isGarage() ? 0 : 1) * 15000)) * (1-(LocalDate.now().getYear() - inmueble.getYear())/100);
			
			if (inmueble.getZona().equalsIgnoreCase("B")) precio *= 1.5;
			//System.out.println(precio);
			if (precio <= presupuesto) {
				nuevosInmuebles.add(inmueble);
			}
		}
		
		System.out.println(nuevosInmuebles);
		
		System.out.println("---------------------- Funcional - Punto 6 ------------------------");
		Function<Inmueble, Double> precio = inmueble -> ((inmueble.getMt() * 1000 + inmueble.getHabitaciones() * 5000 + ((inmueble.isGarage() ? 0 : 1) * 15000)) * (1-(LocalDate.now().getYear() - inmueble.getYear())/100)) * (inmueble.getZona().equalsIgnoreCase("B") ? 1.5 : 1);
		System.out.println(inmuebles.stream().filter(i -> precio.apply(i) <= presupuesto).collect(Collectors.toList()));
		System.out.println("-------------------------------------------------------------------\n");
	}
}

class Inmueble {
	private int year;
	private int mt;
	private int habitaciones;
	private boolean garage;
	private String zona;
	private double precio;
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Inmueble [year=" + year + ", mt=" + mt + ", habitaciones=" + habitaciones + ", garage=" + garage
				+ ", zona=" + zona + "]\n";
	}
	public Inmueble(int year, int mt, int habitaciones, boolean garage, String zona) {
		this.year = year;
		this.mt = mt;
		this.habitaciones = habitaciones;
		this.garage = garage;
		this.zona = zona;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMt() {
		return mt;
	}
	public void setMt(int mt) {
		this.mt = mt;
	}
	public int getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(int habitaciones) {
		this.habitaciones = habitaciones;
	}
	public boolean isGarage() {
		return garage;
	}
	public void setGarage(boolean garage) {
		this.garage = garage;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	
	
}
