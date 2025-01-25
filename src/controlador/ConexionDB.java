package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase con los atributos para la conexion con la base de datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 01/2025
 */
public class ConexionDB {

	 // Para realizar la conexión importamos previamente los recursos externos de la libreria MySQL

	/**
     * URL de conexion a la base de datos.
     */
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/smartstockdb";
	
	/**
     * Atributo estatico tipo Connection.
     * Tipo de conexion que proporciona el driver SQL.
     */
	public static Connection instance = null;
	
	 /**
     * Método publico estatico para obtener la conexion a la base de datos.
     * @return Connection objeto de conexion a la base de datos.
     * @throws SQLException si ocurre un error al establecer la conexion.
     */
	public static Connection getConexion() throws SQLException {
		// Debemos meterlo en un if por si llamamos a la clase por segunda vez y ya esta
		// conectada.
		if (instance == null) {

			instance = DriverManager.getConnection(JDBC_URL, "Daniel", "Daniel1234");
		}
		return instance;
	}

	/**
	 * Cierra la conexion a la base de datos si esta abierta.
	 * Este metodo asegura que la conexion establecida se cierre correctamente
	 * y se liberen los recursos asociados a ella.
	 * 
	 * Si la conexion ya esta cerrada o no existe, no realiza ninguna accion.
	 * @throws SQLException Si ocurre un error al intentar cerrar la conexión.
	 */
	public static void desconectar() throws SQLException {
		if (instance != null && instance.isClosed()) {
			instance.close();
			instance = null;// Limpiar la instancia para que la proxima vez se cree una nueva conexion.
		}
	}
	 
} // Class