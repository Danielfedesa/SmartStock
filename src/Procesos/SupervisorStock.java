package Procesos;

import modelo.Producto;

public class SupervisorStock implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true) {
			try {
				Producto prod = new Producto();
				String alerta = prod.verificarStockMinimo();
				if (alerta.isEmpty()) {
					System.out.println("Alerta de stock bajo");
					System.out.println(alerta);
				}
				Thread.sleep(60000); // Espera 1 minuto.
			} catch (Exception e) {
				System.out.println("Error en la verificación del stock " + e.getMessage());
			}
		}
	} // Fin proceso.

} // Class
