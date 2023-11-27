package interfaces;

import org.example.Biblioteca;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaPrestamo extends JFrame {

    private final Biblioteca biblioteca;
    private JTextField campoNombre;
    private JTextField campoRut;
    private JTextField campoTelefono;
    private JTextField campoTituloLibro;
    private JTextField campoAutorLibro;
    private JTextField campoEditorialLibro;
    private JTextField campoNombreBibliotecario;
    private JButton bNuevoPrestamo;
    private JButton bGenerarPrestamo;
    private JButton bVolver;
    private JPanel panelPrincipal;

    public VentanaPrestamo(Biblioteca biblioteca){
        this.biblioteca = biblioteca;
    }
    public void mostrarInterfaz() {
        panelPrincipal = crearPanelPrincipal();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelTitulo = crearLabelTitulo();
        panelPrincipal.add(labelTitulo);
        configurarDimensionesCampos();
        crearPanelesCampos(panelPrincipal);
        deshabilitarCampos();
        establecerBotones(panelPrincipal);

        bNuevoPrestamo.addActionListener(e -> {
            limpiarCampos();
            habilitarCampos();
            bGenerarPrestamo.setEnabled(true);
        });

        bGenerarPrestamo.addActionListener(e -> {
            boolean prestamoGenerado = biblioteca.recibirDatosPrestamo(obtenerDatosPrestamo());
            if (prestamoGenerado) {
                limpiarCampos();
                deshabilitarCampos();
                bGenerarPrestamo.setEnabled(false);
                JOptionPane.showMessageDialog(this, "El préstamo ha sido generado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error al generar el préstamo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        bVolver.addActionListener(e -> {
            dispose();
            new GUIBiblioteca().mostrarInterfaz();
        });

        add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void crearPanelesCampos(JPanel panelPrincipal) {
        JPanel panelNombre = crearPanelCampo("Nombre:", campoNombre);
        panelPrincipal.add(panelNombre);
        JPanel panelRut = crearPanelCampo("RUT:", campoRut);
        panelPrincipal.add(panelRut);
        JPanel panelTelefono = crearPanelCampo("Teléfono:", campoTelefono);
        panelPrincipal.add(panelTelefono);
        JPanel panelTituloLibro = crearPanelCampo("Título del Libro:", campoTituloLibro);
        panelPrincipal.add(panelTituloLibro);
        JPanel panelAutorLibro = crearPanelCampo("Autor del Libro:", campoAutorLibro);
        panelPrincipal.add(panelAutorLibro);
        JPanel panelEditorialLibro = crearPanelCampo("Editorial del Libro:", campoEditorialLibro);
        panelPrincipal.add(panelEditorialLibro);
        JPanel panelNombreBibliotecario = crearPanelCampo("Bibliotecario", campoNombreBibliotecario);
        panelPrincipal.add(panelNombreBibliotecario);
    }

    private void configurarDimensionesCampos() {
        campoNombre = new JTextField(20);
        campoRut = new JTextField(20);
        campoTelefono = new JTextField(20);
        campoTituloLibro = new JTextField(20);
        campoAutorLibro = new JTextField(20);
        campoEditorialLibro = new JTextField(20);
        campoNombreBibliotecario = new JTextField(20);
    }

    private JPanel crearPanelPrincipal() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        return panelPrincipal;
    }

    private JLabel crearLabelTitulo() {
        JLabel labelTitulo = new JLabel("AGREGAR PRÉSTAMO");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitulo.setBorder(new EmptyBorder(20, 0, 10, 0));
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        return labelTitulo;
    }

    private JPanel crearPanelCampo(String labelText, JTextField textField) {
        JPanel panelCampo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel labelCampo = new JLabel(labelText);
        panelCampo.add(labelCampo);
        panelCampo.add(textField);
        return panelCampo;
    }

    private void establecerBotones(JPanel panelPrincipal) {
        bNuevoPrestamo = crearBoton("Nuevo Préstamo", new Color(0, 150, 0));
        bGenerarPrestamo = crearBoton("Generar Préstamo", new Color(255, 165, 0));
        bVolver = crearBoton("Volver al Inicio", new Color(250, 50, 50));
        JPanel panelBotones = crearPanelBotones(bNuevoPrestamo, bGenerarPrestamo, bVolver);
        bGenerarPrestamo.setEnabled(false);
        panelPrincipal.add(panelBotones);
    }


    private JButton crearBoton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    private JPanel crearPanelBotones(JButton agregarButton, JButton editarButton, JButton bVolver) {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(agregarButton);
        panelBotones.add(editarButton);
        panelBotones.add(bVolver);
        return panelBotones;
    }

    private void deshabilitarCampos() {
        campoNombre.setEnabled(false);
        campoRut.setEnabled(false);
        campoTelefono.setEnabled(false);
        campoTituloLibro.setEnabled(false);
        campoAutorLibro.setEnabled(false);
        campoEditorialLibro.setEnabled(false);
        campoNombreBibliotecario.setEnabled(false);
    }

    private void habilitarCampos() {
        campoNombre.setEnabled(true);
        campoRut.setEnabled(true);
        campoTelefono.setEnabled(true);
        campoTituloLibro.setEnabled(true);
        campoAutorLibro.setEnabled(true);
        campoEditorialLibro.setEnabled(true);
        campoNombreBibliotecario.setEnabled(true);
    }

    private void limpiarCampos() {
        campoNombre.setText("");
        campoRut.setText("");
        campoTelefono.setText("");
        campoTituloLibro.setText("");
        campoAutorLibro.setText("");
        campoEditorialLibro.setText("");
        campoNombreBibliotecario.setText("");
    }

    private String[] obtenerDatosPrestamo() {
        String nombre = campoNombre.getText();
        String rut = campoRut.getText();
        String telefono = campoTelefono.getText();
        String titulo = campoTituloLibro.getText();
        String autor = campoAutorLibro.getText();
        String editorial = campoEditorialLibro.getText();
        String nombreBibliotecario = campoNombreBibliotecario.getText();
        return new String[]{nombre, rut, telefono, titulo, autor, editorial, nombreBibliotecario};
    }
}

