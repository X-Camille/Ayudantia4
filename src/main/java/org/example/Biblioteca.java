package org.example;

import gestorDatos.GestorDatos;
import gestorDatos.GestorPDF;

import java.util.*;

public class Biblioteca {
    private ArrayList<Usuario> usuarios;
    private Collection<Bibliotecario> bibliotecario;
    private ArrayList<Prestamo> prestamos;
    private ArrayList<Libro> libros;
    private String nombreBiblioteca;
    private String direccionBiblioteca;

    public void agregarLibro() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el título del libro: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese la editorial del libro: ");
        String editorial = scanner.nextLine();
        Libro nuevoLibro = new Libro(nombre, autor, editorial);
        libros.add(nuevoLibro);
        System.out.println("Libro " + nombre + " agregado con éxito.");
    }

    public boolean entradaEsValida(String entrada) {
        if (entrada == null) {
            return false;
        }
        boolean contieneSoloEspacios = entrada.trim().isEmpty();
        boolean contieneNumeros = contieneNumeros(entrada);
        return !contieneSoloEspacios && !contieneNumeros;
    }

    private boolean contieneNumeros(String entrada) {
        for (char c : entrada.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Prestamo> buscarPrestamosPorRut(String rut) {
        GestorDatos gestorDatos = new GestorDatos();
        ArrayList<Prestamo> prestamosEncontrados = new ArrayList<>();

        prestamos = gestorDatos.leerArchivoYCrearInstancias("C:/Users/Lenovo/Desktop/biblioteca.txt");

        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().getRut().equals(rut)) {
                System.out.println("Prestamo encontrado con éxito.");
                System.out.println("Nombre: " + prestamo.getLibro().getNombre() + ". Autor: " + prestamo.getLibro().getAutor() + ". Editorial: " + prestamo.getLibro().getEditorial());
                prestamosEncontrados.add(prestamo);
            }
        }

        if (prestamosEncontrados.isEmpty()) {
            System.out.println("Lo sentimos. No hay resultados de búsqueda para ese rut.");
        }

        return prestamosEncontrados;
    }

    public ArrayList<Usuario> buscarUsuariosPorRut(String rut) {
        GestorDatos gestorDatos = new GestorDatos();
        ArrayList<Usuario> usuariosEncontrados = new ArrayList<>();

        usuarios = gestorDatos.leerArchivoUsuario("C:/Users/Lenovo/Desktop/bibliotecaUsuarios.txt");

        for (Usuario usuario : usuarios) {
            if (usuario.getRut().equals(rut)) {
                System.out.println("Usuario encontrado con éxito.");
                System.out.println("Nombre: " + usuario.getNombre() + ". Autor: " + usuario.getRut() + ". Editorial: " + usuario.getTelefono());
                usuariosEncontrados.add(usuario);
            }
        }

        if (usuariosEncontrados.isEmpty()) {
            System.out.println("Lo sentimos. No hay resultados de búsqueda para ese rut.");
        }

        return usuariosEncontrados;
    }

    public ArrayList<Libro> buscarLibroPorTitulo(String titulo) {
        GestorDatos gestorDatos = new GestorDatos();
        ArrayList<Libro> librosEncontrados = new ArrayList<>();

        libros = gestorDatos.leerArchivoLibro("C:/Users/Lenovo/Desktop/bibliotecaLibros.txt");

        for (Libro libro : libros) {
            if (libro.getNombre().equals(titulo)) {
                System.out.println("Libro encontrado con éxito.");
                System.out.println("Nombre: " + libro.getNombre() + ". Autor: " + libro.getAutor() + ". Editorial: " + libro.getEditorial());
                librosEncontrados.add(libro);
            }
        }

        if (librosEncontrados.isEmpty()) {
            System.out.println("Lo sentimos. No hay resultados de búsqueda para ese rut.");
        }

        return librosEncontrados;
    }

    public List<Libro> obtenerLibrosPorAutor(String autor) {
        List<Libro> librosPorAutor = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAutor().equals(autor)) {
                librosPorAutor.add(libro);
                System.out.println("Libro encontrado con éxito.");
                System.out.println("Nombre: " + libro.getNombre() + ". Autor: " + libro.getAutor() + ". Editorial: " + libro.getEditorial());
            }
            else {System.out.println("Lo sentimos. No hay resultados de búsqueda para ese libro.");}
        }
        return librosPorAutor;
    }

    public Libro libroExiste(String titulo) {
        for (Libro libro : libros) {
            if (libro.getNombre().equals(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public Usuario generarUsuario(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del usuario que hará el préstamo: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el rut del usuario que hará el préstamo: ");
        String rut = scanner.nextLine();
        System.out.println("Ingrese el teléfono del usuario que hará el préstamo: ");
        String telefono = scanner.nextLine();
        System.out.println("Datos del usuario registrados con éxito");
        return new Usuario(nombre, rut, telefono);
    }


    public boolean generarPrestamo(Usuario usuario, Libro libro, Bibliotecario bibliotecario) {
        Scanner scanner = new Scanner(System.in);
        GestorDatos gestor = new GestorDatos();
        if (libro != null) {
            Prestamo prestamo = new Prestamo(usuario, libro, bibliotecario);
            prestamos.add(prestamo);
            gestor.registrarDato(prestamo, "C:/Users/Lenovo/Desktop/biblioteca.txt");
            System.out.println("Préstamo generado con éxito");
            return true;
        }
        else {
            System.out.println("Hubo un problema al generar el préstamo. Inténtelo de nuevo");
            return false;
        }
    }

    public boolean generarLibro(Libro libro) {
        Scanner scanner = new Scanner(System.in);
        GestorDatos gestor = new GestorDatos();
        if (libro != null) {
            gestor.registrarDato(libro, "C:/Users/Lenovo/Desktop/bibliotecaLibros.txt");
            System.out.println("Libro generado con éxito");
            return true;
        }
        else {
            System.out.println("Hubo un problema al generar el libro. Inténtelo de nuevo");
            return false;
        }
    }

    public boolean generarUsuario(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        GestorDatos gestor = new GestorDatos();
        if (usuario != null) {
            gestor.registrarDato(usuario, "C:/Users/Lenovo/Desktop/bibliotecaUsuarios.txt");
            System.out.println("Usuario generado con éxito");
            return true;
        }
        else {
            System.out.println("Hubo un problema al generar el usuario. Inténtelo de nuevo");
            return false;
        }
    }

    public boolean recibirDatosLibro(String[] datosLibro){
        if(!comprobarSiCamposVacios(datosLibro) && camposLibroValidos(datosLibro)) {
            Libro libro = new Libro(datosLibro[0], datosLibro[1], datosLibro[2]);
            return generarLibro(libro);
        } else {
            return false;
        }
    }

    public boolean recibirDatosUsuario(String[] datosUsuario){
        if(!comprobarSiCamposVacios(datosUsuario) && camposUsuarioValidos(datosUsuario)) {
            Usuario usuario = new Usuario(datosUsuario[0], datosUsuario[1], datosUsuario[2]);
            return generarUsuario(usuario);
        } else {
            return false;
        }
    }

    private boolean camposLibroValidos(String[] datosLibro) {
        return validarNombre(datosLibro[1]);
    }

    private boolean camposUsuarioValidos(String[] datosUsuario) {
        return validarNombre(datosUsuario[0]) && validarRut(datosUsuario[1]) && validarTelefono((datosUsuario[2]));
    }


    public boolean recibirDatosPrestamo(String[] datosPrestamo){
        if(!comprobarSiCamposVacios(datosPrestamo) && camposValidos(datosPrestamo)) {
            Usuario usuario = new Usuario(datosPrestamo[0], datosPrestamo[1], datosPrestamo[2]);
            Libro libro = new Libro(datosPrestamo[3], datosPrestamo[4], datosPrestamo[5]);
            Bibliotecario bibliotecario = new Bibliotecario(datosPrestamo[6]);
            return generarPrestamo(usuario, libro, bibliotecario);
        } else {
            return false;
        }
    }

    private boolean camposValidos(String[] datosPrestamo) {
        return validarNombre(datosPrestamo[0]) &&
                validarRut(datosPrestamo[1]) &&
                validarTelefono(datosPrestamo[2]) &&
                validarNombre(datosPrestamo[4]) &&
                validarNombre(datosPrestamo[6]);
    }


    private boolean validarNombre(String nombre) {
        // Verificar si el nombre está vacío o nulo
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        return nombre.matches("[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]+");
    }

    private boolean validarRut(String rut) {
        // Verificar si el RUT está vacío o nulo
        if (rut == null || rut.trim().isEmpty()) {
            return false;
        }
        return rut.length() >= 8 && rut.length() <= 9;
    }



    private boolean validarTelefono(String telefono) {
        // Verificar si el teléfono está vacío o nulo
        if (telefono == null || telefono.trim().isEmpty()) {
            return false;
        }

        // Verificar si el teléfono tiene exactamente 9 dígitos
        return telefono.length() == 9;
    }

    public boolean comprobarSiCamposVacios(String[] datosPrestamo){
        for (String dato : datosPrestamo) {
            if (dato == null || dato.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Biblioteca(ArrayList<Libro> libros, String nombreBiblioteca, String direccionBiblioteca) {
        this.libros = libros;
        this.nombreBiblioteca = nombreBiblioteca;
        this.direccionBiblioteca = direccionBiblioteca;
    }

    public Biblioteca(String nombreBiblioteca, String direccionBiblioteca) {
        this.libros = new ArrayList<Libro>();
        this.usuarios = new ArrayList<Usuario>();  // Agrega esta línea
        this.bibliotecario = new ArrayList<Bibliotecario>();  // Agrega esta línea
        this.prestamos = new ArrayList<Prestamo>();  // Agrega esta línea
        this.nombreBiblioteca = nombreBiblioteca;
        this.direccionBiblioteca = direccionBiblioteca;
    }

}