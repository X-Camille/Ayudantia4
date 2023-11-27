package org.example;

public class Bibliotecario {

	private Prestamo Prestamo;
	private String nombre;
	private int rut;
	private String direccion;
	private int id;

	public String getNombre() {
		return this.nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRut() {
		return this.rut;
	}

	/**
	 * 
	 * @param rut
	 */
	public void setRut(int rut) {
		this.rut = rut;
	}

	public String getDireccion() {
		return this.direccion;
	}

	/**
	 * 
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public Bibliotecario(String nombre) {
		this.nombre = nombre;
	}

	public String toString(){
		return getNombre();
	}
}