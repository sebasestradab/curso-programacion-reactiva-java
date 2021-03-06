package co.com.sebas.reactiva;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

public class Observables {

	public static void main(String[] args) {
		Integer[] numeros = {1, 5, 123, 143, 86, 800, 45, 900};
		
//		Observable.empty();
//		Observable.just("Algo");
//		Observable.just("esto", "y esto", "y esto");
		
//		System.out.println(numeros);
		Observable.from(numeros)
					//.filter(x -> x > 100 && x < 500)
					//.first()	// El primero
					//.last()	// El ultimo
					//.take(3)	// Los primeros (n) elementos
					//.skip(3)	// Omite los (n) elementos
					//.skipLast(3)// Omite los ultimos (n)
					.map(x -> Math.pow(x, 2))
					.subscribe(x -> System.out.println("Este es el numero: " + x));

		List<Coord> coords = Arrays.asList(
				new Coord(12.1231, 0.1231),
				new Coord(1.213, 9.4556),
				new Coord(5.33, 6.121),
				new Coord(0.12355, 0.87654)
				);
		
		Observable.from(coords)
				.map(c -> c.x + ", " + c.y)
				.subscribe(System.out::println);
		
		Double[][] matriz = {
				{0.1231, 9.8765}, {1.12, 0.3}, {10.9876, 5.1345}
		};
		
		Observable.from(matriz)
				.map(d -> new Coord(d[0], d[1]))
				//.filter(c -> c.x < 10 && c.y < 10)
				//.count() 	// Cuenta los elementos
				//.all(c -> c.x < 10 && c.y < 10)	// Valida que todos los elementos cumplan la condicion, retorna true o false
				//.reduce((c1, c2) -> new Coord(c1.x + c2.x, c1.y + c2.y))	// Es un acumulador, acumula las coordenadas, crearia un solo elementos de Coords co la sumatoria de toas las coordenadas
				//.toMap(c -> c.x + c.y)	// Convierte a un tipo Map<>
				.toMap(c -> c.toString(), c -> c.x + c.y)
				.subscribe(System.out::println);
	}

}

class Coord {
	double x, y;

	public Coord(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Coord [x=" + x + ", y=" + y + "]";
	}
	
	
}
