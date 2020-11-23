package codes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		//Objetos bandeja platos para los hilos
		Tray dirtyDishesTray = new Tray();
		Tray washedDishesTray = new Tray();
		Tray driedDishesTray = new Tray();
		Tray finishedDishesTray = new Tray();

		//Hilos secundarios declarados con nombre
		Thread washer = new Thread(new Washer(dirtyDishesTray, washedDishesTray), "Cleaner");
		Thread dryer = new Thread(new Dryer(washedDishesTray, driedDishesTray), "Dryer");
		Thread organizer = new Thread(new Organizer(driedDishesTray, finishedDishesTray), "Organizer");
		//Array de hilos secundarios
		Thread[] workers = {washer, dryer, organizer};
		
		try {
			for (int i = 0; i < Constants.DISH_LIMIT; i++) {
				dirtyDishesTray.placeDish(new Dish(i + 1));
			}
			
			//Se inician los hilos
			for (int i = 0; i < Constants.THREAD_LIMIT; i++) {
				workers[i].start();
			}
			
			//Hilo principal espera 60 segundos antes de interrumpir los hilos secundarios
			TimeUnit.SECONDS.sleep(Constants.MAX_TIME_SECONDS);
			
			for (int i = 0; i < Constants.THREAD_LIMIT; i++) {
				workers[i].interrupt();
			}
			//Se muestra por pantalla el mensaje CUMPLEAÑOS FELIZ
			System.out.println(String.format(Constants.MAIN_PROGRAM_END, LocalDateTime.now().format(Constants.TIME_FORMATTER).toString()));

		
		} catch (InterruptedException e) {
			//Si por si algo interrumpe el hilo principal, se muestra un mensaje por pantalla
			System.out.println(String.format(Constants.MAIN_INTERRUPTED, LocalDateTime.now().format(Constants.TIME_FORMATTER)));
			e.printStackTrace();
		}
	}

}
