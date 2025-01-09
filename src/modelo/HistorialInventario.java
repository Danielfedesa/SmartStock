package modelo;

import java.sql.Timestamp;
import java.util.Objects;

public class HistorialInventario {
	
	private int idHistorial;
	private int idProducto;
	private int cantidad;
	private String tipoMovimiento;
	private Timestamp fecha;
	
	public HistorialInventario() {
	}

	public HistorialInventario(int idHistorial, int idProducto, int cantidad, String tipoMovimiento, Timestamp fecha) {
		super();
		this.idHistorial = idHistorial;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.tipoMovimiento = tipoMovimiento;
		this.fecha = fecha;
	}

	public int getIdHistorial() {
		return idHistorial;
	}

	public void setIdHistorial(int idHistorial) {
		this.idHistorial = idHistorial;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, fecha, idHistorial, idProducto, tipoMovimiento);
	}

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

	@Override
	public String toString() {
		return "HistorialInventario [idHistorial=" + idHistorial + ", idProducto=" + idProducto + ", cantidad="
				+ cantidad + ", tipoMovimiento=" + tipoMovimiento + ", fecha=" + fecha + "]";
	}

} // Class
