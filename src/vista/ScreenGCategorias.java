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

import modelo.Categoria;

public class ScreenGCategorias extends JFrame {

    private static final long serialVersionUID = 1L;
    private Categoria categoria; // Controlador que maneja la lógica relacionada con usuarios.
    private JTable tablaCategorias; // Tabla para mostrar la lista de usuarios.

    public ScreenGCategorias(Categoria categoria) {
        this.categoria = categoria;

        // Configuración básica de la ventana.
        setTitle("SmartStock - Gestión de Categorias");
        setSize(1350, 600);
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
        JPanel panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.setBackground(fondoColor);

        GridBagConstraints gbcSuperior = new GridBagConstraints();
        gbcSuperior.insets = new Insets(10, 10, 10, 10); // Margen entre componentes
        gbcSuperior.fill = GridBagConstraints.HORIZONTAL; // Ajuste horizontal

        // Título de la pantalla
        JLabel tituloLabel = new JLabel("Gestión de Categorias", JLabel.CENTER);
        tituloLabel.setFont(fuenteTitulo);
        tituloLabel.setForeground(Color.DARK_GRAY);
        gbcSuperior.gridx = 0;
        gbcSuperior.gridy = 0;
        gbcSuperior.gridwidth = 2; // Ocupa dos columnas para centrar
        panelSuperior.add(tituloLabel, gbcSuperior);

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
        gbcSuperior.gridx = 0;
        gbcSuperior.gridy = 1; // Debajo del título
        gbcSuperior.gridwidth = 2; // Ocupa solo una columna
        gbcSuperior.anchor = GridBagConstraints.CENTER; // Centrar horizontalmente
        panelSuperior.add(botonVolver, gbcSuperior);

        // Añadir el panel superior al contenedor principal
        contenedor.add(panelSuperior, BorderLayout.NORTH);

        // Modelo de la tabla con columnas específicas.
        String[] columnas = {"ID", "Nombre", "Descripción", "Editar", "Eliminar"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                // Solo las columnas de botones son editables.
                return column == 3 || column == 4;
            }
        };

        // Inicialización de la tabla de usuarios.
        tablaCategorias = new JTable(modeloTabla);
        tablaCategorias.setRowHeight(30);
        tablaCategorias.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaCategorias.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
     // Configuración de ancho fijo para las columnas
        tablaCategorias.getColumnModel().getColumn(0).setPreferredWidth(10);  // Columna ID
        tablaCategorias.getColumnModel().getColumn(1).setPreferredWidth(100); // Columna Nombre
        tablaCategorias.getColumnModel().getColumn(2).setPreferredWidth(700); // Columna Descripcion
        tablaCategorias.getColumnModel().getColumn(3).setPreferredWidth(20); // Botón Editar
        tablaCategorias.getColumnModel().getColumn(4).setPreferredWidth(20); // Botón Eliminar

        // Rellenar la tabla con datos desde el backend.
        cargarDatosTabla(modeloTabla);

        // Renderizador y editor para el botón "Editar".
        tablaCategorias.getColumnModel().getColumn(3).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JButton botonEditar = new JButton("Editar");
            botonEditar.setBackground(botonColor);
            botonEditar.setForeground(textoBotonColor);
            return botonEditar;
        });

     // Renderizador y editor para el botón "Editar".
        tablaCategorias.getColumnModel().getColumn(3).setCellEditor(new javax.swing.DefaultCellEditor(new JTextField()) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JButton botonEditar = new JButton("Editar");
                botonEditar.setBackground(botonColor);
                botonEditar.setForeground(textoBotonColor);
                botonEditar.addActionListener(e -> {
                    int idCategoria = Integer.parseInt(table.getValueAt(row, 0).toString());
                    abrirFormularioEdicion(idCategoria); // Abrir formulario de edición con el ID seleccionado.
                });
                return botonEditar;
            }
        });

     // Renderizador y editor para el botón "Eliminar".
        tablaCategorias.getColumnModel().getColumn(4).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JButton botonEliminar = new JButton("Eliminar");
            botonEliminar.setBackground(Color.RED);
            botonEliminar.setForeground(Color.WHITE);
            return botonEliminar;
        });

        tablaCategorias.getColumnModel().getColumn(4).setCellEditor(new javax.swing.DefaultCellEditor(new JTextField()) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JButton botonEliminar = new JButton("Eliminar");
                botonEliminar.setBackground(Color.RED);
                botonEliminar.setForeground(Color.WHITE);

                botonEliminar.addActionListener(e -> {
                    int idCategoria = Integer.parseInt(table.getValueAt(row, 0).toString()); // Obtiene el ID del usuario.
                    int confirmacion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Estás seguro de que deseas eliminar esta categoria?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        try {
                            categoria.eliminarCategoria(idCategoria); // Llama al método para eliminar el usuario.
                            JOptionPane.showMessageDialog(null, "Categoria eliminada correctamente.");
                            
                            // Recargar la tabla con los datos actualizados.
                            DefaultTableModel modeloTabla = (DefaultTableModel) tablaCategorias.getModel();
                            modeloTabla.setRowCount(0); // Limpia la tabla.
                            cargarDatosTabla(modeloTabla); // Carga los datos actualizados.
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al eliminar la categoria: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                return botonEliminar;
            }
        });
        
        // Panel con la tabla.
        JScrollPane scrollTabla = new JScrollPane(tablaCategorias);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(100, 100, 50, 100));

        // Panel inferior con el botón para añadir usuarios.
        JPanel panelInferior = new JPanel(new GridBagLayout());
        panelInferior.setBackground(fondoColor);

        JButton botonAñadir = new JButton("Añadir Categoria");
        botonAñadir.setFont(fuenteBotones);
        botonAñadir.setBackground(botonColor);
        botonAñadir.setForeground(textoBotonColor);
        botonAñadir.setFocusPainted(false);
        botonAñadir.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(botonColor.darker(), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        botonAñadir.addActionListener(e -> {
            abrirFormularioAñadir(); // Llama al formulario de añadir categoria.
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 100, 10);
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
            List<Categoria> categorias = categoria.listarCategorias(); // Obtener la lista de categorias.
            for (Categoria categoria : categorias) {
                modeloTabla.addRow(new Object[]{
                    String.valueOf(categoria.getIdCategoria()), // Convertir el ID a String.
                    categoria.getNombreCategoria(),
                    categoria.getDescripcion(),
                    "Editar",
                    "Eliminar"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las categorias: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para abrir el formulario de edición de una categoria.
    private void abrirFormularioEdicion(int idCategoria) {
        try {
            Categoria categoriaEditar = categoria.recuperarCat(idCategoria); // Recuperar datos de la categoria.
            JFrame formularioEdicion = new JFrame("Editar Categoria");
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

            JTextField nombreField = new JTextField(categoriaEditar.getNombreCategoria());
            nombreField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panelFormulario.add(nombreField, gbc);

            JLabel descripcionLabel = new JLabel("Descripción:"); 
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelFormulario.add(descripcionLabel, gbc);

            JTextField descripcionField = new JTextField(categoriaEditar.getDescripcion());
            descripcionField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panelFormulario.add(descripcionField, gbc);

            JButton aplicarCambios = new JButton("Aplicar Cambios");
            aplicarCambios.addActionListener(e -> {
                try {
                	categoriaEditar.setNombreCategoria(nombreField.getText());
                	categoriaEditar.setDescripcion(descripcionField.getText());

                	categoriaEditar.actualizarCategoria(); // Llama al método para actualizar la categoria en la base de datos.
                    JOptionPane.showMessageDialog(formularioEdicion, "Categoria actualizada correctamente.");
                    formularioEdicion.dispose();

                    // Recargar la tabla con los datos actualizados.
                    DefaultTableModel modeloTabla = (DefaultTableModel) tablaCategorias.getModel();
                    modeloTabla.setRowCount(0); // Limpia la tabla.
                    cargarDatosTabla(modeloTabla); // Carga los datos actualizados.

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioEdicion, "Error al actualizar la categoria: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 2;
            panelFormulario.add(aplicarCambios, gbc);

            formularioEdicion.add(panelFormulario);
            formularioEdicion.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de la categoria: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
	 // Método para abrir el formulario de añadir usuario
	    private void abrirFormularioAñadir() {
	        JFrame formularioInsertar = new JFrame("Añadir Categoria");
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
	
	        JLabel descripcionLabel = new JLabel("Descripcion:");
	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        panelFormulario.add(descripcionLabel, gbc);
	
	        JTextField descripcionField = new JTextField();
	        descripcionField.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 1;
	        panelFormulario.add(descripcionField, gbc);

	        // Botón para aplicar los cambios
	        JButton aplicarCambios = new JButton("Enviar formulario");
	        aplicarCambios.addActionListener(e -> {
	            try {
	                Categoria nuevaCategoria = new Categoria();
	                nuevaCategoria.setNombreCategoria(nombreField.getText());
	                nuevaCategoria.setDescripcion(descripcionField.getText());
	
	                nuevaCategoria.crearCategoria(); // Llama al método para crear la categoria en la base de datos.
	                JOptionPane.showMessageDialog(formularioInsertar, "Categoria creada correctamente.");
	                formularioInsertar.dispose();
	
	                // Recargar la tabla con los datos actualizados.
	                DefaultTableModel modeloTabla = (DefaultTableModel) tablaCategorias.getModel();
	                modeloTabla.setRowCount(0); // Limpia la tabla.
	                cargarDatosTabla(modeloTabla); // Carga los datos actualizados.
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(formularioInsertar, "Error al crear la categoria: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            Categoria categoria = new Categoria(); // Instancia de Categoria que gestiona la lógica.
            new ScreenGCategorias(categoria).setVisible(true);
        });
        
    }
   
}