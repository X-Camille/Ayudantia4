package gestorDatos;

import org.example.Bibliotecario;
import org.example.Libro;
import org.example.Prestamo;
import org.example.Usuario;

import java.io.*;
import java.util.ArrayList;

public class GestorDatos {

    public ArrayList<Prestamo> leerArchivoYCrearInstancias(String direccionArchivo) {
        ArrayList<Prestamo> prestamos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(direccionArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length >= 7) {
                    Usuario usuario = new Usuario(datos[0], datos[1], datos[2]);
                    Libro libro = new Libro(datos[3], datos[4], datos[5]);
                    Bibliotecario bibliotecario = new Bibliotecario(datos[6]);
                    prestamos.add(new Prestamo(usuario, libro, bibliotecario));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al obtener los datos: " + e.getMessage());
        }

        if (prestamos.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo.");
        }
        return prestamos;
    }

    public ArrayList<Libro> leerArchivoLibro(String direccionArchivo) {
        ArrayList<Libro> libros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(direccionArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length >= 3) {
                    Libro libro = new Libro(datos[0], datos[1], datos[2]);
                    libros.add(libro);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al obtener los datos: " + e.getMessage());
        }

        if (libros.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo.");
        }
        return libros;
    }

    public ArrayList<Usuario> leerArchivoUsuario(String direccionArchivo) {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(direccionArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length >= 3) {
                    Usuario usuario = new Usuario(datos[0], datos[1], datos[2]);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al obtener los datos: " + e.getMessage());
        }

        if (usuarios.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo.");
        }
        return usuarios;
    }

    public boolean registrarDato(Object objeto, String direccionArchivo) {
        boolean lineaVacia=false;
        try {
            File file = new File(direccionArchivo);
            if (!file.exists()) {
                lineaVacia=true;
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);

            if(!lineaVacia){
                bw.newLine();
            }
            bw.write(objeto.toString());
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al ingresar dato, favor contactar con administrador");
            return false;
        }
    }

}
