package interfaces;
import org.example.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIBiblioteca extends JFrame implements ActionListener {

	private final Biblioteca biblioteca = new Biblioteca("UFRO", "Uruguay");
	private JButton bAgregarLibro;
	private JButton bAgregarUsuario;
	private JButton bBuscarLibro;
	private JButton bBuscarUsuario;

	public void mostrarInterfaz() {
		configurarVentana();
		JPanel panel = crearPanel();
		agregarComponentes(panel);
		mostrarVentana(panel);
	}

	private void configurarVentana() {
		setTitle("Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
	}

	private JPanel crearPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JLabel titleLabel = new JLabel("Biblioteca", SwingConstants.CENTER);
		configurarTitulo(titleLabel, gbc, panel);

		bAgregarLibro = crearBoton("Agregar Libro", new Color(100, 200, 255));
		configurarBoton(bAgregarLibro, gbc, panel, 2);

		bAgregarUsuario = crearBoton("Agregar Usuario", new Color(100, 255, 100));
		configurarBoton(bAgregarUsuario, gbc, panel, 3);


		bBuscarLibro = crearBoton("Buscar Libro", new Color(255, 100, 100));
		configurarBoton(bBuscarLibro, gbc, panel, 5);

		bBuscarUsuario = crearBoton("Buscar Usuario", new Color(255, 255, 100));
		configurarBoton(bBuscarUsuario, gbc, panel, 6);

		return panel;
	}

	private void configurarTitulo(JLabel label, GridBagConstraints gbc, JPanel panel) {
		label.setFont(new Font("Arial", Font.BOLD, 20));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(label, gbc);
	}

	private JButton crearBoton(String texto, Color color) {
		JButton button = new JButton(texto);
		button.addActionListener(this);
		button.setBackground(color);
		return button;
	}

	private void configurarBoton(JButton button, GridBagConstraints gbc, JPanel panel, int gridY) {
		gbc.gridx = 0;
		gbc.gridy = gridY;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(button, gbc);
	}

	private void agregarComponentes(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
	}

	private void mostrarVentana(JPanel panel) {
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bAgregarLibro) {
			dispose();
			new VentanaAgregarLibro(biblioteca).mostrarInterfaz();
		} else if (e.getSource() == bAgregarUsuario) {
			dispose();
			new VentanaAgregarUsuario(biblioteca).mostrarInterfaz();
		} else if (e.getSource() == bBuscarLibro) {
			dispose();
			new VentanaBusquedaLibro(biblioteca);
		} else if (e.getSource() == bBuscarUsuario) {
			dispose();
			new VentanaBusquedaUsuario(biblioteca);
		}
	}
}
