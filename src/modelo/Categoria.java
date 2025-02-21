package modelo;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import DAO.DaoCategoria;

/**
 * Clase Categoria que representa la informacion,
 * constructores y metodos referentes a las
 * categorias del sistema.
 * @author Daniel Fernandez Sanchez.
 * @version 1.0 01/2025
 */
public class Categoria {
	
	private int idCategoria;
	private String nombreCategoria;
	private String descripcion;
	
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
	
	/**
     * Metodo para crear una nueva categoria en la base de datos.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
	public void crearCategoria() throws SQLException {
		DaoCategoria cat = new DaoCategoria();
		cat.crearCategoria(this);
	}
	
	/**
	 * Metodo para listar todas las categorias de la base de datos
	 * mediante un objeto del dao.
	 * @return Lista de objetos Categoria que representa todas las categorias
	 * almacenadas en la base de datos.
	 * @throws SQLException
	 */
	public List<Categoria> listarCategorias() throws SQLException {
		DaoCategoria daoCategoria = new DaoCategoria();
		
		return daoCategoria.listar();
	}
	
	/**
     * Metodo para recuperar una categoria por su ID y cargar sus datos.
     * @param idCategoria Identificador unico de la categoria a recuperar.
     * @return Objeto Categoria recuperado de la base de datos.
     * @throws SQLException Si ocurre un error en la base de datos
     */
	public Categoria recuperarCat(int idCategoria) throws SQLException {
		DaoCategoria dao = new DaoCategoria();
		Categoria c = dao.leerCategoria(idCategoria);
		
		return c;
	}
	
	/**
     * Metodo para insertar la actualizacion de los datos de una categoria en la base de datos.
     * @return boolean true si la actualizacion fue correcta, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
	public boolean actualizarCategoria() throws SQLException {
		DaoCategoria daoCat = new DaoCategoria();
		
		return daoCat.actualizarCategoria(this);
	}
	
	/**
     * Metodo para eliminar una categoria de la base de datos.
     * @throws SQLException Si hay un error al interactuar con la base de datos.
     */
	@SuppressWarnings("static-access")
	public void eliminarCategoria(int idCategoria) throws SQLException {
		DaoCategoria elim = new DaoCategoria();
		elim.borrarCategoria(idCategoria);
	}
	
} // Class
