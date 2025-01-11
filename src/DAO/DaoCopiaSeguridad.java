package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controlador.ConexionDB;

/**
 * Clase para realizar operaciones relacionadas con
 * la tabla 'copiasseguridad' en la base de datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 01/2025
 */
public class DaoCopiaSeguridad {

	/**
	 * Atributo estatico que gestiona la conexion a la base de datos.
	 * en toda la aplicacion, permitiendo la reutilizacion de la misma conexion.
	 * Inicialmente la instancia esta establecida en null.
	 * @see java.sql.Connection
	 */
	// Genera un atributo de tipo Connection llamado "con" y la instancia en null:
	public static Connection con = null;
	
	/**
	 * Constructor de la clase DaoCopiaSeguridad para establecer conexion con la base de datos.
	 * Crea el objeto DaoCopiaSeguridad y el atributo "con"
	 * conecta a ConexionDB.getConexion() automaticamente.
	 * @throws SQLException Si hay un error de conexion.
	 */
	@SuppressWarnings("static-access")
	public DaoCopiaSeguridad() throws SQLException {
		this.con = ConexionDB.getConexion();
	}
	
	 /**
     * Metodo para registrar una copia de seguridad en la base de datos.
     * @param rutaBackup Ruta del archivo de la copia de seguridad.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
	public void registrarBackup(String rutaBackup) throws SQLException {
		
		String sql = "INSERT INTO copiasseguridad (ruta_Archivo) VALUES (?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, rutaBackup);
		
		@SuppressWarnings("unused")
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
} // Class
