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
		this.status=Status.IDLE;
	}

	private Status status;
	
	public Status getStatus() {
		return this.status;
	}
	public void setStatus(Status status) {
		if (isValidStatus()) {
			this.status = status;
		}
		
	}
	private boolean isValidStatus () {
		if (status==Status.FALLING||status==Status.IDLE) {
			return true;
		}
		else
			return false;
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
	public  boolean isValidPosition(Vector position) {
		//TODO: isValidPosition uitwerken
		if (position==null){
			return true;
		}
		//TODO: updaten van maxcoordinates
		double[] arrayposition=  position.toArray();
		for(int i=0;i<3;i++){
			if (arrayposition[i]>world.maxCoordinates()[i]+1) {
				return false;
			}
		}
		if (this.getWorld().isSolidGround(position.getCubeX(), position.getCubeY(), position.getCubeZ())){
			return false;
		}
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
	
	/**
	 * Check whether the given World is a valid World for this GameObject
	 * @param world
	 * 			The World to be checked.
	 * @return	If this GameObject has been terminated or a Unit is carrying this GameObject,
	 * 			true if the given World is the null reference.
	 * 			If this GameObject has not been terminated and no Unit is carrying it,
	 * 			true if the given World is not the null reference.
	 */
	boolean canHaveAsWorld(World world) {
		if (this.isTerminated())
			return (world == null);
		else
			return (world != null);
	}
	
	/**
	 * Return the World this GameObject currently exists in.
	 */
	World getWorld(){
		return this.world;
	}
	
	/**
	 * Add this GameObject to the given World.
	 * @param 	world
	 * 			The World to add this GameObject to.
	 * @pre		The given World is a valid World for this GameObject.
	 * @post	This GameObject's world is the given world
	 * @post	This GameObject has been added to the given World's GameObjects
	 */
	void addToWorld(World world){
		assert (this.canHaveAsWorld(world));
		this.setWorld(world);
		world.addGameObject(this);
	}
	
	/**
	 * Set this GameObject's World to the given World
	 * @param 	world
	 * 			The new World for this GameObject
	 * @pre		The given World is a valid World for this GameObject.
	 * @post	This GameObject's World is the given World.
	 */
	private void setWorld(World world){
		assert (this.canHaveAsWorld(world));
		this.world = world;
	}
	
	/**
	 * Variable registering the World this GameObject exists in.
	 */
	private World world;
	
	/**
	 * Return a boolean reflecting whether this GameObject has been terminated.
	 */
	boolean isTerminated(){
		return this.isTerminated;
	}
	
	/**
	 * Terminate this GameObject.
	 */
	void terminate(){
		//TODO: terminate schrijven.
	}
	private final Vector fallspeed=new Vector(0, 0, -3);
	
	/**
	 * Variable registering whether this GameObject has been terminated.
	 */
	private boolean isTerminated;
	public void advanceTime(double time){
		//TODO: constantes in aparte file?
		
		if (!world.isSolidGround(this.position.getCubeX(),this.position.getCubeY(),this.position.getCubeZ()-1)&&this.getPosition().getCubeZ()-1!=0) {
			setStatus(Status.FALLING);
			this.setPosition(new Vector(this.getPosition().getCubeX()+CUBELENGTH/2,this.getPosition().getCubeY()+CUBELENGTH/2,this.getPosition().getZ()));

		}
	if (status==Status.FALLING) {
		fall(time);
	}
	}
	private void fall(double time){
		Vector displacement = fallspeed.scalarMultiply(time);
		Vector new_pos = this.getPosition().add(displacement);
		if (world.isSolidGround( this.getPosition().getCubeX(), this.getPosition().getCubeY(), this.getPosition().getCubeZ()-1)|| (this.getPosition().getCubeZ()==0)){
			this.setPosition(new Vector(this.getPosition().getCubeX()+CUBELENGTH/2, this.getPosition().getCubeY()+CUBELENGTH/2, this.getPosition().getCubeZ()+CUBELENGTH/2));
			this.setStatus(Status.IDLE);
			
			
		}
		
		else{
			this.setPosition(new_pos);
			}
		
	}
}
