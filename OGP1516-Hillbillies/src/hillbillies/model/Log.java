package hillbillies.model;

import java.util.Random;

public class Log {
	public Log(Vector position){
		Random random= new Random();
		this.weight= random.nextInt(41)+10;
	}
	private Vector position;
	private final int weight;
	public int getWeight() {
		return this.weight;
	}
}
