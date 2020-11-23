package codes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Main {
	
	private static final int LIMITE_BANDEJA = 20;
	private static final int MAX_HILOS = 3;
	private static final int MAX_SEGUNDOS = 60;
	private static DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss"); //Static?
	

	public static void main(String[] args) {
		//Objeto cerrojo comun a los hilos
		Bandeja bandeja_sucios = new Bandeja();
		Bandeja bandeja_fregados = new Bandeja();
		Bandeja bandeja_secos = new Bandeja();
		Bandeja bandeja_alacena = new Bandeja();

		//Hilos secundarios declarados con nombre
		Thread fregador = new Thread(new Fregador(bandeja_sucios, bandeja_fregados), "Fregador");
		Thread secador = new Thread(new Secador(bandeja_fregados, bandeja_secos), "Secador");
		Thread organizador = new Thread(new Organizador(bandeja_secos, bandeja_alacena), "Organizador");
		//Array de hilos secundarios
		Thread[] trabajadores = {fregador, secador, organizador};
		
		try {
			for (int i = 0; i < LIMITE_BANDEJA; i++) {
				bandeja_sucios.colocarPlato(new Plato(i + 1));
			}
			
			//Se inician los hilos
			for (int i = 0; i < MAX_HILOS; i++) {
				trabajadores[i].start();
			}
			
			//Hilo principal espera 60 segundos antes de interrumpir los hilos secundarios
			TimeUnit.SECONDS.sleep(MAX_SEGUNDOS);
			
			for (int i = 0; i < MAX_HILOS; i++) {
				trabajadores[i].interrupt();
			}
			//Se muestra por pantalla el mensaje CUMPLEAÑOS FELIZ
			System.out.printf("Hora %s: --Todos: CUMPLEAÑOS FELIZ!!\n", LocalDateTime.now().format(formatoHora).toString());

		
		} catch (InterruptedException e) {
			//Si por si algo interrumpe el hilo principal, se muestra un mensaje por pantalla
			System.out.println("ERROR -- CUMPLEAÑERO INTERRUMPIDO");
			e.printStackTrace();
		}
	}

}
