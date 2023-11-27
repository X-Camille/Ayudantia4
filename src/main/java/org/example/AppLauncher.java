package org.example;

import interfaces.GUIBiblioteca;

import java.io.IOException;

public class AppLauncher {
    public static void main(String[] args) throws IOException {
        GUIBiblioteca biblioteca = new GUIBiblioteca();
        biblioteca.mostrarInterfaz();
    }
}
