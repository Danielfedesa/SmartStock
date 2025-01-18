package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ConexionDB;
import modelo.HistorialInventario;

/**
 * Clase para realizar operaciones relacionadas con
 * la tabla 'historialinventario' en la base de datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 01/2025
 */
public class DaoHistorialInventario {

	/**
	 * Atributo estatico que gestiona la conexion a la base de datos.
	 * en toda la aplicacion, permitiendo la reutilizacion de la misma conexion.
	 * Inicialmente la instancia esta establecida en null.
	 * @see java.sql.Connection
	 */
	// Genera un atributo de tipo Connection llamado "con" y la instancia en null:
	public static Connection con = null;
	
	/**
	 * Constructor de la clase DaoHistorialInventario para establecer conexion con la base de datos.
	 * Crea el objeto DaoHistorialInventario y el atributo "con"
	 * conecta a ConexionDB.getConexion() automaticamente.
	 * @throws SQLException Si hay un error de conexion.
	 */
	@SuppressWarnings("static-access")
	public DaoHistorialInventario() throws SQLException {
		this.con = ConexionDB.getConexion();
	}
	
	/**
	 * Metodo para insertar un nuevo registro en la tabla `historialinventario`
	 * con la informacion del movimiento de un producto.
	 * 
	 * @param historial El objeto `HistorialInventario` que contiene los detalles del movimiento a registrar.
	 * @throws Exception Si ocurre un error durante la ejecución de la sentencia SQL.
	 */
	public void registrarMovimiento(HistorialInventario historial) throws Exception {
	    String sql = "INSERT INTO historialinventario (id_Producto, id_Usuario, cantidad, tipo_Movimiento) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, historial.getIdProducto());
	        ps.setInt(2, historial.getIdUsuario());
	        ps.setInt(3, historial.getCantidad());
	        ps.setString(4, historial.getTipoMovimiento());

	        ps.executeUpdate();
	        
	        ps.close();
	    }
	}
	
	/**
	 * Metodo para listar todos los movimientos del inventario de la base de datos.
	 * @return ArrayList de HistorialInventario con los datos de los movimientos.
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	public ArrayList<HistorialInventario> listarMov() throws SQLException {
		
		String sql = "SELECT * FROM historialinventario";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<HistorialInventario> result = null;
		
		while (rs.next()) {
			if (result == null) {
				result = new ArrayList<HistorialInventario>();
			}
			
			result.add(new HistorialInventario(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), 
					rs.getString(5), rs.getTimestamp(6)));
		}
		
		ps.close();
		
		return result;
	}
	
} // Class
