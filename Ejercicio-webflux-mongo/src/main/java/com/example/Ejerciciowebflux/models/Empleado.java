package com.example.Ejerciciowebflux.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "empleados")
public class Empleado {
	@Id
	private String id;
	private String nombre;
	private double salario;
	private Area area;
}
