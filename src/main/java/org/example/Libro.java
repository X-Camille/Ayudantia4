package org.example;

import java.util.Random;

public class Libro {
    private Prestamo prestamo;
    private String nombre;
    private String autor;
    private String editorial;
    private String isbn;

    private Especialidad especialidad;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Especialidad getEspecialidad() {
        return this.especialidad;
    }
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    // Genera un ISBN aleatorio de 13 dígitos
    public void generarIsbnAleatorio() {
        StringBuilder isbnBuilder = new StringBuilder("978");
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int digito = random.nextInt(10);
            isbnBuilder.append(digito);
        }

        this.isbn = isbnBuilder.toString();
    }

    public Libro(String nombre, String autor, String editorial) {
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        generarIsbnAleatorio();
    }
    @Override
    public String toString() {
        return nombre + ", " + autor + ", " + editorial;
    }
}
