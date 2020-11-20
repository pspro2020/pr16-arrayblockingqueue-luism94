package codes;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Fregador implements Runnable {

	private final int MIN_DURACION = 4;
	//Objeto recibido por parametro del constructor
	private Bandeja bandeja_sucios;
	private Bandeja bandeja_limpios;
	
	public Fregador(Bandeja bandeja_1, Bandeja bandeja_2) {
		this.bandeja_sucios = bandeja_1;
		this.bandeja_limpios = bandeja_2;
	}
	
	@Override
	public void run() {
		Plato plato = null;
		
		while (!Thread.currentThread().isInterrupted()) {
			try {
				plato = bandeja_sucios.recogerPlato();
				limpiarPlato(plato);
			} catch (InterruptedException e) {
				System.out.printf("Hora %s: Se ha interrumpido a %s mientras cogia un plato......\n", LocalDateTime.now().format(Messages.formatoHora).toString(), Thread.currentThread().getName());
				return;
			}
			
			try {
				bandeja_limpios.colocarPlato(plato);
			} catch (InterruptedException e) {
				System.out.printf("Hora %s: Se ha interrumpido a %s mientras colocaba un plato......\n", LocalDateTime.now().format(Messages.formatoHora).toString(), Thread.currentThread().getName());
				return;
			}
			
		}
	}

	private void limpiarPlato(Plato plato) throws InterruptedException {
		//Se meustra un mensaje por pantalla y el hilo se suspende durante unos segundos
		System.out.printf("Hora %s: %s coge un plato y se pone a fregar el plato %s...\n", LocalDateTime.now().format(Messages.formatoHora).toString(), Thread.currentThread().getName(), plato.getNumSerie());
		TimeUnit.SECONDS.sleep(new Random().nextInt(MIN_DURACION + 1) + 4);
	}

}
