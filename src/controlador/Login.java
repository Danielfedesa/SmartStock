package controlador;

import DAO.DaoUsuario;
import modelo.Usuario;

public class Login {

	private DaoUsuario usuarioDao;
	
	public Login(DaoUsuario usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	// Metodo para iniciar sesion
	public Usuario iniciarSesion(String email, String contrasena) {
		try {
			Usuario usu = usuarioDao.obtenerUsuXMail(email);
			if (usu != null && usu.getContrasena().equals(contrasena)) {
				return usu;
			}
		} catch (Exception e) {
			System.err.println("Error de inicio de sesión: " + e.getMessage());
		}
				return null;
	}
	
} // Class
