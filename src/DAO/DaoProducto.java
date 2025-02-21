package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ConexionDB;
import modelo.Producto;

/**
 * Clase para realizar operaciones relacionadas con
 * la tabla 'pruductos' en la base de datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 01/2025
 */
public class DaoProducto {

	/**
	 * Atributo estatico que gestiona la conexion a la base de datos.
	 * en toda la aplicacion, permitiendo la reutilizacion de la misma conexion.
	 * Inicialmente la instancia esta establecida en null.
	 * @see java.sql.Connection
	 */
	// Genera un atributo de tipo Connection llamado "con" y la instancia en null:
	public static Connection con = null;
	
	/**
	 * Constructor de la clase DaoProducto para establecer conexion con la base de datos.
	 * Crea el objeto DaoProducto y el atributo "con"
	 * conecta a ConexionDB.getConexion() automaticamente.
	 * @throws SQLException Si hay un error de conexion.
	 */
	@SuppressWarnings("static-access")
	public DaoProducto() throws SQLException {
		this.con = ConexionDB.getConexion();
	}
	
	/**
	 * Metodo para insertar un nuevo producto en la base de datos. 
	 * @param p Objeto Producto para insertar.
	 * @throws SQLException Si hay un error de insercion en base de datos.
	 */
	public void insertar(Producto p) throws SQLException {
		String sql = "INSERT INTO productos (nombre_Producto, descripcion, precio, stock, stock_Minimo, id_Categoria)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, p.getNombreProducto());
		ps.setString(2, p.getDescripcion());
		ps.setDouble(3, p.getPrecio());
		ps.setInt(4, p.getStock());
		ps.setInt(5, p.getStockMinimo());
		ps.setInt(6, p.getIdCategoria());
		
		@SuppressWarnings("unused")
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
	/**
	 * Metodo para listar todos los productos de la base de datos.
	 * @return ArrayList de Producto con los datos de los productos.
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	public ArrayList<Producto> listar() throws SQLException {
		
		String sql = "SELECT * FROM productos";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Producto> result = null;
		
		while (rs.next()) {
			if (result == null) {
				result = new ArrayList<Producto>();
			}
			
			result.add(new Producto(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
		}
		
		ps.close();
		
		return result;
	}
	
	/**
	 * Metodo para listar todos los productos con stock bajo de la base de datos.
	 * @return ArrayList de Producto con los datos de los productos.
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	public ArrayList<Producto> listarStockBajo() throws SQLException {
		
		String sql = "SELECT * FROM productos WHERE stock < stock_Minimo";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Producto> result = null;
		
		while (rs.next()) {
			if (result == null) {
				result = new ArrayList<Producto>();
			}
			
			result.add(new Producto(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
		}
		
		ps.close();
		
		return result;
	}
	
	/**
	 * Metodo para leer un producto de la base de datos por su ID y modificarlo
	 * posteriormente.
	 * @param idProducto ID del producto a leer.
	 * @return Objeto Producto con los datos del producto leidos.
	 * @throws SQLException Si hay un error de lectura
	 */
	public Producto leerProducto(int idProducto) throws SQLException {
		
		String sql = "SELECT * FROM productos WHERE id_Producto = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idProducto);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Producto p = new Producto(rs.getInt(1), rs.getString(2), rs.getString(3),
				rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
	
	ps.close();
	
	return p;
	
	}
	
	/**
	 * Metodo para insertar la modificacion de un producto en la base de datos. 
	 * @param prod Objeto Producto con los datos actualizados.
	 * @return true si la actualizacion fue correcta, false si esta falla.
	 */
	public boolean actualizarProducto(Producto prod) {
		
		PreparedStatement ps = null;

	    try {
	        // Sentencia SQL para actualizar un producto en la base de datos
	        String sql = "UPDATE productos SET nombre_Producto = ?, descripcion = ?, "
	                + "precio = ?, stock = ?, stock_Minimo = ?, id_Categoria = ? WHERE id_Producto = ?";

	        ps = con.prepareStatement(sql);

	        ps.setString(1, prod.getNombreProducto());
	        ps.setString(2, prod.getDescripcion());
	        ps.setDouble(3, prod.getPrecio());
	        ps.setInt(4, prod.getStock());
	        ps.setInt(5, prod.getStockMinimo());
	        ps.setInt(6, prod.getIdCategoria());
	        ps.setInt(7, prod.getIdProducto());

	        int rs = ps.executeUpdate();

	        return rs == 1;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // Si ocurre un error
	    } finally {
	        // Cerrar el PreparedStatement si fue inicializado
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	/**
	 * Metodo para eliminar un producto en la base de datos
	 * identificado por su ID. Si el producto no existe, no se realiza ninguna accion.
	 * 
	 * @param idProducto El ID del producto que se desea eliminar.
	 * @throws SQLException Si ocurre un error al ejecutar la sentencia SQL.
	 */
	public static void borrarProducto(int idProducto) throws SQLException {
		
		String sql = "DELETE FROM productos WHERE id_Producto = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idProducto);
		
		@SuppressWarnings("unused")
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
	/**
     * Metodo para listar todos los productos con stock inferior al minimo.
     * @return ArrayList de Producto con los productos que tienen stock bajo.
     * @throws SQLException Si ocurre un error al ejecutar la consulta.
     */
	public ArrayList<Producto> listarMinimo() throws SQLException {
	    String sql = "SELECT * FROM productos WHERE stock < stock_Minimo";
	    ArrayList<Producto> result = new ArrayList<>(); // Inicialización de la lista

	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();

	    while (rs.next()) {
	        // Agregar productos encontrados a la lista
	        result.add(new Producto(rs.getInt(1), rs.getString(2), rs.getString(3),
	                rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
	    }

	    ps.close();

	    return result;
	}
	
} // Class
