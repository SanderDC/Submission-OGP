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
	public GameObject(Vector position, World world) throws IllegalArgumentException{
		this.addToWorld(world);
		this.setPosition(position);
		Random random = new Random();
		this.weight= random.nextInt(41)+10;
		this.status=Status.IDLE;
	}
	/**
	 * Variable registering this Unit's current status.
	 */
	private Status status;
	
	private Status getStatus() {
		return this.status;
	}
	private void setStatus(Status status) {
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
		
		if (position==null){
			return true;
		}
		
		double[] arrayposition=  position.toArray();
		for(int i=0;i<3;i++){
			if (arrayposition[i]>=(this.getWorld().maxCoordinates()[i])+1) {
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
		return true;
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
	 * Remove this gameObject from its World
	 * @pre	The game World of this GameObject is not the null reference.
	 * 		| this.getWorld() != null
	 * @post	The game World of this gameObject is the null reference.
	 * 			| this.getWorld() == null
	 * @post	This GameObject has been removed from its game World
	 * 			| !(this.getWorld()).hasAsGameObject(this)
	 */
	void removeFromWorld(){
		assert (this.getWorld() != null);
		World oldWorld = this.getWorld();
		this.setWorld(null);
		oldWorld.removeGameObject(this);
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
	 * 
	 * Terminate this Unit
	 * @post	This Unit has been terminated
	 * 			| new.isTerminated() == true
	 * @post	This Gameobject has been removed from its World
	 * 			| (new this).getWorld() == null
	 * 			| (new this.getWorld()).hasAsGameobject(this) == false
	 
	 */
	 
	void terminate(){
		this.isTerminated=true;
		World oldWorld= this.world;
		this.world=null;
		oldWorld.removeGameObject(this);
	}
	/**
	 * the speed this Gameobject will fall
	 */
	private final Vector fallspeed=new Vector(0, 0, -3);
	
	/**
	 * Variable registering whether this GameObject has been terminated.
	 */
	private boolean isTerminated;
	/**
	 * advancing the Gameobjects time
	 * @param time
	 * 		The time to advance the gametime with.
	 * @effect if this Gameobject has no solidground underneath it, it will fall
	 * 		if!( (this.getPosition().getCubeZ()==0) || world.isSolidGround(this.position.getCubeX(),this.position.getCubeY(),this.position.getCubeZ()-1))
	 * 		then setStatus(Status.FALLING)&&this.setPosition(new Vector(this.getPosition().getCubeX()+World.CUBELENGTH/2,
										this.getPosition().getCubeY()+World.CUBELENGTH/2,
										this.getPosition().getZ()))
	 * 
	 * *@throws IllegalArgumentException
	 * 			The given time is an illegal time.
	 * 			| (time < 0) || (time > 0.2)
	 */
	public void advanceTime(double time){
		
		if (!( (this.getPosition().getCubeZ()==0) || world.isSolidGround(this.position.getCubeX(),this.position.getCubeY(),this.position.getCubeZ()-1))) {
			setStatus(Status.FALLING);
			this.setPosition(new Vector(this.getPosition().getCubeX()+World.CUBELENGTH/2,
										this.getPosition().getCubeY()+World.CUBELENGTH/2,
										this.getPosition().getZ()));

		}
	if (status==Status.FALLING) {
		fall(time);
	}
	}
	/**
	 * Update the GameObject's position as it is falling
	 * @param time
	 * 			The time used to calculate the new position for this GameObject.
	 * @post	If the GameObject is not arriving at or surpassing a valid position,
	 * 			its speed times the given time is added to its position
	 * @post	If the GameObject is arriving at or surpassing a valid position,
	 * 			its position is set to the valid position, and its status will be set to IDLE
	 */
	private void fall(double time){
		Vector displacement = fallspeed.scalarMultiply(time);
		Vector new_pos = this.getPosition().add(displacement);
		if ((this.getPosition().getCubeZ()==0) || world.isSolidGround(this.getPosition().getCubeX(), this.getPosition().getCubeY(), this.getPosition().getCubeZ()-1)){
			this.setPosition(new Vector(this.getPosition().getCubeX()+World.CUBELENGTH/2,
										this.getPosition().getCubeY()+World.CUBELENGTH/2,
										this.getPosition().getCubeZ()+World.CUBELENGTH/2));
			this.setStatus(Status.IDLE);
			
			
		}
		
		else{
			this.setPosition(new_pos);
			}
		
	}
	/**
	 * 
	 *
	 * @post	This Gameobject has been removed from its World
	 * 			| (new this).getWorld() == null
	 * 			| (new this.getWorld()).hasAsGameobject(this) == false
	 * @post	This Gameobject's position has been set to null
	 * 			this.getnewPosition==null
	 *
	 */
	public void pickedUp(Unit unit) {
		World oldWorld= this.world;
		setWorld(null);
		oldWorld.removeGameObject(this);
		this.setPosition(null);
		
	}
	/**
	 * @post	This Gameobject's position has been set to null
	 * 			this.getnewPosition==unit.getPosition()
	 * @post	This Gameobject has been added to a World
	 * 			| (new this).getWorld() == unit.getWorld
	 *			| (new this.getWorld()).hasAsGameobject(this) == true
	 *@post		This gameobject is no longer being carried by a unit
	 *			unit.getnewGameobject==null
	 */
	public void dropped(Unit unit) {
		this.world=unit.getWorld();
		setPosition(unit.getPosition());
		this.world.addGameObject(this);
		unit.setGameObject(null);
	}
	
}
