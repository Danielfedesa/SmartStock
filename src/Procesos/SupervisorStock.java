package Procesos;

import modelo.Producto;

public class SupervisorStock implements Runnable {

	 @Override
	    public void run() {

	        while (true) {
	            try {
	                Producto producto = new Producto();
	                String alerta = producto.verificarStockMinimo();

	                if (!alerta.equals("No hay productos con stock bajo.")) {
	                    System.out.println("¡Alerta de stock bajo!");
	                    System.out.println(alerta);
	                } else {
	                    System.out.println(alerta); // Imprime mensaje si no hay productos con stock bajo
	                }

	                Thread.sleep(60000); // Espera 1 minuto antes de la próxima verificación
	            } catch (Exception e) {
	                System.err.println("Error en la verificación del stock: " + e.getMessage());
	            }
	        }
	} // Fin proceso.

} // Class
