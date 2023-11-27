package org.example;

import java.util.UUID;

public class Prestamo {

	private Usuario usuarios;
	private Bibliotecario bibliotecario;
	private Usuario usuario;
	private Libro libro;
	private String fecha_inicio;
	private String fecha_termino;

	public Prestamo(Usuario usuario, Libro libro) {
		this.usuario = usuario;
		this.libro = libro;
	}

	public Prestamo(Usuario usuario, Libro libro, Bibliotecario bibliotecario){
		this.usuario = usuario;
		this.libro = libro;
		this.bibliotecario = bibliotecario;
	}

	public Bibliotecario getBibliotecario() {
		return this.bibliotecario;
	}


	public void setBibliotecario(Bibliotecario bibliotecario) {
		this.bibliotecario = bibliotecario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Libro getLibro() {
		return libro;
	}


	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public String getFecha_inicio() {
		return this.fecha_inicio;
	}


	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_termino() {
		return this.fecha_termino;
	}


	public void setFecha_termino(String fecha_termino) {
		this.fecha_termino = fecha_termino;
	}

	public String toString(){
		return getUsuario() + ", " + getLibro() + ", " + getBibliotecario();
	}
}