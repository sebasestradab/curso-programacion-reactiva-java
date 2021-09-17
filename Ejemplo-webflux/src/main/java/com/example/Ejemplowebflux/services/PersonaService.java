package com.example.Ejemplowebflux.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Ejemplowebflux.entities.Persona;

@Service
public class PersonaService {
	private List<Persona> crearLista(){
		return Arrays.asList(
				new Persona("Sebas", 33),
				new Persona("Maria", 28),
				new Persona("Samuel", 1)
				);
	}
	
	public List<Persona> getPersonas(){
		return crearLista();
	}
}
