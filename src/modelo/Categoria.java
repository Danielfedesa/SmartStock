package modelo;

import java.util.Objects;

/**
 * Clase Categoria que representa la informacion,
 * constructores y metodos referentes a las
 * categorias del sistema.
 * @author Daniel Fernandez Sanchez.
 * @version 1.0 01/2025
 */
public class Categoria {

	// Atributos:
	
	private int idCategoria;
	private String nombreCategoria;
	private String descripcion;
	
	// Constructores:
	
	/**
     * Constructor por defecto (vacio).
     */
	public Categoria() {
	}

	/**
	 * Constructor completo Categoria.
	 * @param idCategoria Identificador unico de la categoria.
	 * @param nombreCategoria Nombre de la categoria.
	 * @param descripcion Descripcion de la categoria.
	 */
	public Categoria(int idCategoria, String nombreCategoria, String descripcion) {
		super();
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.descripcion = descripcion;
	}
	
	/**
	 * Constructor para crear una Categoria sin id.
	 * @param nombreCategoria Nombre de la categoria.
	 * @param descripcion Descripcion de la categoria.
	 */
	public Categoria(String nombreCategoria, String descripcion) {
		super();
		this.nombreCategoria = nombreCategoria;
		this.descripcion = descripcion;
	}

	// Getters y Setters:
	
	/**
	 * Obtiene el identificador de la categoria.
	 * @return idCategoria Identificador de la categoria.
	 */
	public int getIdCategoria() {
		return idCategoria;
	}

	/**
	 * Establece el identificador de la categoria.
	 * @param idCategoria Identificador de la categoria.
	 */
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * Obtiene el nombre de la categoria.
	 * @return nombreCategoria Nombre de la categoria.
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}

	/**
	 * Establece el nombre de la categoria.
	 * @param nombreCategoria Nombre de la categoria.
	 */
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	/**
	 * Obtiene la descripcion de la categoria.
	 * @return descripcion Descripcion de la categoria.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripcion de la categoria.
	 * @param descripcion Descripcion de la categoria.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Calcula el codigo hash del objeto Categoria.
	 * @return int Codigo hash del objeto Categoria.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, idCategoria, nombreCategoria);
	}

	/**
	 * Compara dos objetos Categoria para verificar si son iguales.
	 * @param obj Objeto con el cual se va a comparar la categoria.
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
		Categoria other = (Categoria) obj;
		return Objects.equals(descripcion, other.descripcion) && idCategoria == other.idCategoria
				&& Objects.equals(nombreCategoria, other.nombreCategoria);
	}

	/**
	 * Devuelve una representacion en forma de cadena del objeto Categoria.
	 * @return String Representacion en cadena del objeto Categoria.
	 */
	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nombreCategoria=" + nombreCategoria + ", descripcion="
				+ descripcion + "]";
	}
	
} // Class
