package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.ConexionDB;
import modelo.Usuario;

/**
 * Clase para realizar operaciones relacionadas con
 * la tabla 'usuarios' en la base de datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 01/2025
 */
public class DaoUsuario {

	/**
	 * Atributo estatico que gestiona la conexion a la base de datos.
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
	@SuppressWarnings("static-access")
	public DaoUsuario() throws SQLException {
		this.con = ConexionDB.getConexion();
	}
	
	// MIRAR ESTE MÉTODO Y VER SI EL ANTERIOR ME HACE FALTA PARA INICIAR SESION
	public String validarCredenciales(String email, String contrasena) throws Exception {
		
		String sql = "SELECT rol FROM usuarios WHERE email = ? AND contrasena = ?";
		
        try (PreparedStatement ps = con.prepareStatement(sql)) {
        	
            ps.setString(1, email);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                	return rs.getString("rol"); // Devuelve el rol si las credenciales son correctas.
                }
            }
        }
        return null;
    }
	
	/**
	 * Metodo para insertar un nuevo usuario en la base de datos. 
	 * @param u Objeto Usuario para insertar.
	 * @return ID del usuario insertado.
	 * @throws SQLException Si hay un error de insercion en base de datos.
	 */
	public int insertar(Usuario u) throws SQLException {
		// Sentencia SQL para insertar un nuevo registro en la tabla usuarios.
		String sql = "INSERT INTO usuarios (nombre_Usuario, apellido1, apellido2, telefono, email, contrasena, rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
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
		@SuppressWarnings("unused")
		int filas = ps.executeUpdate();
		
		// Obtiene el ID generado.
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			id = rs.getInt(1);
		}
		
		ps.close();
		
		return id;
	}
	
	/**
	 * Metodo para listar todos los usuarios de la base de datos. 
	 * @return ArrayList de Usuario con los datos de los usuarios.
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	public ArrayList<Usuario> listar() throws SQLException {
		
		// Sentencia para seleccionar todos los registros.
		String sql = "SELECT * FROM usuarios ";
		
		// Prepara la sentencia SQL.
		PreparedStatement ps = con.prepareStatement(sql);
		
		// Almacena los datos en un objeto ResultSet.
		ResultSet rs = ps.executeQuery();
		
		// Inicializa el ArrayList para almacenar los objetos de Usuario.
		ArrayList<Usuario> result = null;
		
		// Lee el resultado del ResultSet y crea objetos Usuario.
		while (rs.next()) {
			// Comprueba si el array sigue null, en caso contrario lo inicializa.
			if (result == null) {
				result = new ArrayList<Usuario>();
			}
			// Añade al rs los elementos que obtengo de la BD.
			result.add(new Usuario(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5),
					rs.getString(6),
					rs.getString(7)));
		}
		
		ps.close();
		
		// Devuelve el ArrayList de Usuario.
		return result;
	}
	
	/**
	 * Metodo para leer un usuario de la base de datos por su ID y modificarlo posteriormente.
	 * @param id_Usuario ID del usuario para leer.
	 * @return Objeto Usuario con los datos del usuario leido.
	 * @throws SQLException Si hay un error de lectura en base de datos.
	 */
	public Usuario leerUsuario(int idUsuario) throws SQLException {
		// Sentencia para seleccionar un registro en la bd.
		String sql = "SELECT * FROM usuarios WHERE id_Usuario=?";
		
		// Prepara la sentencia SQL.
		PreparedStatement ps = con.prepareStatement(sql);
		
		// Manda el preparedStatement y el idUsuario.
		ps.setInt(1,  idUsuario);
		
		// Ejecuta la consulta y obtiene el resultado.
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		// Crea un objeto usuario y establece los datos obtenidos.
		Usuario u = new Usuario(
				rs.getInt(1),
				rs.getString(2),
				rs.getString(3),
				rs.getString(4),
				rs.getInt(5),
				rs.getString(6),
				rs.getString(7));
		
		ps.close();
		
		return u;
	}
	
	/**
	 * Metodo para insertar la modificacion de un usuario en la base de datos. 
	 * @param user Objeto Usuario con los datos actualizados.
	 * @return true si la actualizacion fue correcta, false si esta falla.
	 */
	public boolean actualizarUsuario(Usuario user) {
		
		// Sentencia para actualizar un registro de la tabla usuarios por su ID.
		try {
			String sql = "UPDATE usuarios SET nombre_Usuario =?, apellido1 =?, apellido2 =?,"
					+ "telefono =?, email =?, rol =? WHERE id_Usuario =?";
			
			// Prepara la sentencia SQL.
			PreparedStatement ps = con.prepareStatement(sql);
			
			// Establece los parametros en la sentencia SQL.
			ps.setString(1, user.getNombreUsuario());
			ps.setString(2, user.getApellido1());
			ps.setString(3, user.getApellido2());
			ps.setInt(4, user.getTelefono());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getRol());
			ps.setInt(7, user.getIdUsuario());
			
			// Ejecuta la sentencia y obtiene el resultado.
			int rs = ps.executeUpdate();
			
			// Comprueba si se ha insertado correctamente y devuelve true o false.
			if (rs == 1) {
				return true;
				} else {
					return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo para eliminar un usuario de la base de datos.
	 * @param e Objeto Usuario para eliminar.
	 * @throws SQLException Si hay un error de eliminacion en base de datos.
	 */
	public static void eliminarUsuario(Usuario user) throws SQLException {
		// Sentencia para eliminar un registro de la tabla usuarios por su ID.
		String sql = "DELETE FROM usuarios WHERE id_Usuario = ?";
		
		// Prepara la sentencia SQL.
		PreparedStatement ps = con.prepareStatement(sql);
		
		// Manda el preparedStatement y el id.
		ps.setInt(1, user.getIdUsuario());
		
		// Ejecuta la sentencia.
		@SuppressWarnings("unused")
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
} // Class
