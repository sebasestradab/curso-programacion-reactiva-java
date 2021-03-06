package co.com.sebas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Ejercicio2 {
	public static void main(String[] args) {
		try {
			FileReader vehiculosArchivo = new FileReader("D:\\Personal\\Java\\Cursos\\recursos\\vehiculos.txt"); 
			BufferedReader vehiculos = new BufferedReader(vehiculosArchivo);
			
			vehiculos.lines().filter(r -> !r.equalsIgnoreCase("Motos") && !r.equalsIgnoreCase("Carros") && r != null).forEach(s -> Vehiculo.agregarVehiculo((s.split(",").length == 7 ? new Carro(s.split(",")) : new Moto(s.split(",")))));
			
			Vehiculo.calcularImpuesto();
			
			// Obtener Vehiculo
			System.out.println("----------------------- Vehiculos -----------------------");
			Vehiculo.retornarVehiculos().forEach(v -> System.out.println(v));
			
			System.out.println("\n----------------------- Carros -----------------------");
			Predicate<Vehiculo> carros = v -> v.getClass() == Carro.class;
			Vehiculo.retonarCarrosOMotos(carros).forEach(c -> System.out.println(c));
			
			System.out.println("\n----------------------- Motos -----------------------");
			Predicate<Vehiculo> motos = v -> v.getClass() == Moto.class;
			Vehiculo.retonarCarrosOMotos(motos).forEach(c -> System.out.println(c));
			
			System.out.println("\n---------------------------------------------------- Definir Averiado ----------------------------------------------------");
			Vehiculo.definirAveriado().forEach((k, v) -> {
				System.out.println("-------------------------------------------------------- "+ k + " --------------------------------------------------------");
				v.forEach(vehiculo -> {
					vehiculo.setEstado(k);
					System.out.println(vehiculo);
					System.out.println(vehiculo.encender());
				});
			});
			
			System.out.println("\n----------------------- Vehiculos por A?o -----------------------");
			Vehiculo.vehiculosXA?o().forEach((k, v) -> {
				System.out.println("A?o: " + k);
				v.forEach(vehiculo -> System.out.println(vehiculo));
			});
			
			System.out.println("\n----------------------- Vehiculos por Precio -----------------------");
			Vehiculo.vehiculosXPrecio().forEach((k, v) -> {
				System.out.println("Precio: " + k);
				v.forEach(vehiculo -> System.out.println(vehiculo));
			});
			
			System.out.println("\n----------------------- Son Automaticos -----------------------");
			Vehiculo.sonAutomaticos().forEach((k, v) -> {
				System.out.println(k);
				v.forEach(vehiculo -> System.out.println(vehiculo));
			});
			
			System.out.println("\n----------------------- Datos Carro -----------------------");
			DoubleSummaryStatistics datosCarro= Vehiculo.datosPrecioCarroOMoto(v -> v.getClass() == Carro.class);
			System.out.println("Valor M?ximo: " + datosCarro.getMax());
			System.out.println("Valor Minimo: " + datosCarro.getMin());
			System.out.println("Promedio: " + datosCarro.getAverage());
			System.out.println("Suma: " + datosCarro.getSum());
			
			System.out.println("\n----------------------- Datos Moto -----------------------");
			DoubleSummaryStatistics datosMoto= Vehiculo.datosPrecioCarroOMoto(v -> v.getClass() == Moto.class);
			System.out.println("Valor M?ximo: " + datosMoto.getMax());
			System.out.println("Valor Minimo: " + datosMoto.getMin());
			System.out.println("Promedio: " + datosMoto.getAverage());
			System.out.println("Suma: " + datosMoto.getSum());
			
			System.out.println("\n----------------------- Datos Marca -----------------------");
			List<String> marcas = Vehiculo.retornarVehiculos().stream().map(v -> v.getMarca()).distinct().collect(Collectors.toList());
			System.out.println(marcas);
			String marca = marcas.get(new Random().nextInt(marcas.size()));
			DoubleSummaryStatistics datosMarca= Vehiculo.datosMarca(marca);
			System.out.println(marca);
			System.out.println("Valor M?ximo: " + datosMarca.getMax());
			System.out.println("Valor Minimo: " + datosMarca.getMin());
			System.out.println("Promedio: " + datosMarca.getAverage());
			
			System.out.println("\n----------------------- Datos Colores por Modelo -----------------------");
			Vehiculo.datosColoresXModelo(marca).forEach((k, v) -> {
				System.out.println("Marca: " + marca + " - Modelo: " + k);
				v.stream().distinct().forEach(vehiculo -> System.out.println("* " + vehiculo));
			});
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

abstract class Vehiculo {
	//Los datos para los carros tienen el siguiente orden:
	//Marca, modelo, a?o, precio, num puertas, color, transmisi?n.

	//Los datos para las motos tienen el siguiente orden:
	//Marca, modelo, a?o, CC, precio, color.
	
	private String marca;
	private String modelo;
	private int modelo_year;
	private double precio;
	private String color;
	private double impuesto;
	private String estado;
	
	public Vehiculo(String[] vehiculo, String tipo) {
		this.marca = vehiculo[0].trim();
		this.modelo = vehiculo[1].trim(); 
		this.modelo_year = Integer.parseInt(vehiculo[2].trim());
		this.precio = (tipo.equals("C") ? Double.parseDouble(vehiculo[3].trim()) : Double.parseDouble(vehiculo[4].trim()));
		this.color = vehiculo[5].trim();
	}
	
	private static List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	
	public static List<Vehiculo> retornarVehiculos(){
		return vehiculos;
	}
	
	public static void agregarVehiculo(Vehiculo vehiculo) {
		vehiculos.add(vehiculo);
	}
	
	public static List<Vehiculo> retonarCarrosOMotos(Predicate<Vehiculo> p){
		return vehiculos.stream().filter(v -> p.test(v)).collect(Collectors.toList());
		// vehiculos.stream().filter(v -> v.getClass() == Carro.class).map(v -> (Carro)v).collect(Collectors.toList());
	}
	
	//public static Map<String>
	
	public static void calcularImpuesto() {
		vehiculos.forEach(v -> v.setImpuesto(v.precio * 0.465));
	}
	
	public static Map<String, List<Vehiculo>> definirAveriado() {
		return vehiculos.stream().collect(Collectors.groupingBy(v -> ((int)(Math.random() * 100) > 50) ? "OK" : "Averiado" ));
	}
	
	public String encender() {
		return ("Vehiculo " + this.marca + "-" + this.modelo + (this.estado.equals("OK") ? " Encendido" : " No se pudo encender"));
	}
	
	public static Map<Integer, List<Vehiculo>> vehiculosXA?o(){
		return vehiculos.stream().collect(Collectors.groupingBy(Vehiculo::getModelo_year));
	}
	
	public static Map<Double, List<Vehiculo>> vehiculosXPrecio(){
		return vehiculos.stream().collect(Collectors.groupingBy(v -> v.getPrecio() - ((LocalDate.now().getYear() - v.getModelo_year()) * (v.getPrecio() * 0.1))));
	}
	
	public static Map<Object, List<Vehiculo>> sonAutomaticos(){
		return Vehiculo.retonarCarrosOMotos(v -> v.getClass() == Carro.class).stream().collect(Collectors.groupingBy(v -> {
			Carro carro = (Carro)v;
			return carro.getTransmision();
		}));
	}
	
	public static DoubleSummaryStatistics datosPrecioCarroOMoto(Predicate p){
		return Vehiculo.retonarCarrosOMotos(v -> p.test(v)).stream().collect(Collectors.summarizingDouble(v -> v.getPrecio()));
	}
	
	public static DoubleSummaryStatistics datosMarca(String marca) {
		return Vehiculo.vehiculos.stream().filter(v -> v.getMarca().equalsIgnoreCase(marca)).collect(Collectors.summarizingDouble(v -> v.getPrecio()));
	}
	
	// Funci?n para clasificar los colores disponibles por modelo, de una marca en espec?fico.
	public static Map<String, List<String>> datosColoresXModelo(String marca){
		Map<String, List<String>> coloresXModelo = new HashMap<>();
		vehiculos.stream().filter(v -> v.getMarca().equalsIgnoreCase(marca)).collect(Collectors.groupingBy(Vehiculo::getModelo)).forEach((k, v) -> {
			List<String> colores = new ArrayList<>();
			v.stream().forEach(c -> colores.add(c.getColor()));
			coloresXModelo.put(k, colores);
		});
		
		return coloresXModelo;
	}
	
	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}
	
	public double getPrecio() {
		return this.precio;
	}
	
	public String getMarca() {
		return this.marca;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getModelo_year() {
		return modelo_year;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public String getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		return "[marca=" + marca + ", modelo=" + modelo + ", modelo_year=" + modelo_year + ", precio=" + precio
				+ ", color=" + color + ", impuesto=" + impuesto + ", estado=" + estado + "]";
	}
	
}

class Carro extends Vehiculo {
	private int puertas;
	private String transmision;
	
	public Carro(String[] carro) {
		super(carro, "C");
		this.puertas = Integer.parseInt(carro[4].trim());
		this.transmision = carro[6].trim();
		
	}
	
	public String getTransmision() {
		return transmision;
	}

	@Override
	public String toString() {
		return "Carro " + super.toString() +  " [puertas=" + puertas + ", transmision=" + transmision + "]";
	}
	
}

class Moto extends Vehiculo {
	private int cilindraje;
	
	public Moto(String[] moto) {
		super(moto, "M");
		this.cilindraje = Integer.parseInt(moto[3].trim());
	}

	@Override
	public String toString() {
		return "Moto " + super.toString() + " [cilindraje=" + cilindraje + "]";
	}
	
	
}
