package interfaces;

import org.example.Biblioteca;
import org.example.Prestamo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaBusquedaPrestamo extends JFrame {

    private Biblioteca biblioteca;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField campoBusqueda;

    public VentanaBusquedaPrestamo(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        setTitle("Búsqueda de Préstamos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelBuscador = new JLabel("Buscador:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelBuscador, gbc);

        campoBusqueda = new JTextField();
        campoBusqueda.setPreferredSize(new Dimension(200, campoBusqueda.getPreferredSize().height)); // Establecer un ancho prefijado
        campoBusqueda.setMinimumSize(new Dimension(200, campoBusqueda.getPreferredSize().height)); // Establecer un tamaño mínimo
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoBusqueda, gbc);

        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String busqueda = campoBusqueda.getText();
                ArrayList<Prestamo> prestamo = biblioteca.buscarPrestamosPorRut(busqueda);
                if(prestamo != null){
                    llenarTablaConDatos(prestamo);
                }
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(botonBuscar, gbc);

        // Configuración de la tabla para mostrar resultados
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);

        // Botón para volver al menú de inicio
        JButton botonVolver = new JButton("Volver al menú de inicio");
        botonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra esta ventana
                new GUIBiblioteca().mostrarInterfaz();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(botonVolver, gbc);

        getContentPane().add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void llenarTablaConDatos(ArrayList<Prestamo> prestamos) {
        String[] columnas = {"RUT", "Nombre", "Título del Libro", "Bibliotecario"};
        Object[][] datos = new Object[prestamos.size()][columnas.length];

        for (int i = 0; i < prestamos.size(); i++) {
            Prestamo prestamo = prestamos.get(i);
            datos[i][0] = prestamo.getUsuario().getRut();
            datos[i][1] = prestamo.getUsuario().getNombre();
            datos[i][2] = prestamo.getLibro().getNombre();
            datos[i][3] = prestamo.getBibliotecario().getNombre();
        }

        tableModel.setDataVector(datos, columnas);
    }


}
