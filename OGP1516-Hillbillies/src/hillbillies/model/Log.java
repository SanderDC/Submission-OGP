package hillbillies.model;

/**
 * A class of Logs that can exist in a World.
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 */
public class Log extends InanimateObject {
	
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
