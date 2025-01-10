package controlador;

import DAO.DaoUsuario;
import modelo.Usuario;

/**
 * Clase para realizar operaciones de inicio de sesion 
 * con la tabla 'usuarios' en la base de datos.
 * Utiliza la calse DaoUsuario para validar los datos.
 * @author Daniel Fernandez Sanchez
 * @version 1.0 01/2025
 */
public class Login {

	/**
     * DAO de Usuario para interactuar con la base de datos.
     */
	private DaoUsuario usuarioDao;
	
	/**
     * Constructor de la clase Login.
     * Se requiere un DAO de Usuario para inicializar
     * la clase y gestionar la validación.
     * @param usuarioDao Objeto de tipo DaoUsuario
     * que permite interactuar con la tabla de usuarios.
     */
	public Login(DaoUsuario usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	 /**
     * Método para iniciar sesión en el sistema.
     * Valida las credenciales proporcionadas (correo electronico y contrasena)
     * y devuelve el objeto Usuario si son correctas.
     * En caso contrario, devuelve {@code null}.
     * 
     * @param email El correo electronico del usuario que intenta iniciar sesion.
     * @param contrasena La contrasena del usuario que intenta iniciar sesion.
     * @return Un objeto {@link Usuario} si las credenciales son validas, o {@code null} si no lo son.
     */
	public Usuario iniciarSesion(String email, String contrasena) {
		try {
			// Llama al metodo del DAO para obtener el usuario por su email.
			Usuario usu = usuarioDao.obtenerUsuXMail(email);
			
			// Comprueba si el usuario existe y si la contrasena coincide.
			if (usu != null && usu.getContrasena().equals(contrasena)) {
				return usu; // Retorna el objeto Usuario si las credenciales son validas.
			}
		} catch (Exception e) {
			System.err.println("Error de inicio de sesión: " + e.getMessage());
		}
		// Devuelve null si las credenciales no son validas o si ocurre un error.
				return null;
	}
	
} // Class
