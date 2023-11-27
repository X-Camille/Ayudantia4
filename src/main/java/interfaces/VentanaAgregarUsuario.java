package interfaces;

import org.example.Biblioteca;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaAgregarUsuario extends JFrame {

    private final Biblioteca biblioteca;;
    private JTextField campoNombre;
    private JTextField campoRut;
    private JTextField campoTelefono;
    private JButton bNuevoUsuario;
    private JButton bAgregarUsuario;
    private JButton bVolver;
    private JPanel panelPrincipal;

    public VentanaAgregarUsuario(Biblioteca biblioteca){
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

        bNuevoUsuario.addActionListener(e -> {
            limpiarCampos();
            habilitarCampos();
            bAgregarUsuario.setEnabled(true);
        });

        bAgregarUsuario.addActionListener(e -> {
            boolean prestamoGenerado = biblioteca.recibirDatosUsuario(obtenerDatosUsuario());
            if (prestamoGenerado) {
                limpiarCampos();
                deshabilitarCampos();
                bAgregarUsuario.setEnabled(false);
                JOptionPane.showMessageDialog(this, "El usuario ha sido generado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error al generar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
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
        JPanel panelNombreUsuario = crearPanelCampo("Nombre del Usuario:", campoNombre);
        panelPrincipal.add(panelNombreUsuario);
        JPanel panelRutUsuario = crearPanelCampo("RUT:", campoRut);
        panelPrincipal.add(panelRutUsuario);
        JPanel panelTelefonoUsuario= crearPanelCampo("Teléfono:", campoTelefono);
        panelPrincipal.add(panelTelefonoUsuario);
    }

    private void configurarDimensionesCampos() {
        campoNombre = new JTextField(20);
        campoRut = new JTextField(20);
        campoTelefono= new JTextField(20);
    }

    private JPanel crearPanelPrincipal() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        return panelPrincipal;
    }

    private JLabel crearLabelTitulo() {
        JLabel labelTitulo = new JLabel("AGREGAR USUARIO");
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
        bNuevoUsuario = crearBoton("Nuevo Usuario", new Color(0, 150, 0));
        bAgregarUsuario = crearBoton("Agregar Usuario", new Color(255, 165, 0));
        bVolver = crearBoton("Volver al Inicio", new Color(250, 50, 50));
        JPanel panelBotones = crearPanelBotones(bNuevoUsuario, bAgregarUsuario, bVolver);
        bAgregarUsuario.setEnabled(false);
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
    }

    private void habilitarCampos() {
        campoNombre.setEnabled(true);
        campoRut.setEnabled(true);
        campoTelefono.setEnabled(true);
    }

    private void limpiarCampos() {
        campoNombre.setText("");
        campoRut.setText("");
        campoTelefono.setText("");
    }

    private String[] obtenerDatosUsuario() {
        String nombre = campoNombre.getText();
        String rut = campoRut.getText();
        String telefono = campoTelefono.getText();
        return new String[]{nombre, rut, telefono};
    }
}

