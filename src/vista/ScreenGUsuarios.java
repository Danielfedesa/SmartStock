package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import modelo.Usuario;

public class ScreenGUsuarios extends JFrame {

    private static final long serialVersionUID = 1L;
    private Usuario usuario; // Controlador que maneja la lógica relacionada con usuarios.
    private JTable tablaUsuarios; // Tabla para mostrar la lista de usuarios.

    public ScreenGUsuarios(Usuario usuario) {
        this.usuario = usuario;

        // Configuración básica de la ventana.
        setTitle("SmartStock - Gestión de Usuarios");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600));

        // Estilos de colores y fuentes.
        Color fondoColor = new Color(240, 240, 240);
        Color botonColor = new Color(70, 130, 180);
        Color textoBotonColor = Color.white;
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 24);
        Font fuenteBotones = new Font("Arial", Font.PLAIN, 16);

        // Configuración del fondo.
        getContentPane().setBackground(fondoColor);

        // Panel principal que contiene todos los componentes.
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(fondoColor);

     // Panel superior con el título y el botón "Volver atrás"
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(fondoColor);

        // Título de la pantalla
        JLabel tituloLabel = new JLabel("Gestión de Usuarios", JLabel.CENTER);
        tituloLabel.setFont(fuenteTitulo);
        tituloLabel.setForeground(Color.DARK_GRAY);

        // Botón "Volver atrás"
        JButton botonVolver = new JButton("Volver atrás");
        botonVolver.setFont(fuenteBotones);
        botonVolver.setBackground(botonColor);
        botonVolver.setForeground(textoBotonColor);
        botonVolver.setFocusPainted(false);
        botonVolver.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(botonColor.darker(), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        botonVolver.addActionListener(e -> {
            // Lógica para volver al dashboard
            new ScreenDashboardAdmin().setVisible(true); // Abre la pantalla del dashboard
            this.dispose(); // Cierra la pantalla actual
        });

        // Agregar el botón y el título al panel superior
        panelSuperior.add(botonVolver, BorderLayout.WEST);
        panelSuperior.add(tituloLabel, BorderLayout.CENTER);

        // Añadir el panel superior al contenedor principal
        contenedor.add(panelSuperior, BorderLayout.NORTH);

        panelSuperior.add(tituloLabel);

        // Modelo de la tabla con columnas específicas.
        String[] columnas = {"ID", "Nombre", "Apellido 1", "Apellido 2", "Teléfono", "Email", "Rol", "Editar", "Eliminar"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo las columnas de botones son editables.
                return column == 7 || column == 8;
            }
        };

        // Inicialización de la tabla de usuarios.
        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setRowHeight(30);
        tablaUsuarios.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaUsuarios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Rellenar la tabla con datos desde el backend.
        cargarDatosTabla(modeloTabla);

        // Renderizador y editor para el botón "Editar".
        tablaUsuarios.getColumnModel().getColumn(7).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JButton botonEditar = new JButton("Editar");
            botonEditar.setBackground(botonColor);
            botonEditar.setForeground(textoBotonColor);
            return botonEditar;
        });

     // Renderizador y editor para el botón "Editar".
        tablaUsuarios.getColumnModel().getColumn(7).setCellEditor(new javax.swing.DefaultCellEditor(new JTextField()) {
            @Override
            public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JButton botonEditar = new JButton("Editar");
                botonEditar.setBackground(botonColor);
                botonEditar.setForeground(textoBotonColor);
                botonEditar.addActionListener(e -> {
                    int idUsuario = Integer.parseInt(table.getValueAt(row, 0).toString());
                    abrirFormularioEdicion(idUsuario); // Abrir formulario de edición con el ID seleccionado.
                });
                return botonEditar;
            }
        });

     // Renderizador y editor para el botón "Eliminar".
        tablaUsuarios.getColumnModel().getColumn(8).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JButton botonEliminar = new JButton("Eliminar");
            botonEliminar.setBackground(Color.RED);
            botonEliminar.setForeground(Color.WHITE);
            return botonEliminar;
        });

        tablaUsuarios.getColumnModel().getColumn(8).setCellEditor(new javax.swing.DefaultCellEditor(new JTextField()) {
            @Override
            public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JButton botonEliminar = new JButton("Eliminar");
                botonEliminar.setBackground(Color.RED);
                botonEliminar.setForeground(Color.WHITE);

                botonEliminar.addActionListener(e -> {
                    int idUsuario = Integer.parseInt(table.getValueAt(row, 0).toString()); // Obtiene el ID del usuario.
                    int confirmacion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Estás seguro de que deseas eliminar este usuario?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        try {
                            usuario.eliminarUsuario(idUsuario); // Llama al método para eliminar el usuario.
                            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
                            
                            // Recargar la tabla con los datos actualizados.
                            DefaultTableModel modeloTabla = (DefaultTableModel) tablaUsuarios.getModel();
                            modeloTabla.setRowCount(0); // Limpia la tabla.
                            cargarDatosTabla(modeloTabla); // Carga los datos actualizados.
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                return botonEliminar;
            }
        });
        
        // Panel con la tabla.
        JScrollPane scrollTabla = new JScrollPane(tablaUsuarios);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel inferior con el botón para añadir usuarios.
        JPanel panelInferior = new JPanel(new GridBagLayout());
        panelInferior.setBackground(fondoColor);

        JButton botonAñadir = new JButton("Añadir Usuario");
        botonAñadir.setFont(fuenteBotones);
        botonAñadir.setBackground(botonColor);
        botonAñadir.setForeground(textoBotonColor);
        botonAñadir.setFocusPainted(false);
        botonAñadir.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(botonColor.darker(), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        botonAñadir.addActionListener(e -> {
            abrirFormularioAñadir(); // Llama al formulario de añadir usuario.
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        panelInferior.add(botonAñadir, gbc);

        // Agregar paneles al contenedor principal.
        contenedor.add(panelSuperior, BorderLayout.NORTH);
        contenedor.add(scrollTabla, BorderLayout.CENTER);
        contenedor.add(panelInferior, BorderLayout.SOUTH);

        // Añadir el contenedor a la ventana.
        add(contenedor);
    }

    // Método para cargar datos en la tabla desde la base de datos.
    private void cargarDatosTabla(DefaultTableModel modeloTabla) {
        try {
            List<Usuario> usuarios = usuario.listarUsuarios(); // Obtener la lista de usuarios.
            for (Usuario usuario : usuarios) {
                modeloTabla.addRow(new Object[]{
                    String.valueOf(usuario.getIdUsuario()), // Convertir el ID a String.
                    usuario.getNombreUsuario(),
                    usuario.getApellido1(),
                    usuario.getApellido2(),
                    String.valueOf(usuario.getTelefono()), // Convertir el teléfono a String.
                    usuario.getEmail(),
                    usuario.getRol(),
                    "Editar",
                    "Eliminar"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los usuarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para abrir el formulario de edición de un usuario.
    private void abrirFormularioEdicion(int idUsuario) {
        try {
            Usuario usuarioEditar = usuario.recuperarUsu(idUsuario); // Recuperar datos del usuario.
            JFrame formularioEdicion = new JFrame("Editar Usuario");
            formularioEdicion.setSize(400, 600);
            formularioEdicion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            formularioEdicion.setLocationRelativeTo(null);

            JPanel panelFormulario = new JPanel(new GridBagLayout());
            panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Campos del formulario para editar los datos.
            JLabel nombreLabel = new JLabel("Nombre:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelFormulario.add(nombreLabel, gbc);
            
            Dimension campoTamanio = new Dimension(200, 25); // Tamaño para los campos de texto del formulario.

            JTextField nombreField = new JTextField(usuarioEditar.getNombreUsuario());
            nombreField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panelFormulario.add(nombreField, gbc);

            JLabel apellido1Label = new JLabel("Apellido 1:"); 
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelFormulario.add(apellido1Label, gbc);

            JTextField apellido1Field = new JTextField(usuarioEditar.getApellido1());
            apellido1Field.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panelFormulario.add(apellido1Field, gbc);

            JLabel apellido2Label = new JLabel("Apellido 2:");
            gbc.gridx = 0;
            gbc.gridy = 2;
            panelFormulario.add(apellido2Label, gbc);

            JTextField apellido2Field = new JTextField(usuarioEditar.getApellido2());
            apellido2Field.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 2;
            panelFormulario.add(apellido2Field, gbc);

            JLabel telefonoLabel = new JLabel("Teléfono:");
            gbc.gridx = 0;
            gbc.gridy = 3;
            panelFormulario.add(telefonoLabel, gbc);

            JTextField telefonoField = new JTextField(String.valueOf(usuarioEditar.getTelefono()));
            telefonoField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 3;
            panelFormulario.add(telefonoField, gbc);

            JLabel emailLabel = new JLabel("Email:");
            gbc.gridx = 0;
            gbc.gridy = 4;
            panelFormulario.add(emailLabel, gbc);

            JTextField emailField = new JTextField(usuarioEditar.getEmail());
            emailField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 4;
            panelFormulario.add(emailField, gbc);

            JLabel rolLabel = new JLabel("Rol:");
            gbc.gridx = 0;
            gbc.gridy = 5;
            panelFormulario.add(rolLabel, gbc);

            JTextField rolField = new JTextField(usuarioEditar.getRol());
            rolField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 5;
            panelFormulario.add(rolField, gbc);

            JButton aplicarCambios = new JButton("Aplicar Cambios");
            aplicarCambios.addActionListener(e -> {
                try {
                    usuarioEditar.setNombreUsuario(nombreField.getText());
                    usuarioEditar.setApellido1(apellido1Field.getText());
                    usuarioEditar.setApellido2(apellido2Field.getText());
                    usuarioEditar.setTelefono(Integer.parseInt(telefonoField.getText()));
                    usuarioEditar.setEmail(emailField.getText());
                    usuarioEditar.setRol(rolField.getText());

                    usuarioEditar.actualizarUsuario(); // Llama al método para actualizar el usuario en la base de datos.
                    JOptionPane.showMessageDialog(formularioEdicion, "Usuario actualizado correctamente.");
                    formularioEdicion.dispose();

                    // Recargar la tabla con los datos actualizados.
                    DefaultTableModel modeloTabla = (DefaultTableModel) tablaUsuarios.getModel();
                    modeloTabla.setRowCount(0); // Limpia la tabla.
                    cargarDatosTabla(modeloTabla); // Carga los datos actualizados.

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioEdicion, "Error al actualizar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 2;
            panelFormulario.add(aplicarCambios, gbc);

            formularioEdicion.add(panelFormulario);
            formularioEdicion.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
	 // Método para abrir el formulario de añadir usuario
	    private void abrirFormularioAñadir() {
	        JFrame formularioInsertar = new JFrame("Añadir Usuario");
	        formularioInsertar.setSize(400, 600);
	        formularioInsertar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        formularioInsertar.setLocationRelativeTo(null);
	
	        JPanel panelFormulario = new JPanel(new GridBagLayout());
	        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(10, 10, 10, 10);
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        
	        Dimension campoTamanio = new Dimension(200, 25); // Tamaño para los campos de texto del formulario.
	
	        // Campos del formulario
	        JLabel nombreLabel = new JLabel("Nombre:");
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        panelFormulario.add(nombreLabel, gbc);
	
	        JTextField nombreField = new JTextField();
	        nombreField.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 0;
	        panelFormulario.add(nombreField, gbc);
	
	        JLabel apellido1Label = new JLabel("Apellido 1:");
	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        panelFormulario.add(apellido1Label, gbc);
	
	        JTextField apellido1Field = new JTextField();
	        apellido1Field.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 1;
	        panelFormulario.add(apellido1Field, gbc);
	
	        JLabel apellido2Label = new JLabel("Apellido 2:");
	        gbc.gridx = 0;
	        gbc.gridy = 2;
	        panelFormulario.add(apellido2Label, gbc);
	
	        JTextField apellido2Field = new JTextField();
	        apellido2Field.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 2;
	        panelFormulario.add(apellido2Field, gbc);
	
	        JLabel telefonoLabel = new JLabel("Teléfono:");
	        gbc.gridx = 0;
	        gbc.gridy = 3;
	        panelFormulario.add(telefonoLabel, gbc);
	
	        JTextField telefonoField = new JTextField();
	        telefonoField.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 3;
	        panelFormulario.add(telefonoField, gbc);
	
	        JLabel emailLabel = new JLabel("Email:");
	        gbc.gridx = 0;
	        gbc.gridy = 4;
	        panelFormulario.add(emailLabel, gbc);
	
	        JTextField emailField = new JTextField();
	        emailField.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 4;
	        panelFormulario.add(emailField, gbc);
	
	        JLabel contrasenaLabel = new JLabel("Contraseña:");
	        gbc.gridx = 0;
	        gbc.gridy = 5;
	        panelFormulario.add(contrasenaLabel, gbc);
	
	        JTextField contrasenaField = new JTextField();
	        contrasenaField.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 5;
	        panelFormulario.add(contrasenaField, gbc);
	
	        JLabel rolLabel = new JLabel("Rol:");
	        gbc.gridx = 0;
	        gbc.gridy = 6;
	        panelFormulario.add(rolLabel, gbc);
	
	        JTextField rolField = new JTextField();
	        rolField.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 6;
	        panelFormulario.add(rolField, gbc);
	
	        // Botón para aplicar los cambios
	        JButton aplicarCambios = new JButton("Aplicar Cambios");
	        aplicarCambios.addActionListener(e -> {
	            try {
	                Usuario nuevoUsuario = new Usuario();
	                nuevoUsuario.setNombreUsuario(nombreField.getText());
	                nuevoUsuario.setApellido1(apellido1Field.getText());
	                nuevoUsuario.setApellido2(apellido2Field.getText());
	                nuevoUsuario.setTelefono(Integer.parseInt(telefonoField.getText()));
	                nuevoUsuario.setEmail(emailField.getText());
	                nuevoUsuario.setContrasena(contrasenaField.getText());
	                nuevoUsuario.setRol(rolField.getText());
	
	                nuevoUsuario.crearUsuario(); // Llama al método para crear el usuario en la base de datos.
	                JOptionPane.showMessageDialog(formularioInsertar, "Usuario creado correctamente.");
	                formularioInsertar.dispose();
	
	                // Recargar la tabla con los datos actualizados.
	                DefaultTableModel modeloTabla = (DefaultTableModel) tablaUsuarios.getModel();
	                modeloTabla.setRowCount(0); // Limpia la tabla.
	                cargarDatosTabla(modeloTabla); // Carga los datos actualizados.
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(formularioInsertar, "Error al crear el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        });
	
	        gbc.gridx = 0;
	        gbc.gridy = 7;
	        gbc.gridwidth = 2;
	        panelFormulario.add(aplicarCambios, gbc);
	
	        formularioInsertar.add(panelFormulario);
	        formularioInsertar.setVisible(true);
	    }

	   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Usuario usuario = new Usuario(); // Instancia de Usuario que gestiona la lógica.
            new ScreenGUsuarios(usuario).setVisible(true);
        });
        
    }
   
}