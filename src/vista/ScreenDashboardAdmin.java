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

import modelo.Categoria;
import modelo.Producto;
import modelo.Usuario;

public class ScreenDashboardAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScreenDashboardAdmin() {
		// Ventana
		setTitle("SmartStock - Dashboard ADMINISTRADOR");
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
		JLabel tituloLabel = new JLabel("Dashboard Administrador", SwingConstants.CENTER);
		tituloLabel.setFont(fuenteTitulo);
		tituloLabel.setBackground(Color.DARK_GRAY);
		
		JButton gUsuarios = new JButton("Gestión de Usuarios");
		configBoton(gUsuarios, botonColor, textoBotonColor, fuenteBotones);
		
		JButton gCategorias = new JButton("Gestión de Categorías");
        configBoton(gCategorias, botonColor, textoBotonColor, fuenteBotones);

        JButton gProductos = new JButton("Gestión de Productos");
        configBoton(gProductos, botonColor, textoBotonColor, fuenteBotones);

        JButton inventario = new JButton("Inventario");
        configBoton(inventario, botonColor, textoBotonColor, fuenteBotones);

        JButton copiasSeguridad = new JButton("Copias de Seguridad");
        configBoton(copiasSeguridad, botonColor, textoBotonColor, fuenteBotones);
        
        // Acciones de los botnes
        gUsuarios.addActionListener(e -> abrirGestionUsuarios());
        gCategorias.addActionListener(e -> abrirGestionCategorias());
        gProductos.addActionListener(e -> abrirGestionProductos());
        inventario.addActionListener(e -> abrirInventario());
        copiasSeguridad.addActionListener(e -> abrirCopiasSeguridad());
        
        // Añadir componentes al panel del menu
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        
        gbc.gridy = 0;
        panelMenu.add(tituloLabel, gbc);
        
        gbc.gridy++;
        panelMenu.add(gUsuarios, gbc);

        gbc.gridy++;
        panelMenu.add(gCategorias, gbc);

        gbc.gridy++;
        panelMenu.add(gProductos, gbc);

        gbc.gridy++;
        panelMenu.add(inventario, gbc);

        gbc.gridy++;
        panelMenu.add(copiasSeguridad, gbc);

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

		private void abrirGestionUsuarios() {
			Usuario usu = new Usuario();
	        new ScreenGUsuarios(usu).setVisible(true);
	        this.dispose();
	    }

	    private void abrirGestionCategorias() {
	        Categoria cat = new Categoria();
	        new ScreenGCategorias(cat).setVisible(true);
	        this.dispose();
	    }

	    private void abrirGestionProductos() {
	        Producto pro = new Producto();
	        new ScreenGProductos(pro).setVisible(true);
	        this.dispose();
	    }

	    private void abrirInventario() {
	        // Lógica para abrir la pantalla de inventario
	        JOptionPane.showMessageDialog(this, "Abrir Inventario");
	    }

	    private void abrirCopiasSeguridad() {
	        // Lógica para abrir la pantalla de copias de seguridad
	        JOptionPane.showMessageDialog(this, "Abrir Copias de Seguridad");
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            new ScreenDashboardAdmin().setVisible(true);
	        });
		
	} // Close ventana
	

} // Class
