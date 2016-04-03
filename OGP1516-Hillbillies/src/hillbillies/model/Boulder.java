package hillbillies.model;

public class Boulder extends GameObject {
	
	/**
	 * Initialize a new Boulder with a given position and random weight
	 * @param 	position
	 * 			The position for this new Boulder
	 * @post	The Boulder's position equals the given position.
	 * @post	The Boulder's weight is a random value between 10 and 50, inclusive.
	 */
	public Boulder(Vector position, World world){
		super(position, world);
	}
}
