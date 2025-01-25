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
import controlador.Login;
import controlador.UsuarioSesion;
import modelo.Categoria;
import modelo.CopiaSeguridad;
import modelo.HistorialInventario;
import modelo.Producto;
import modelo.Usuario;

public class ScreenDashboardAdmin extends JFrame {

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

        // Panel principal
        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBackground(fondoColor);

        // Panel superior con el botón "Cerrar Sesión"
        JPanel panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.setBackground(fondoColor);

        GridBagConstraints gbcSuperior = new GridBagConstraints();
        gbcSuperior.insets = new Insets(10, 10, 10, 10); // Margen
        gbcSuperior.gridx = 0;
        gbcSuperior.gridy = 0;
        gbcSuperior.anchor = GridBagConstraints.WEST; // Botón alineado a la izquierda

        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        botonCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 14));
        botonCerrarSesion.setBackground(new Color(220, 53, 69)); // Color rojo
        botonCerrarSesion.setForeground(Color.WHITE);
        botonCerrarSesion.setFocusPainted(false);
        botonCerrarSesion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 40, 55), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        botonCerrarSesion.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estás seguro de que deseas cerrar sesión?",
                    "Confirmar Cierre de Sesión",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                UsuarioSesion.cerrarSesion(); // Limpia la sesión actual
                dispose(); // Cierra la ventana actual
                new ScreenLogin(new Login()).setVisible(true); // Redirige a la pantalla de inicio de sesión
            }
        });

        panelSuperior.add(botonCerrarSesion, gbcSuperior);

        // Añadir el panel superior al contenedor principal
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        contenedor.add(panelSuperior, gbc);

        // Panel del menú
        JPanel panelMenu = new JPanel(new GridBagLayout());
        panelMenu.setPreferredSize(new Dimension(1400, 1300));
        panelMenu.setBackground(fondoColor);
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Componentes
        JLabel tituloLabel = new JLabel("Dashboard Administrador", SwingConstants.CENTER);
        tituloLabel.setFont(fuenteTitulo);
        tituloLabel.setForeground(Color.DARK_GRAY);

        JButton gUsuarios = new JButton("Gestión de Usuarios");
        configBoton(gUsuarios, botonColor, textoBotonColor, fuenteBotones);

        JButton gCategorias = new JButton("Gestión de Categorías");
        configBoton(gCategorias, botonColor, textoBotonColor, fuenteBotones);

        JButton gProductos = new JButton("Gestión de Productos");
        configBoton(gProductos, botonColor, textoBotonColor, fuenteBotones);

        JButton inventario = new JButton("Inventario");
        configBoton(inventario, botonColor, textoBotonColor, fuenteBotones);

        JButton movInventario = new JButton("Movimientos de inventario");
        configBoton(movInventario, botonColor, textoBotonColor, fuenteBotones);

        JButton copiasSeguridad = new JButton("Copias de Seguridad");
        configBoton(copiasSeguridad, botonColor, textoBotonColor, fuenteBotones);
        
        JButton alertasStock = new JButton("Alertas de stock");
        configBoton(alertasStock, botonColor, textoBotonColor, fuenteBotones);

        // Acciones de los botones
        gUsuarios.addActionListener(e -> abrirGestionUsuarios());
        gCategorias.addActionListener(e -> abrirGestionCategorias());
        gProductos.addActionListener(e -> abrirGestionProductos());
        inventario.addActionListener(e -> abrirInventario());
        movInventario.addActionListener(e -> abrirMovInventario());
        copiasSeguridad.addActionListener(e -> abrirCopiasSeguridad());
        alertasStock.addActionListener(e -> abrirAlertasStock());

        // Añadir componentes al panel del menú
        gbc.gridy = 1; // Comienza después del panel superior
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

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
        panelMenu.add(movInventario, gbc);

        gbc.gridy++;
        panelMenu.add(copiasSeguridad, gbc);
        
        gbc.gridy++;
        panelMenu.add(alertasStock, gbc);

        // Añadir el panel del menú al contenedor principal
        gbc.gridy = 1;
        contenedor.add(panelMenu, gbc);

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
        Producto inv = new Producto();
        new ScreenGInventario(inv).setVisible(true);
        this.dispose();
    }

    private void abrirCopiasSeguridad() {
        CopiaSeguridad cop = new CopiaSeguridad();
        new ScreenGCopiasSeguridad(cop).setVisible(true);
        this.dispose();
    }

    private void abrirMovInventario() {
        HistorialInventario his = new HistorialInventario();
        new ScreenGHistorialInventario(his).setVisible(true);
        this.dispose();
    }
    
    private void abrirAlertasStock() {
        Producto pro = new Producto();
        new ScreenAlertasStock(pro).setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ScreenDashboardAdmin().setVisible(true);
        });
    }
}
