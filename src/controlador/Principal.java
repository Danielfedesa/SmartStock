package controlador;

import java.sql.SQLException;

import DAO.DaoUsuario;
import modelo.Usuario;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		/*
		 * 
		 
        Usuario u1 = new Usuario();
        u1.setNombreUsuario("Felipe");
        u1.setApellido1("García");
        u1.setApellido2("López");
        u1.setTelefono(222222222);
        u1.setEmail("adadad@adadadad.com");
        u1.setContrasena("12344");
        u1.setRol("empleado");
		
        
       try {
		u1.crearUsuario();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
*/
		
		// Pruebas para iniciar sesion.
		        try {
		            // Crear el DAO de Usuario
		            DaoUsuario usuarioDao = new DaoUsuario();

		            // Crear la instancia de Login
		            Login login = new Login(usuarioDao);

		            // Datos de prueba de inicio de sesión
		            String emailPrueba = "administrador@smartstock.com";
		            String contrasenaPrueba = "1234";

		            // Intentar iniciar sesión
		            Usuario usuario = login.iniciarSesion(emailPrueba, contrasenaPrueba);

		            if (usuario != null) {
		                System.out.println("Inicio de sesión exitoso.");
		                System.out.println("Bienvenido, " + usuario.getNombreUsuario() + " " + usuario.getApellido1());
		                System.out.println("Rol: " + usuario.getRol());
		            } else {
		                System.out.println("Inicio de sesión fallido. Verifica tus credenciales.");
		            }
		        } catch (Exception e) {
		            System.err.println("Error: " + e.getMessage());
		        }
		   
	
	
	} // Main
} // Class
