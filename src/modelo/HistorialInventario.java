package modelo;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import com.google.gson.Gson;

import DAO.DaoHistorialInventario;

/**
 * Clase HistorialInventario que representa la informacion,
 * constructores y metodos referentes a los
 * movimientos del inventario del sistema.
 * @author Daniel Fernandez Sanchez.
 * @version 1.0 01/2025
 */
public class HistorialInventario {
	
	// Atributos:
	
	private int idHistorial;
	private int idProducto;
	private int cantidad;
	private String tipoMovimiento;
	private Timestamp fecha;
	
	// Constructores:
	
	/**
     * Constructor por defecto (vacio).
     */
	public HistorialInventario() {
	}
	
	/**
	 * Constructor completo HistorialInventario.
	 * @param idHistorial Identificador unico de los movimientos.
	 * @param idProducto Identificador unico de los productos.
	 * @param cantidad Cantidad de unidades del producto.
	 * @param tipoMovimiento Tipo de movimiento de stock: Entrada o Salida.
	 * @param fecha Fecha del movimiento.
	 */
	public HistorialInventario(int idHistorial, int idProducto, int cantidad, String tipoMovimiento, Timestamp fecha) {
		super();
		this.idHistorial = idHistorial;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.tipoMovimiento = tipoMovimiento;
		this.fecha = fecha;
	}
	
	/**
	 * Constructor HistorialInventario sin idHistorial.
	 * @param idProducto Identificador unico de los productos.
	 * @param cantidad Cantidad de unidades del producto.
	 * @param tipoMovimiento Tipo de movimiento de stock: Entrada o Salida.
	 * @param fecha Fecha del movimiento.
	 */
	public HistorialInventario(int idProducto, int cantidad, String tipoMovimiento) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.tipoMovimiento = tipoMovimiento;
        this.fecha = new Timestamp(System.currentTimeMillis());
    }

	// Getters y Setters:
	
	
	/**
	 * Obtiene el identificador del historial.
	 * @return idHistorial Identificador del historial.
	 */
	public int getIdHistorial() {
		return idHistorial;
	}

	/**
	 * Establece el identificador del historial.
	 * @param idHistorial Identificador del historial.
	 */
	public void setIdHistorial(int idHistorial) {
		this.idHistorial = idHistorial;
	}

	/**
	 * Obtiene el identificador del producto.
	 * @return idProducto Identificador del producto.
	 */
	public int getIdProducto() {
		return idProducto;
	}

	/**
	 * Establece el identificador del producto.
	 * @param idProducto Identificador del producto.
	 */
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * Obtiene la cantidad de productos en el movimiento.
	 * @return cantidad Cantidad de productos en el movimiento.
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Establece la cantidad de productos en el movimiento.
	 * @param cantidad Cantidad de productos en el movimiento.
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Obtiene el tipo de movimiento (Entrada o Salida).
	 * @return tipoMovimiento tipo de movimiento (Entrada o Salida).
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * Establece el tipo de movimiento (Entrada o Salida)
	 * @param tipoMovimiento tipo de movimiento (Entrada o Salida).
	 */
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	/**
	 * Obtiene la fecha del movimiento.
	 * @return fecha Fecha del movimiento.
	 */
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha del movimiento.
	 * @param fecha Fecha del movimiento.
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/**
	 * Calcula el codigo hash del objeto HistorialInventario.
	 * @return int Codigo hash del objeto HistorialInventario.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cantidad, fecha, idHistorial, idProducto, tipoMovimiento);
	}

	/**
	 * Compara dos objetos HistorialInventario para verificar si son iguales.
	 * @param obj Objeto con el cual se va a comparar.
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
		HistorialInventario other = (HistorialInventario) obj;
		return cantidad == other.cantidad && Objects.equals(fecha, other.fecha) && idHistorial == other.idHistorial
				&& idProducto == other.idProducto && Objects.equals(tipoMovimiento, other.tipoMovimiento);
	}

	/**
	 * Devuelve una representacion en forma de cadena del objeto HistorialInventario.
	 * @return String Representacion en cadena del objeto HistorialInventario.
	 */
	@Override
	public String toString() {
		return "HistorialInventario [idHistorial=" + idHistorial + ", idProducto=" + idProducto + ", cantidad="
				+ cantidad + ", tipoMovimiento=" + tipoMovimiento + ", fecha=" + fecha + "]";
	}
	
	public void insertarMovimiento() throws SQLException {
		DaoHistorialInventario dao = new DaoHistorialInventario();
		dao.insertarMov(this);
	}
	
	public String listarMovimientos() throws SQLException {
		DaoHistorialInventario dao = new DaoHistorialInventario();
		return new Gson().toJson(dao.listarMov());
	}

} // Class
