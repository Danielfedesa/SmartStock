package modelo;

import java.util.Objects;

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
	
} // Class
