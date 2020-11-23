package codes;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Dryer implements Runnable {

	private Tray tray_of_washedDishes;
	private Tray tray_of_driedDishes;
	
	public Dryer(Tray tray_of_washedDishes, Tray tray_of_driedDishes) {
		this.tray_of_washedDishes = tray_of_washedDishes;
		this.tray_of_driedDishes = tray_of_driedDishes;
	}

	@Override
	public void run() {
		//Metodo que contiene las instrucciones del hilo secundario a ejecutar
		Dish dish = null;
		while (!Thread.currentThread().isInterrupted()) {
			try {
				//El hilo coge un plato de la bandeja de platos limpios
				dish = tray_of_washedDishes.pickUpDish();
				secarPlato(dish);
			} catch(InterruptedException e) {
				System.out.println(String.format(Constants.INTERRUPTED_THREAD_PICKING_DISH, LocalDateTime.now().format(Constants.TIME_FORMATTER).toString(), Thread.currentThread().getName(), dish.getNumSerie()));
				return;
			}


			try {
				//El hilo coloca el plato seco en la bandeja de platos secos
				tray_of_driedDishes.placeDish(dish);
			} catch(InterruptedException e) {
				System.out.println(String.format(Constants.INTERRUPTED_THREAD_PLACING_DISH, LocalDateTime.now().format(Constants.TIME_FORMATTER).toString(), Thread.currentThread().getName(), dish.getNumSerie()));
				return;
			}
		}
	}

	private void secarPlato(Dish dish) throws InterruptedException {
		//Se muestra un mensaje por pantalla y el hilo se suspende durante unos segundos
		System.out.println(String.format(Constants.DRYER_DOING_CHORES, LocalDateTime.now().format(Constants.TIME_FORMATTER).toString(), Thread.currentThread().getName(), dish.getNumSerie()));
		TimeUnit.SECONDS.sleep(new Random().nextInt(Constants.DRYING_TIME_SPAN) + Constants.DRYING_TIME_MIN);
	}

}
