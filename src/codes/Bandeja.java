package codes;

import java.util.concurrent.ArrayBlockingQueue;

public class Bandeja {

	private static final int LIMITE_BANDEJA = 20;

	//Lista de tipo Plato que representan las bandejas de platos
	private final ArrayBlockingQueue<Plato> bandeja = new ArrayBlockingQueue<Plato>(LIMITE_BANDEJA);

	public void colocarPlato(Plato plato) throws InterruptedException {
		bandeja.put(plato);
		//System.out.println(String.format("Plato %d colocado en la bandeja", plato.getNumSerie()));
	}
	
	public Plato recogerPlato() throws InterruptedException {
		//Metodo con seguridad para recoger un plato de la bandeja consciente de la concurrencia de los hilos
		Plato plato = bandeja.take();
		//System.out.println(String.format("Plato %d recogido de la bandeja", plato.getNumSerie()));
		return plato;
	}
}
