package org.example;

public class Usuario {

	private Prestamo prestamo;
	private String nombre;
	private String rut;
	private String telefono;

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return this.rut;
	}


	public void setRut(String rut) {
		this.rut = rut;
	}


	public String getTelefono() {
		return this.telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Usuario(String nombre, String rut, String telefono) {
		this.nombre = nombre;
		this.rut = rut;
		this.telefono = telefono;
	}

	public String toString(){
		return getNombre() + "," + getRut() + "," + getTelefono();
	}

}