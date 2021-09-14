package co.com.sebas;

import java.util.Optional;
import java.util.function.Supplier;

public class ClaseOptional {
	public static void main(String[] args) {
		Supplier<String> supplier = () -> "Referencia Suplier";
		Optional<String> op = Optional.of(";");
		
		if(op.isPresent()) {
			op.get();
			// Hacer algo
		}
		
		op.ifPresent(s -> System.out.println("Esto es lo que habia: " + s));
		String dato = op.orElse("Por defecto");
		dato = op.orElseGet(() -> "Uso del Supplier");
		dato = op.orElseGet(supplier);
		
		try {
			op.orElseThrow(() -> new Exception("Algo..."));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		op.empty();
	}
}
