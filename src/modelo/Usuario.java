package modelo;

import java.util.Objects;

/**
 * Clase Usuario que representa la informacion,
 * constructores y metodos referentes a los
 * usuarios del sistema.
 * @author Daniel Fernandez Sanchez.
 * @version 1.0 01/2025
 */
public class Usuario {
	
	// Atributos:
	
	private int idUsuario;
	private String nombreUsuario;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private String email;
	private String contrasena;
	private String Rol;
	
	// Constructores:
	
	/**
     * Constructor por defecto (vacio).
     */
	public Usuario() {
	}

	/**
	 * Constructor completo Usuario.
	 * @param idUsuario Identificador unico del usuario.
	 * @param nombreUsuario Nombre del usuario.
	 * @param apellido1 Primer apellido del usuario.
	 * @param apellido2 Segundo apellido del usuario.
	 * @param telefono Telefono del usuario.
	 * @param email Correo electronico unico del usuario.
	 * @param contrasena Password del usuario.
	 * @param rol Rol del usuario (admin o empleado).
	 */
	public Usuario(int idUsuario, String nombreUsuario, String apellido1, String apellido2, int telefono, String email,
			String contrasena, String rol) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.telefono = telefono;
		this.email = email;
		this.contrasena = contrasena;
		Rol = rol;
	}
	
	/**
	 * Constructor para crear un Usuario sin el id.
	 * @param nombreUsuario Nombre del usuario.
	 * @param apellido1 Primer apellido del usuario.
	 * @param apellido2 Segundo apellido del usuario.
	 * @param telefono Telefono del usuario.
	 * @param email Correo electronico unico del usuario.
	 * @param contrasena Password del usuario.
	 * @param rol Rol del usuario (admin o empleado).
	 */
	public Usuario(String nombreUsuario, String apellido1, String apellido2, int telefono, String email,
			String contrasena, String rol) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.telefono = telefono;
		this.email = email;
		this.contrasena = contrasena;
		Rol = rol;
	}

	// Getters y Setters:
	
	/**
	 * Obtiene el identificador del usuario.
	 * @return idUsuario Identificador del usuario.
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Establece el identificador del usuario.
	 * @param idUsuario Identificador del usuario.
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Obtiene el nombre del usuario.
	 * @return nombreUsuario Nombre del usuario.
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Establece el nombre del usuario.
	 * @param nombreUsuario Nombre del usuario.
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * Obtiene el primer apellido del usuario.
	 * @return apellido1 Primer apellido del usuario.
	 */
	public String getApellido1() {
	    return apellido1;
	}

	/**
	 * Establece el primer apellido del usuario.
	 * @param apellido1 Primer apellido del usuario.
	 */
	public void setApellido1(String apellido1) {
	    this.apellido1 = apellido1;
	}

	/**
	 * Obtiene el segundo apellido del usuario.
	 * @return apellido2 Segundo apellido del usuario.
	 */
	public String getApellido2() {
	    return apellido2;
	}

	/**
	 * Establece el segundo apellido del usuario.
	 * @param apellido2 Segundo apellido del usuario.
	 */
	public void setApellido2(String apellido2) {
	    this.apellido2 = apellido2;
	}

	/**
	 * Obtiene el numero de telefono del usuario.
	 * @return telefono Numero de telefono del usuario.
	 */
	public int getTelefono() {
	    return telefono;
	}

	/**
	 * Establece el numero de telefono del usuario.
	 * @param telefono Numero de telefono del usuario.
	 */
	public void setTelefono(int telefono) {
	    this.telefono = telefono;
	}

	/**
	 * Obtiene el correo electronico del usuario.
	 * @return email Correo electronico del usuario.
	 */
	public String getEmail() {
	    return email;
	}

	/**
	 * Establece el correo electronico del usuario.
	 * @param email Correo electronico del usuario.
	 */
	public void setEmail(String email) {
	    this.email = email;
	}

	/**
	 * Obtiene la contrasena del usuario.
	 * @return contrasena Contrasena del usuario.
	 */
	public String getContrasena() {
	    return contrasena;
	}

	/**
	 * Establece la contrasena del usuario.
	 * @param contrasena Contrasena del usuario.
	 */
	public void setContrasena(String contrasena) {
	    this.contrasena = contrasena;
	}

	/**
	 * Obtiene el rol del usuario.
	 * @return Rol del usuario.
	 */
	public String getRol() {
	    return Rol;
	}

	/**
	 * Establece el rol del usuario.
	 * @param rol Rol del usuario.
	 */
	public void setRol(String rol) {
	    Rol = rol;
	}

	/**
	 * Calcula el codigo hash del objeto Usuario.
	 * @return int Codigo hash del objeto Usuario.
	 */
	@Override
	public int hashCode() {
	    return Objects.hash(Rol, apellido1, apellido2, contrasena, email, idUsuario, nombreUsuario, telefono);
	}

	/**
	 * Compara dos objetos Usuario para verificar si son iguales.
	 * @param obj Objeto con el cual se va a comparar el usuario.
	 * @return true si los objetos son iguales, false de lo contrario.
	 */
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Usuario other = (Usuario) obj;
	    return Objects.equals(Rol, other.Rol) && Objects.equals(apellido1, other.apellido1)
	            && Objects.equals(apellido2, other.apellido2) && Objects.equals(contrasena, other.contrasena)
	            && Objects.equals(email, other.email) && idUsuario == other.idUsuario
	            && Objects.equals(nombreUsuario, other.nombreUsuario) && telefono == other.telefono;
	}

	/**
	 * Devuelve una representacion en forma de cadena del objeto Usuario.
	 * @return String Representacion en cadena del objeto Usuario.
	 */
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", telefono=" + telefono + ", email=" + email + ", contrasena="
				+ contrasena + ", Rol=" + Rol + "]";
	}

} // Class
