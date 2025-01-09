package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.ConexionDB;
import modelo.Usuario;

/**
 * Clase para realizar operaciones relacionadas con la tabla 'usuarios'
 * en la base de datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 01/2025
 */
public class DaoUsuario {

	/**
	 * Atributo estatico que gestiona la conexion a la base de datos
	 * en toda la aplicacion, permitiendo la reutilizacion de la misma conexion.
	 * Inicialmente la instancia esta establecida en null.
	 * @see java.sql.Connection
	 */
	// Genera un atributo de tipo Connection llamado "con" y la instancia en null:
	public static Connection con = null;
	
	/**
	 * Constructor de la clase DaoUsuario para establecer conexion con la base de datos.
	 * Crea el objeto DaoUsuario y el atributo "con"
	 * conecta a ConexionDB.getConexion() automaticamente.
	 * @throws SQLException Si hay un error de conexion.
	 */
	public DaoUsuario() throws SQLException {
		this.con = ConexionDB.getConexion();
	}
	
	/**
	 * Metodo para insertar un nuevo usuario en la base de datos. 
	 * @param u Objeto Usuario para insertar.
	 * @return ID del usuario insertado.
	 * @throws SQLException Si hay un error de insercion en base de datos.
	 */
	public int insertar(Usuario u) throws SQLException {
		// Sentencia SQL para insertar un nuevo registro en la tabla usuarios.
		String sql = "INSERT INTO usuarios (nombreUsuario, apellido1, apellido2, telefono, email, contrasena, rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		int id = 0;
		
		// Prepara la sentencia SQL y obtiene el ID generado con el GENERATED_KEYS
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		// Establece los parámetros en la sentencia SQL.
		ps.setString(1, u.getNombreUsuario());
		ps.setString(2, u.getApellido1());
		ps.setString(3, u.getApellido2());
		ps.setInt(4, u.getTelefono());
		ps.setString(5, u.getEmail());
		ps.setString(6, u.getContrasena());
		ps.setString(7, u.getRol());
		
		// Ejecuta la sentencia y actualiza el numero de filas afectadas.
		int filas = ps.executeUpdate();
		
		// Obtiene el ID generado.
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			id = rs.getInt(1);
		}
		
		ps.close();
		
		return id;
	}
	
} // Class
