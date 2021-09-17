package com.example.Ejemplowebflux.entities;

import java.util.Map;

public class Persona {
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
