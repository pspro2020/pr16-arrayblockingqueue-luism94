package codes;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Organizer implements Runnable {
	
	private int MIN_DURACION = 1;
	private int MAX_DURACION = 2;
	private Tray tray_of_driedDishes;
	private Tray tray_of_finisedDishes;
	
	public Organizer(Tray tray_of_driedDishes, Tray tray_of_finisedDishes) {
		this.tray_of_driedDishes = tray_of_driedDishes;
		this.tray_of_finisedDishes = tray_of_finisedDishes;
	}

	@Override
	public void run() {
		//Metodo que contiene las instrucciones del hilo secundario a ejecutar
		Dish dish = null;
		while (!Thread.currentThread().isInterrupted()) {
			try {
				//El hilo coge un plato de la bandeja de platos secos
				dish = tray_of_driedDishes.pickUpDish();
				System.out.printf("Hora %s: %s coge el plato %s...\n", LocalDateTime.now().format(Constants.TIME_FORMATTER).toString(), Thread.currentThread().getName(), dish.getNumSerie());
			} catch(InterruptedException e) {
				System.out.println(String.format(Constants.INTERRUPTED_THREAD_PICKING_DISH, LocalDateTime.now().format(Constants.TIME_FORMATTER).toString(), dish.getNumSerie()));
				return;
			}

			try {
				//El hilo coloca el plato en la alacena de platos
				tray_of_finisedDishes.placeDish(dish);
				System.out.printf(String.format("Hora %s: %s coloca el plato %s en la alacena...\n", LocalDateTime.now().format(Constants.TIME_FORMATTER).toString(), Thread.currentThread().getName(), dish.getNumSerie()));
				TimeUnit.SECONDS.sleep(new Random().nextInt(MAX_DURACION) + MIN_DURACION);;
			} catch(InterruptedException e) {
				System.out.println(String.format(Constants.INTERRUPTED_THREAD_PLACING_DISH, LocalDateTime.now().format(Constants.TIME_FORMATTER).toString(), dish.getNumSerie()));
				return;
			}
		}
	}

}
