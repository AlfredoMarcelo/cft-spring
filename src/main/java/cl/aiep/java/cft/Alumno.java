package cl.aiep.java.cft;

import javax.validation.constraints.Min; //las dependencia de validacion siempre deben ser Javax.validation
import javax.validation.constraints.Size;

public class Alumno {

	
	@Size(min = 3, max = 20, message = "debes ingresar un nombre y un apellido") //se esta validando que el nombre contenga mas de 3 letras
	private String nombre;
	@Min(value=18, message = "avispate debes ser mayor de edad") //validacion de numero, debe ser mayor a 18
	private int edad;
	
	
	public Alumno() {
	}


	public Alumno(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
	
	
	
	
	
}
