package modelo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import DAO.DaoCopiaSeguridad;

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
	
	/**
	 * Metodo para realizar una copia de seguridad de la base de datos.
	 * @throws SQLException Si ocurre un error al interactuar con la base de datos.
	 */
	public void realizarBackup() throws SQLException {
		
		// Formatear la fecha y hora actual para incluirla en el nombre del archivo
	    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy_HH.mm");
	    String fechaHora = formatter.format(new Date());
		
		// Ruta y nombre
		String rutaBackup = "C:\\Users\\Daniel\\Desktop\\SmartStock\\SmartStock_Backups\\SmartStock_" + fechaHora + ".sql";
		String comando = "cmd /c \"C:\\xampp\\mysql\\bin\\mysqldump.exe -u Daniel -p1234 smartstockdb > " + rutaBackup + "\"";		
		
		try {
	        // Ejecuta el comando para realizar la copia
	        Process proceso = Runtime.getRuntime().exec(new String[]{"cmd", "/c", comando});

	        // Capturar errores del comando
	        InputStream errorStream = proceso.getErrorStream();
	        InputStreamReader isr = new InputStreamReader(errorStream);
	        BufferedReader br = new BufferedReader(isr);
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            System.err.println("Error mysqldump: " + linea);
	        }

	        int resultado = proceso.waitFor(); // Espera a que termine el proceso

	        if (resultado == 0) {
	            System.out.println("Copia de seguridad creada correctamente en la ruta: " + rutaBackup);

	            // Registra la copia de seguridad en la base de datos
	            DaoCopiaSeguridad daoCopia = new DaoCopiaSeguridad();
	            daoCopia.registrarBackup(rutaBackup);
	        } else {
	            System.err.println("Error al ejecutar el comando mysqldump. Código de salida: " + resultado);
	        }
	    } catch (Exception e) {
	        throw new SQLException("Error al realizar la copia de seguridad: " + e.getMessage());
	    }
	} // Cierre del metodo.
	
	public List<CopiaSeguridad> listarCopias() throws SQLException {
		DaoCopiaSeguridad daoCopia = new DaoCopiaSeguridad();
		return daoCopia.listar();
	}
	
} // Class
