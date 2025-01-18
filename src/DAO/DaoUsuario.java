package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	/**
	 * Método para consultar la base de datos y verificar si las credenciales proporcionadas 
	 * (email y contrasena) coinciden con los datos almacenados en la tabla `usuarios`.
	 * Si las credenciales son correctas, se devuelve un objeto `Usuario`
	 * con la informacion del usuario. Si las credenciales no son válidas, se retorna `null`.
	 * 
	 * @param email El correo electronico del usuario a validar.
	 * @param contrasena La contrasena del usuario a validar.
	 * @return Un objeto `Usuario` con el ID y el rol del usuario si las credenciales son correctas, 
	 *         o `null` si las credenciales no son validas.
	 * @throws Exception Si ocurre un error al ejecutar la consulta en la base de datos.
	 */
	public Usuario validarCredenciales(String email, String contrasena) throws Exception {
		
		String sql = "SELECT rol, id_Usuario FROM usuarios WHERE email = ? AND contrasena = ?";
		
        try (PreparedStatement ps = con.prepareStatement(sql)) {
        	
            ps.setString(1, email);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                	Usuario usuario = new Usuario();
                	usuario.setIdUsuario(rs.getInt("id_Usuario"));
                    usuario.setRol(rs.getString("rol"));
                    return usuario; // Devuelve el objeto Usuario si las credenciales son correctas.
                	
                }
            }
        }
        return null;
    }
	
	/**
	 * Metodo para insertar un nuevo usuario en la base de datos. 
	 * @param u Objeto Usuario para insertar.
	 * @throws SQLException Si hay un error de insercion en base de datos.
	 */
	public void insertar(Usuario u) throws SQLException {
		String sql = "INSERT INTO usuarios (nombre_Usuario, apellido1, apellido2, telefono, email, contrasena, rol)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, u.getNombreUsuario());
		ps.setString(2, u.getApellido1());
		ps.setString(3, u.getApellido2());
		ps.setInt(4, u.getTelefono());
		ps.setString(5, u.getEmail());
		ps.setString(6, u.getContrasena());
		ps.setString(7, u.getRol());
		
		@SuppressWarnings("unused")
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
	/**
	 * Metodo para listar todos los usuarios de la base de datos sin la contrasena. 
	 * @return ArrayList de Usuario con los datos de los usuarios.
	 * @throws SQLException Si hay un error de lectura en base de datos
	 */
	public ArrayList<Usuario> listar() throws SQLException {
		
		String sql = "SELECT id_Usuario, nombre_Usuario, apellido1, apellido2, telefono, email, rol FROM usuarios ";

		PreparedStatement ps = con.prepareStatement(sql);
		
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
		String sql = "SELECT id_Usuario, nombre_Usuario, apellido1, apellido2, telefono, email, rol FROM usuarios WHERE id_Usuario=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1,  idUsuario);
		
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
	    PreparedStatement ps = null;

	    try {
	        String sql = "UPDATE usuarios SET nombre_Usuario =?, apellido1 =?, apellido2 =?, "
	                + "telefono =?, email =?, rol =? WHERE id_Usuario =?";

	        ps = con.prepareStatement(sql);

	        ps.setString(1, user.getNombreUsuario());
	        ps.setString(2, user.getApellido1());
	        ps.setString(3, user.getApellido2());
	        ps.setInt(4, user.getTelefono());
	        ps.setString(5, user.getEmail());
	        ps.setString(6, user.getRol());
	        ps.setInt(7, user.getIdUsuario());

	        int rs = ps.executeUpdate();

	        // Retornar true si la actualizacion fue ok
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
	 * Metodo para eliminar un usuario de la base de datos.
	 * @param idUsuario Id del usuario que se desea eliminar.
	 * @throws SQLException Si hay un error de eliminacion en base de datos.
	 */
	public static void eliminarUsuario(int idUsuario) throws SQLException {
		String sql = "DELETE FROM usuarios WHERE id_Usuario = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idUsuario);
		
		@SuppressWarnings("unused")
		int filas = ps.executeUpdate();
		
		ps.close();
	}
	
} // Class
