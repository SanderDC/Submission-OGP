package hillbillies.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @author Sander
 *
 * @invar  The position of each GameObject must be a valid position for any
 *         GameObject.
 */
public abstract class GameObject {

	/**
	 * Initialize this new GameObject with given position and random weight.
	 * @param  	position
	 *         	The position for this new GameObject.
	 * @post	The GameObject's weight equals a random value between 10 and 50, inclusive.
	 * @effect 	The position of this new GameObject is set to
	 *         	the given position.
	 */
	public GameObject(Vector position) throws IllegalArgumentException{
		this.setPosition(position);
		Random random = new Random();
		this.weight= random.nextInt(41)+10;
	}

	/**
	 * Return the position of this GameObject.
	 */
	@Basic @Raw
	public Vector getPosition() {
		return this.position;
	}

	/**
	 * Check whether the given position is a valid position for
	 * any GameObject.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return 
	 *       | result == 
	 */
	public static boolean isValidPosition(Vector position) {
		//TODO: isValidPosition uitwerken
		return true;
	}

	/**
	 * Set the position of this GameObject to the given position.
	 * 
	 * @param  position
	 *         The new position for this GameObject.
	 * @post   The position of this new GameObject is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *         The given position is not a valid position for any
	 *         GameObject.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(Vector position) 
			throws IllegalArgumentException {
		if (! isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}

	/**
	 * Variable registering the position of this GameObject.
	 */
	private Vector position;

	/**
	 * Return this GameObject's weight
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Variable registering this GameObject's weight
	 */
	private final int weight;

}
