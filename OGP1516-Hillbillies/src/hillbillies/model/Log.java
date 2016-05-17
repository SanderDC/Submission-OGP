package hillbillies.model;

public class Log extends GameObject {
	
	/**
	 * Initialize a new Log with given position and random weight.
	 * @param 	position
	 * 			The position for this new Log.
	 * @post	The Log's position equals the given position
	 * @post	The Log's weight is a random value between 10 and 50, inclusive.
	 */
	public Log(Vector position, World world){
		super(position, world);
	}
	
}
