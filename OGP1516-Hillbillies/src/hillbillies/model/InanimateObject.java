package hillbillies.model;

import java.util.Random;

public abstract class InanimateObject extends GameObject {
	
	/**
	 * Initialize a new InanimateObject a the given position in the given World
	 * @param position
	 * 			The position to initialize this InanimateObject at
	 * @param world
	 * 			The World to initialize this new InanimateObject in
	 * @post	This InanimateObject has the given World as its World
	 * @post	This InanimateObject has the given Vector as its position
	 * @post	This InanimateObject has a random weight between 10 and 50, inclusive
	 * @throws IllegalArgumentException
	 * 			The given World is not a valid World for this new InanimateObject
	 * @throws IllegalArgumentException
	 * 			The given Vector is not a valid Position for this new InanimateObject
	 * 			
	 */
	public InanimateObject(Vector position, World world) throws IllegalArgumentException {
		super(position, world);
		this.setWeight(new Random().nextInt(41) + 10);
	}
	
	/**
	 * Check whether the given weight is a valid weight for this InanimateObject
	 * @return	true if and only if the given weight lies between 10 and 50, inclusive
	 */
	@Override
	public boolean canHaveAsWeight(int weight) {
		return (weight >= 10 && weight <= 50);
	}
	
	/**
	 * Check whether the given Status is a valid Status for this InanimateObject
	 * @return true if the given Status is either Status.IDLE or Status.FALLING
	 */
	@Override
	protected boolean isValidStatus(Status status) {
		return status == Status.FALLING || status == Status.IDLE;
	}
	
	

}
