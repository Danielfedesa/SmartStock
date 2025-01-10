package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ConexionDB;
import modelo.Categoria;

/**
 * Clase para realizar operaciones relacionadas con
 * la tabla 'categorias' en la base de datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 01/2025
 */
public class DaoCategoria {

	/**
	 * Atributo estatico que gestiona la conexion a la base de datos.
	 * en toda la aplicacion, permitiendo la reutilizacion de la misma conexion.
	 * Inicialmente la instancia esta establecida en null.
	 * @see java.sql.Connection
	 */
	// Genera un atributo de tipo Connection llamado "con" y la instancia en null:
	public static Connection con = null;
	
	/**
	 * Constructor de la clase DaoCategoria para establecer conexion con la base de datos.
	 * Crea el objeto DaoCategoria y el atributo "con"
	 * conecta a ConexionDB.getConexion() automaticamente.
	 * @throws SQLException Si hay un error de conexion.
	 */
	@SuppressWarnings("static-access")
	public DaoCategoria() throws SQLException {
		this.con = ConexionDB.getConexion();
	}
	
	/**
	 * Metodo para crear una nueva categoria en la base de datos. 
	 * @param c Objeto Categoria para insertar.
	 * @return ID de la catedoria insertada.
	 * @throws SQLException Si hay un error de insercion en base de datos.
	 */
	public void crearCategoria(Categoria c) throws SQLException {
		String sql = "INSERT INTO categorias (nombre_Categoria, descripcion) VALUES (?, ?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, c.getNombreCategoria());
		ps.setString(2, c.getDescripcion());
		
		@SuppressWarnings("unused")
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
	/**
	 * Metodo para listar todos los productos de la base de datos.
	 * @return ArrayList de Producto con los datos de los productos.
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	public ArrayList<Categoria> listar() throws SQLException {
		
		String sql = "SELECT * FROM categorias";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Categoria> result = null;
		
		while (rs.next()) {
			if (result == null) {
				result = new ArrayList<Categoria>();
			}
			
			result.add(new Categoria(rs.getString(1), rs.getString(2)));
			
		}
		
		ps.close();
		
		return result;
	}
	
	/**
	 * Metodo para leer una categoria de la base de datos
	 * por su ID y modificarla posteriormente.
	 * @param idCategoria ID de la categoria a leer.
	 * @return Objeto Categoria con los datos de la categoria leidos.
	 * @throws SQLException Si hay un error de lectura
	 */
	public Categoria leerFormulario(int idCategoria) throws SQLException {
		
		String sql = "SELECT * FROM categorias WHERE id_Categoria = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idCategoria);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Categoria c = new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3));
		
		ps.close();
		
		return c;
	}
	
	public boolean actualizarCategoria(Categoria cate) {
		
		try {
			
		String sql = "UPDATE categorias SET nombre_Categoria = ?, descripcion = ?, id_Categoria = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, cate.getNombreCategoria());
		ps.setString(2, cate.getDescripcion());
		ps.setInt(3, cate.getIdCategoria());
		
		int rs = ps.executeUpdate();
		
		if (rs == 1) {
			return true;
		} else {
			return false;
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} // Cierre del catch
	}
	
	public static void borrarCategoria(Categoria cate) throws SQLException {
		
		String sql = "DELETE FROM categorias WHERE id_Categoria = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, cate.getIdCategoria());
		
		@SuppressWarnings("unused")
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
} // Class
