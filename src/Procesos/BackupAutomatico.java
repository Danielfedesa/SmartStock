package Procesos;

import modelo.CopiaSeguridad;

public class BackupAutomatico implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true) {
			try {
				CopiaSeguridad copia = new CopiaSeguridad();
				copia.realizarBackup();
				Thread.sleep(60000); // 86400000 Espera 24 horas.
			} catch (Exception e) {
				System.out.println("Error al crear la copia de seguridad");
			}
		}
		
	} // Fin proceso

} // Class
