package codes;

import java.util.concurrent.ArrayBlockingQueue;

public class Tray {

	//Lista de tipo Plato que representan las bandejas de platos
	private final ArrayBlockingQueue<Dish> dish_pile = new ArrayBlockingQueue<Dish>(Constants.DISH_LIMIT);

	public void placeDish(Dish plato) throws InterruptedException {
		//Metodo con seguridad para recoger un plato de la bandeja consciente de la concurrencia de los hilos
		dish_pile.put(plato);
		//System.out.println(String.format("Plato %d colocado en la bandeja", plato.getNumSerie()));
	}
	
	public Dish pickUpDish() throws InterruptedException {
		//Metodo con seguridad para recoger un plato de la bandeja consciente de la concurrencia de los hilos
		Dish dish = dish_pile.take();
		//System.out.println(String.format("Plato %d recogido de la bandeja", plato.getNumSerie()));
		return dish;
	}
}
