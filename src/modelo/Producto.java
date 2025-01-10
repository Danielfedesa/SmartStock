package modelo;

import java.sql.SQLException;
import java.util.Objects;

import com.google.gson.Gson;

import DAO.DaoProducto;

public class Producto {

	private int idProducto;
	private String nombreProducto;
	private String descripcion;
	private double precio;
	private int stock;
	private int stockMinimo;
	private int idCategoria;
	
	public Producto() {
	}

	public Producto(int idProducto, String nombreProducto, String descripcion, double precio, int stock,
			int stockMinimo, int idCategoria) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.stockMinimo = stockMinimo;
		this.idCategoria = idCategoria;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, idCategoria, idProducto, nombreProducto, precio, stock, stockMinimo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(descripcion, other.descripcion) && idCategoria == other.idCategoria
				&& idProducto == other.idProducto && Objects.equals(nombreProducto, other.nombreProducto)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio) && stock == other.stock
				&& stockMinimo == other.stockMinimo;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", descripcion="
				+ descripcion + ", precio=" + precio + ", stock=" + stock + ", stockMinimo=" + stockMinimo
				+ ", idCategoria=" + idCategoria + "]";
	}
	
	/**
     * Metodo para insertar un producto en la base de datos.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
	public void crearProducto() throws SQLException {
		DaoProducto prod = new DaoProducto();
		prod.insertar(this);
	}
	
	/**
     * Metodo para listar todos los productos almacenados en la BD.
     * @return String JSON con la lista de productos.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
	public String listarProductos() throws SQLException {
		String json = "";
		Gson objetoGson = new Gson();
		DaoProducto resultado = new DaoProducto();
		
		json = objetoGson.toJson(resultado.listar());
		
		return json;
	}
	
	/**
     * Metodo para recuperar un producto por su ID y cargar sus datos.
     * @param idProducto Identificador unico del producto.
     * @throws SQLException Si ocurre un error en la base de datos
     */
	public void recuperarProd(int idProducto) throws SQLException {
		DaoProducto dao = new DaoProducto();
		
		Producto p = dao.leerFormulario(idProducto);
		
		this.setIdProducto(p.getIdProducto());
		this.setNombreProducto(p.getNombreProducto());
		this.setDescripcion(p.getDescripcion());
		this.setPrecio(p.getPrecio());
		this.setStock(p.getStock());
		this.setStockMinimo(p.getStockMinimo());
		this.setIdCategoria(p.getIdCategoria());
	}
	
	/**
     * Metodo para insertar la actualizacion de los datos de un producto en la base de datos.
     * @return boolean true si la actualizacion fue correcta, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
	public boolean actualizarProducto() throws SQLException {
		DaoProducto daoProd = new DaoProducto();
		
		return daoProd.actualizarProducto(this);
	}
	
	/**
     * Metodo para eliminar un producto de la base de datos.
     * @throws SQLException Si hay un error al interactuar con la base de datos.
     */
	@SuppressWarnings("static-access")
	public void eliminarProducto(int idProducto) throws SQLException {
		DaoProducto elim = new DaoProducto();
		elim.borrarProducto(this);
	}
	
} // Class
