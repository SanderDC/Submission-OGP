package hillbillies.model;

import java.util.Random;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Log {
	public Log(Vector position){
		Random random= new Random();
		this.weight= random.nextInt(41)+10;
		this.position= position;
	}
	private Vector position;
	private final int weight;
	public int getWeight() {
		return this.weight;
	}
	private static boolean isValidWeight(int weight) {
		if (weight>=10||weight<=50)
			return true;
		else {
			return false;
		}

	}
	public Vector getPosition() {
		return this.position;
	}
	 

}
