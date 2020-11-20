package codes;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Organizador implements Runnable {
	
	private int MIN_DURACION = 1;
	private int MAX_DURACION = 2;
	private Bandeja bandeja_secos;
	private Bandeja bandeja_alacena;
	
	public Organizador(Bandeja bandeja_secos, Bandeja bandeja_alacena) {
		this.bandeja_secos = bandeja_secos;
		this.bandeja_alacena = bandeja_alacena;
	}

	@Override
	public void run() {
		//Metodo que contiene las instrucciones del hilo secundario a ejecutar
		Plato plato;
		while (!Thread.currentThread().isInterrupted()) {
			try {
				//El hilo coge un plato de la bandeja de platos secos
				plato = bandeja_secos.recogerPlato();
				System.out.printf("Hora %s: %s coge el plato %s...\n", LocalDateTime.now().format(Messages.formatoHora).toString(), Thread.currentThread().getName(), plato.getNumSerie());
			} catch(InterruptedException e) {
				System.out.printf("Hora %s: Se ha interrumpido al Organizador mientras cogia un plato......\n", LocalDateTime.now().format(Messages.formatoHora).toString());
				return;
			}

			try {
				//El hilo coloca el plato en la alacena de platos
				bandeja_alacena.colocarPlato(plato);
				System.out.printf("Hora %s: %s coloca el plato %s en la alacena...\n", LocalDateTime.now().format(Messages.formatoHora).toString(), Thread.currentThread().getName(), plato.getNumSerie());
				TimeUnit.SECONDS.sleep(new Random().nextInt(MAX_DURACION) + MIN_DURACION);;
			} catch(InterruptedException e) {
				System.out.printf("Hora %s: Se ha interrumpido al Organizador mientras colocaba un plato......\n", LocalDateTime.now().format(Messages.formatoHora).toString());
				return;
			}
		}
	}

}
