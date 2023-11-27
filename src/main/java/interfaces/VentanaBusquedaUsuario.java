package interfaces;

import org.example.Biblioteca;
import org.example.Libro;
import org.example.Prestamo;
import org.example.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaBusquedaUsuario extends JFrame {

    private Biblioteca biblioteca;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField campoBusqueda;

    public VentanaBusquedaUsuario(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        setTitle("Búsqueda de Usuarios");
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
                ArrayList<Usuario> usuarios = biblioteca.buscarUsuariosPorRut(busqueda);
                llenarTablaConDatos(usuarios);
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

    private void llenarTablaConDatos(ArrayList<Usuario> usuarios) {
        String[] columnas = {"Nombre", "RUT", "Teléfono"};
        Object[][] datos = new Object[usuarios.size()][columnas.length];

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            datos[i][0] = usuario.getNombre();
            datos[i][1] = usuario.getRut();
            datos[i][2] = usuario.getTelefono();
        }

        tableModel.setDataVector(datos, columnas);
    }


}
