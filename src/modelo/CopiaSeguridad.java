package modelo;

import java.sql.Timestamp;
import java.util.Objects;

public class CopiaSeguridad {

	private int idBackup;
	private Timestamp fechaBackup;
	private String rutaArchivo;
	
	public CopiaSeguridad() {
	}

	public CopiaSeguridad(int idBackup, Timestamp fechaBackup, String rutaArchivo) {
		super();
		this.idBackup = idBackup;
		this.fechaBackup = fechaBackup;
		this.rutaArchivo = rutaArchivo;
	}

	public int getIdBackup() {
		return idBackup;
	}

	public void setIdBackup(int idBackup) {
		this.idBackup = idBackup;
	}

	public Timestamp getFechaBackup() {
		return fechaBackup;
	}

	public void setFechaBackup(Timestamp fechaBackup) {
		this.fechaBackup = fechaBackup;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaBackup, idBackup, rutaArchivo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CopiaSeguridad other = (CopiaSeguridad) obj;
		return Objects.equals(fechaBackup, other.fechaBackup) && idBackup == other.idBackup
				&& Objects.equals(rutaArchivo, other.rutaArchivo);
	}

	@Override
	public String toString() {
		return "CopiaSeguridad [idBackup=" + idBackup + ", fechaBackup=" + fechaBackup + ", rutaArchivo=" + rutaArchivo
				+ "]";
	}
	
} // Class
