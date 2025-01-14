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

import modelo.Producto;

public class ScreenGProductos extends JFrame {

    private static final long serialVersionUID = 1L;
    private Producto producto; // Controlador que maneja la lógica relacionada con productos.
    private JTable tablaProductos; // Tabla para mostrar la lista de productos.

    public ScreenGProductos(Producto producto) {
        this.producto = producto;

        // Configuración básica de la ventana.
        setTitle("SmartStock - Gestión de Productos");
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
        JLabel tituloLabel = new JLabel("Gestión de Productos", JLabel.CENTER);
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
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Stock", "Stock MIN", "ID Cat", "Editar", "Eliminar"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                // Solo las columnas de botones son editables.
                return column == 7 || column == 8;
            }
        };

        // Inicialización de la tabla de productos.
        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setRowHeight(30);
        tablaProductos.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaProductos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
     // Configuración de ancho fijo para las columnas
        tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(10);  // Columna ID
        tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(200); // Columna Nombre
        tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(400); // Columna Descripcion
        tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(20); // Columna Precio
        tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(20); // Columna Stock
        tablaProductos.getColumnModel().getColumn(5).setPreferredWidth(20); // Columna Stock Minimo
        tablaProductos.getColumnModel().getColumn(6).setPreferredWidth(10); // Columna ID Categoria
        tablaProductos.getColumnModel().getColumn(7).setPreferredWidth(40); // Botón Editar
        tablaProductos.getColumnModel().getColumn(8).setPreferredWidth(40); // Botón Eliminar

        // Rellenar la tabla con datos desde el backend.
        cargarDatosTabla(modeloTabla);

        // Renderizador y editor para el botón "Editar".
        tablaProductos.getColumnModel().getColumn(7).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JButton botonEditar = new JButton("Editar");
            botonEditar.setBackground(botonColor);
            botonEditar.setForeground(textoBotonColor);
            return botonEditar;
        });

     // Renderizador y editor para el botón "Editar".
        tablaProductos.getColumnModel().getColumn(7).setCellEditor(new javax.swing.DefaultCellEditor(new JTextField()) {
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
                    int idProducto = Integer.parseInt(table.getValueAt(row, 0).toString());
                    abrirFormularioEdicion(idProducto); // Abrir formulario de edición con el ID seleccionado.
                });
                return botonEditar;
            }
        });

     // Renderizador y editor para el botón "Eliminar".
        tablaProductos.getColumnModel().getColumn(8).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JButton botonEliminar = new JButton("Eliminar");
            botonEliminar.setBackground(Color.RED);
            botonEliminar.setForeground(Color.WHITE);
            return botonEliminar;
        });

        tablaProductos.getColumnModel().getColumn(8).setCellEditor(new javax.swing.DefaultCellEditor(new JTextField()) {
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
                    int idProducto = Integer.parseInt(table.getValueAt(row, 0).toString()); // Obtiene el ID del producto.
                    int confirmacion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Estás seguro de que deseas eliminar este producto?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        try {
                            producto.eliminarProducto(idProducto); // Llama al método para eliminar el producto.
                            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                            
                            // Recargar la tabla con los datos actualizados.
                            DefaultTableModel modeloTabla = (DefaultTableModel) tablaProductos.getModel();
                            modeloTabla.setRowCount(0); // Limpia la tabla.
                            cargarDatosTabla(modeloTabla); // Carga los datos actualizados.
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                return botonEliminar;
            }
        });
        
        // Panel con la tabla.
        JScrollPane scrollTabla = new JScrollPane(tablaProductos);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(100, 90, 20, 90));

        // Panel inferior con el botón para añadir usuarios.
        JPanel panelInferior = new JPanel(new GridBagLayout());
        panelInferior.setBackground(fondoColor);

        JButton botonAñadir = new JButton("Añadir producto");
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
            List<Producto> productos = producto.listarProductos();
            for (Producto producto : productos) {
                modeloTabla.addRow(new Object[]{
                    String.valueOf(producto.getIdProducto()), // Convertir el ID a String.
                    producto.getNombreProducto(),
                    producto.getDescripcion(),
                    String.valueOf(producto.getPrecio()),
                    String.valueOf(producto.getStock()),
                    String.valueOf(producto.getStockMinimo()),
                    String.valueOf(producto.getIdCategoria()),
                    "Editar",
                    "Eliminar"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para abrir el formulario de edición de un producto.
    private void abrirFormularioEdicion(int idProducto) {
        try {
            Producto productoEditar = producto.recuperarPro(idProducto); // Recuperar datos del usuario.
            JFrame formularioEdicion = new JFrame("Editar Producto");
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

            JTextField nombreField = new JTextField(productoEditar.getNombreProducto());
            nombreField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panelFormulario.add(nombreField, gbc);

            JLabel descripcionLabel = new JLabel("Descripción:"); 
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelFormulario.add(descripcionLabel, gbc);

            JTextField descripcionField = new JTextField(productoEditar.getDescripcion());
            descripcionField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panelFormulario.add(descripcionField, gbc);

            JLabel precioLabel = new JLabel("Precio:");
            gbc.gridx = 0;
            gbc.gridy = 2;
            panelFormulario.add(precioLabel, gbc);

            JTextField precioField = new JTextField(String.valueOf(productoEditar.getPrecio()));
            precioField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 2;
            panelFormulario.add(precioField, gbc);

            JLabel stockLabel = new JLabel("Stock:");
            gbc.gridx = 0;
            gbc.gridy = 3;
            panelFormulario.add(stockLabel, gbc);

            JTextField stockField = new JTextField(String.valueOf(productoEditar.getStock()));
            stockField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 3;
            panelFormulario.add(stockField, gbc);

            JLabel stockMinLabel = new JLabel("Stock mínimo:");
            gbc.gridx = 0;
            gbc.gridy = 4;
            panelFormulario.add(stockMinLabel, gbc);

            JTextField stockMinField = new JTextField(String.valueOf(productoEditar.getStockMinimo()));
            stockMinField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 4;
            panelFormulario.add(stockMinField, gbc);

            JLabel idCategoriaLabel = new JLabel("ID Cat:");
            gbc.gridx = 0;
            gbc.gridy = 5;
            panelFormulario.add(idCategoriaLabel, gbc);

            JTextField idCategoriaField = new JTextField(String.valueOf(productoEditar.getIdCategoria()));
            idCategoriaField.setPreferredSize(campoTamanio);
            gbc.gridx = 1;
            gbc.gridy = 5;
            panelFormulario.add(idCategoriaField, gbc);

            JButton aplicarCambios = new JButton("Aplicar Cambios");
            aplicarCambios.addActionListener(e -> {
                try {
                	productoEditar.setNombreProducto(nombreField.getText());
                	productoEditar.setDescripcion(descripcionField.getText());
                	productoEditar.setPrecio(Double.parseDouble(precioField.getText()));
                	productoEditar.setStock(Integer.parseInt(stockField.getText()));
                	productoEditar.setStockMinimo(Integer.parseInt(stockMinField.getText()));
                	productoEditar.setIdCategoria(Integer.parseInt(idCategoriaField.getText()));


                	productoEditar.actualizarProducto(); // Llama al método para actualizar el producto en la base de datos.
                    JOptionPane.showMessageDialog(formularioEdicion, "Producto actualizado correctamente.");
                    formularioEdicion.dispose();

                    // Recargar la tabla con los datos actualizados.
                    DefaultTableModel modeloTabla = (DefaultTableModel) tablaProductos.getModel();
                    modeloTabla.setRowCount(0); // Limpia la tabla.
                    cargarDatosTabla(modeloTabla); // Carga los datos actualizados.

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioEdicion, "Error al actualizar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 2;
            panelFormulario.add(aplicarCambios, gbc);

            formularioEdicion.add(panelFormulario);
            formularioEdicion.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos del producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
	 // Método para abrir el formulario de añadir producto
	    private void abrirFormularioAñadir() {
	        JFrame formularioInsertar = new JFrame("Añadir Producto");
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
	
	        JLabel descripcionLabel = new JLabel("Descripción:");
	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        panelFormulario.add(descripcionLabel, gbc);
	
	        JTextField descripcionField = new JTextField();
	        descripcionField.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 1;
	        panelFormulario.add(descripcionField, gbc);
	
	        JLabel precioLabel = new JLabel("Precio:");
	        gbc.gridx = 0;
	        gbc.gridy = 2;
	        panelFormulario.add(precioLabel, gbc);
	
	        JTextField precioField = new JTextField();
	        precioField.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 2;
	        panelFormulario.add(precioField, gbc);
	
	        JLabel stockLabel = new JLabel("Stock:");
	        gbc.gridx = 0;
	        gbc.gridy = 3;
	        panelFormulario.add(stockLabel, gbc);
	
	        JTextField stockField = new JTextField();
	        stockField.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 3;
	        panelFormulario.add(stockField, gbc);
	
	        JLabel stockMinLabel = new JLabel("Stock mínimo:");
	        gbc.gridx = 0;
	        gbc.gridy = 4;
	        panelFormulario.add(stockMinLabel, gbc);
	
	        JTextField stockMinField = new JTextField();
	        stockMinField.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 4;
	        panelFormulario.add(stockMinField, gbc);
	
	        JLabel idCategoriaLabel = new JLabel("id Cat:");
	        gbc.gridx = 0;
	        gbc.gridy = 5;
	        panelFormulario.add(idCategoriaLabel, gbc);
	
	        JTextField idCategoriaField = new JTextField();
	        idCategoriaField.setPreferredSize(campoTamanio);
	        gbc.gridx = 1;
	        gbc.gridy = 5;
	        panelFormulario.add(idCategoriaField, gbc);
	
	        // Botón para aplicar los cambios
	        JButton aplicarCambios = new JButton("Enviar formulario");
	        aplicarCambios.addActionListener(e -> {
	            try {
	                Producto nuevoProducto = new Producto();
	                
	                nuevoProducto.setNombreProducto(nombreField.getText());
	                nuevoProducto.setDescripcion(descripcionField.getText());
	                nuevoProducto.setPrecio(Double.parseDouble(precioField.getText()));
	                nuevoProducto.setStock(Integer.parseInt(stockField.getText()));
	                nuevoProducto.setStockMinimo(Integer.parseInt(stockMinField.getText()));
	                nuevoProducto.setIdCategoria(Integer.parseInt(idCategoriaField.getText()));
	
	                nuevoProducto.crearProducto(); // Llama al método para crear el producto en la base de datos.
	                JOptionPane.showMessageDialog(formularioInsertar, "Producto creado correctamente.");
	                formularioInsertar.dispose();
	
	                // Recargar la tabla con los datos actualizados.
	                DefaultTableModel modeloTabla = (DefaultTableModel) tablaProductos.getModel();
	                modeloTabla.setRowCount(0); // Limpia la tabla.
	                cargarDatosTabla(modeloTabla); // Carga los datos actualizados.
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(formularioInsertar, "Error al crear el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            Producto producto = new Producto(); // Instancia de Usuario que gestiona la lógica.
            new ScreenGProductos(producto).setVisible(true);
        });
        
    }
   
}