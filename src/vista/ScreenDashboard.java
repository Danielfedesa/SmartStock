package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ScreenDashboard extends JFrame {

	private static final long serialVersionUID = 1L;

	public ScreenDashboard() {
		// Ventana
		setTitle("SmartStock - Dashboard");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(800, 600));
		
		// Estilos
		Color fondoColor = new Color(240, 240, 240);
		Color botonColor = new Color(70, 130, 180);
		Color textoBotonColor = Color.white;
		Font fuenteTitulo = new Font("Arial", Font.BOLD, 24);
		Font fuenteBotones = new Font("Arial", Font.PLAIN, 16);
				
		// Configuración del fondo
		getContentPane().setBackground(fondoColor);
		
		// Panel PRINCIPAL
		JPanel contenedor = new JPanel(new GridBagLayout());
		contenedor.setBackground(fondoColor);
		
		// Panel del menu
		JPanel panelMenu = new JPanel();
		panelMenu.setPreferredSize(new Dimension(400, 300));
		panelMenu.setBackground(fondoColor);
		panelMenu.setLayout(new GridBagLayout());
		panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Componentes
		JLabel tituloLabel = new JLabel("Dashboard", SwingConstants.CENTER);
		tituloLabel.setFont(fuenteTitulo);
		tituloLabel.setBackground(Color.DARK_GRAY);

        JButton inventario = new JButton("Inventario");
        configBoton(inventario, botonColor, textoBotonColor, fuenteBotones);
        
        // Acciones de los botnes
        inventario.addActionListener(e -> abrirInventario());
        
        // Añadir componentes al panel del menu
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        
        gbc.gridy = 0;
        panelMenu.add(tituloLabel, gbc);

        gbc.gridy++;
        panelMenu.add(inventario, gbc);

        // Añadir el panel del menú al contenedor principal
        contenedor.add(panelMenu);

        // Añadir el contenedor a la ventana
        add(contenedor);
    }
	
	private void configBoton(JButton boton, Color fondo, Color texto, Font fuente) {
		boton.setFont(fuente);
		boton.setBackground(fondo);
		boton.setForeground(texto);
		boton.setFocusPainted(false);
		boton.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(fondo.darker(), 1),
				BorderFactory.createEmptyBorder(5, 15, 5, 15)
				));
		boton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				boton.setBackground(fondo.brighter());
			}
			
			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				boton.setBackground(fondo);
			}
		});
	}
	
	
	    private void abrirInventario() {
	        // Lógica para abrir la pantalla de inventario
	        JOptionPane.showMessageDialog(this, "Abrir Inventario");
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            new ScreenDashboardAdmin().setVisible(true);
	        });
		
	} // Close ventana
	

} // Class

