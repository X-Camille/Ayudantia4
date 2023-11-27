package interfaces;

import org.example.Biblioteca;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaAgregarLibro extends JFrame {

    private final Biblioteca biblioteca;;
    private JTextField campoTituloLibro;
    private JTextField campoAutorLibro;
    private JTextField campoEditorialLibro;
    private JButton bNuevoLibro;
    private JButton bAgregarLibro;
    private JButton bVolver;
    private JPanel panelPrincipal;

    public VentanaAgregarLibro(Biblioteca biblioteca){
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

        bNuevoLibro.addActionListener(e -> {
            limpiarCampos();
            habilitarCampos();
            bAgregarLibro.setEnabled(true);
        });

        bAgregarLibro.addActionListener(e -> {
            boolean prestamoGenerado = biblioteca.recibirDatosLibro(obtenerDatosLibro());
            if (prestamoGenerado) {
                limpiarCampos();
                deshabilitarCampos();
                bAgregarLibro.setEnabled(false);
                JOptionPane.showMessageDialog(this, "El libro ha sido generado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error al generar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
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
        JPanel panelTituloLibro = crearPanelCampo("Título del Libro:", campoTituloLibro);
        panelPrincipal.add(panelTituloLibro);
        JPanel panelAutorLibro = crearPanelCampo("Autor del Libro:", campoAutorLibro);
        panelPrincipal.add(panelAutorLibro);
        JPanel panelEditorialLibro = crearPanelCampo("Editorial del Libro:", campoEditorialLibro);
        panelPrincipal.add(panelEditorialLibro);
    }

    private void configurarDimensionesCampos() {
        campoTituloLibro = new JTextField(20);
        campoAutorLibro = new JTextField(20);
        campoEditorialLibro = new JTextField(20);
    }

    private JPanel crearPanelPrincipal() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        return panelPrincipal;
    }

    private JLabel crearLabelTitulo() {
        JLabel labelTitulo = new JLabel("AGREGAR LIBRO");
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
        bNuevoLibro = crearBoton("Nuevo Libro", new Color(0, 150, 0));
        bAgregarLibro = crearBoton("Agregar Libro", new Color(255, 165, 0));
        bVolver = crearBoton("Volver al Inicio", new Color(250, 50, 50));
        JPanel panelBotones = crearPanelBotones(bNuevoLibro, bAgregarLibro, bVolver);
        bAgregarLibro.setEnabled(false);
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
        campoTituloLibro.setEnabled(false);
        campoAutorLibro.setEnabled(false);
        campoEditorialLibro.setEnabled(false);
    }

    private void habilitarCampos() {
        campoTituloLibro.setEnabled(true);
        campoAutorLibro.setEnabled(true);
        campoEditorialLibro.setEnabled(true);
    }

    private void limpiarCampos() {
        campoTituloLibro.setText("");
        campoAutorLibro.setText("");
        campoEditorialLibro.setText("");
    }

    private String[] obtenerDatosLibro() {
        String titulo = campoTituloLibro.getText();
        String autor = campoAutorLibro.getText();
        String editorial = campoEditorialLibro.getText();
        return new String[]{titulo, autor, editorial};
    }
}

