package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase con los atributos para la conexion con la base de datos.
 */
public class ConexionDB {

	/*
	 * Para realizar la conexión importamos los recursos externos
	 * necesarias (servidor apache y librería SQL)
	 */

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
		// Debemos meterlo en un if por si llamamos a la clase por segunda vez y ya está
		// conectada.
		if (instance == null) {

			// Queremos que este método se conecte a
			// "jdbc:mysql://localhost:3360/alcaride_bd" y me lo devuelva generando una instancia.
			instance = DriverManager.getConnection(JDBC_URL, "Daniel", "1234");
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