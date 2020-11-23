package codes;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Washer implements Runnable {
	//Objeto recibido por parametro del constructor
	private Tray tray_of_dirtyDishes;
	private Tray tray_of_cleanDishes;
	
	public Washer(Tray tray_of_dirtyDishes, Tray tray_of_cleanDishes) {
		this.tray_of_dirtyDishes = tray_of_dirtyDishes;
		this.tray_of_cleanDishes = tray_of_cleanDishes;
	}
	
	@Override
	public void run() {
		Dish dish = null;
		
		while (!Thread.currentThread().isInterrupted()) {
			try {
				dish = tray_of_dirtyDishes.pickUpDish();
				limpiarPlato(dish);
			} catch (InterruptedException e) {
				System.out.println(String.format(Constants.INTERRUPTED_THREAD_PICKING_DISH, LocalDateTime.now().format(Constants.TIME_FORMATTER).toString(), Thread.currentThread().getName(), dish.getNumSerie()));
				return;
			}
			
			try {
				tray_of_cleanDishes.placeDish(dish);
			} catch (InterruptedException e) {
				System.out.println(String.format(Constants.INTERRUPTED_THREAD_PLACING_DISH, LocalDateTime.now().format(Constants.TIME_FORMATTER).toString(), Thread.currentThread().getName(), dish.getNumSerie()));
				return;
			}
			
		}
	}

	private void limpiarPlato(Dish plato) throws InterruptedException {
		//Se muestra un mensaje por pantalla y el hilo se suspende durante unos segundos
		System.out.println(String.format(Constants.WASHER_DOING_CHORES, LocalDateTime.now().format(Constants.TIME_FORMATTER).toString(), Thread.currentThread().getName(), plato.getNumSerie()));
		TimeUnit.SECONDS.sleep(new Random().nextInt(Constants.WASHING_TIME_SPAN) + Constants.WASHING_TIME_MIN);
	}

}
