package hillbillies.model;
import java.nio.DoubleBuffer;
import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/**
 * Documentatie
 * 
 * @invar  	The position of each Unit must be a valid position for any
 *         	Unit.
 *        	| isValidPosition(getPosition())
 * @invar 	The agility of each Unit must be a valid agility for any Unit. 
 * 		  	| isValidAgility(getAgility())
 * @invar	The toughness of each Unit must be a valid toughness for any Unit. 
 * 		  	| isValidToughness(getToughness())
 * @invar 	The strength of each Unit must be a valid strength for any Unit.
 * 		  	| isValidStrength(getStrength())
 * @invar 	The weight of each Unit must be a valid weight for any Unit.
 * 		  	| isValidWeight(getWeight())
 * @invar 	The status of each Unit must be a valid status for any Unit.
 * 		  	| isValidStatus(getStatus())
 * @invar  	The name of each Unit must be a valid name for any
 *         	Unit.
 *        	| isValidName(getName())
 * @invar  	The speed of each Unit must be a valid speed for any
 *         	Unit.
 *       	| isValidSpeed(getSpeed())
 * @invar  	The nearTarget of each Unit must be a valid nearTarget for any
 *         	Unit.
 *       	| isValidNearTarget(getNearTarget())
 * @invar  	The DistantTarget of each Unit must be a valid DistantTarget for any
 *         	Unit.
 *       	| isValidDistantTarget(getDistantTarget())
 * @invar  	The TimeUntilRest of each Unit must be a valid TimeUntilRest for any
 *         	Unit.
 *       	| isValidTimeUntilRest(getTimeUntilRest())
 * 
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 */
public class Unit {

	/**
	 * Initialize a new Unit with given parameters
	 * 
	 * @param  position
	 *         The position for this new Unit.
	 * @param agility
	 *            The agility of the new unit
	 * @param strength
	 *            The strength of the new unit
	 * @param weight
	 *            The weight of the new unit
	 * @param name
	 *            The name of the new unit
	 * @param hitpoints
	 *            The hitpoints of the new unit
	 * @param stamina
	 *            The stamina of the new unit
	 * @param toughness
	 *            The toughness of the new unit
	 * @param maxHitpoint
	 *            The maximum amount of hitpoints of the new unit
	 * @param maxStamina
	 *            The maximum amount of stamina of the new unit
	 * @param orientation
	 *            The orientation of the new unit
	 * 
	 * @effect 	The position of this new Unit is set to
	 *         	the given position.
	 *       	| this.setPosition(position)
	 * @post 	If the given agility lies between the minimum and maximum initial
	 *       	agility, the agility of this Unit equals the given agility.
	 *       	| if (agility >= MIN_INITIAL_AGILITY) && (agility <= MAX_INITIAL_AGILITY)
	 *       	| then new.getAgility() == agility
	 * @post 	If the given agility is larger than the maximum initial agility,
	 *       	the agility of this Unit equals the maximum initial agility.
	 *       	| if (agility > MAX_INITIAL_AGILITY)
	 *       	| then new.getAgility() == MAX_INITIAL_AGILITY
	 * @post 	If the given agility is smaller than the minimum initial agility,
	 *       	the agility of this Unit equals the minimum initial agility.
	 *       	| if (agility < MIN_INITIAL_AGILITY)
	 *       	| then new.getAgility() == MIN_INITIAL_AGILITY
	 *       
	 * @post 	If the given strength lies between the minimum and maximum initial
	 *       	strength, the strength of this Unit equals the given strength.
	 *       	| if (strength >= MIN_INITIAL_STRENGTH) && (strength <= MAX_INITIAL_STRENGTH)
	 *       	| then new.getStrength() == strength
	 * @post 	If the given strength is larger than the maximum initial strength,
	 *       	the strength of this Unit equals the maximum initial strength.
	 *       	| if (strength > MAX_INITIAL_STRENGTH)
	 *       	| then new.getStrength() == MAX_INITIAL_STRENGTH
	 * @post 	If the given strength is smaller than the minimum initial strength,
	 *       	the strength of this Unit equals the minimum initial strength. | if
	 *       	(strength < MIN_INITIAL_STRENGTH) | then new.getStrength() ==
	 *       	MIN_INITIAL_STRENGTH
	 *       
	 * @post 	If the given toughness lies between the minimum and maximum initial
	 *       	toughness, the toughness of this Unit equals the given toughness.
	 *       	| if (toughness >= MIN_INITIAL_TOUGHNESS) && (toughness <= MAX_INITIAL_TOUGHNESS)
	 *       	| then new.getToughness() == toughness
	 * @post 	If the given toughness is larger than the maximum initial toughness,
	 *       	the toughness of this Unit equals the maximum initial toughness.
	 *       	| if (toughness > MAX_INITIAL_TOUGHNESS)
	 *       	| then new.getToughness() == MAX_INITIAL_TOUGHNESS
	 * @post 	If the given toughness is smaller than the minimum initial toughness,
	 *       	the toughness of this Unit equals the minimum initial toughness.
	 *       	| if (toughness < MIN_INITIAL_TOUGHNESS)
	 *       	| then new.getToughness() == MIN_INITIAL_TOUGHNESS
	 *       
	 * @post 	If the given weight lies between the minimum and maximum initial
	 *       	weight, the weight of this Unit equals the given weight. 
	 *       	| if (weight >= new.getMinInitialWeight()) && (weight <= MAX_INITIAL_WEIGHT)
	 *       	| then new.getWeight() == weight
	 * @post 	If the given weight is larger than the maximum initial weight,
	 *       	the weight of this Unit equals the maximum initial weight. 
	 *       	| if (weight > MAX_INITIAL_WEIGHT)
	 *       	| then new.getWeight() == MAX_INITIAL_WEIGHT
	 * @post 	If the given weight is smaller than the minimum initial weight,
	 *       	the weight of this Unit equals the minimum initial weight. 
	 *       	| if (weight < new.getMinInitialWeight())
	 *       	| then new.getWeight() == new.getMinInitialWeight()
	 *       
	 * @effect 	The name of the new unit is set to the given name 
	 * 		   	| this.setName(name)
	 * @effect 	The maximum amount of hitpoints for this Unit is set. 
	 * 			| this.setMaxHitPoints()
	 * @effect 	The maximum amount of stamina for this Unit is set. 
	 * 			| this.setMaxStamina()
	 * @effect 	The current amount of hitpoints is set to the given amount of
	 *         	hitpoints 
	 *         	| this.setHitpoints(hitpoints)
	 * @effect 	The current amount of stamina is set to the given amount of
	 *         	stamina 
	 *         	| this.setStamina(stamina)
	 * @effect 	The speed of the Unit is set to null
	 * 			| this.setSpeed(null)
	 * @effect 	The nearTarget of this new Unit is set to null
	 *       	| this.setNearTarget(null)
	 * @effect 	The DistantTarget of this new Unit is set to
	 *         	the given DistantTarget.
	 *       	| this.setDistantTarget(null)
	 * @effect 	The TimeUntilRest of this new Unit is set to
	 *         	the standard interval between two rests.
	 *       	| this.setTimeUntilRest(RESTING_INTERVAL)
	 * 
	 */
	public Unit(Vector position, int agility, int strength, int weight, String name, int toughness) throws IllegalArgumentException {

		this.setPosition(position);
		this.setSpeed(null);
		this.setNearTarget(null);
		this.setDistantTarget(null);

		if (agility < MIN_INITIAL_AGILITY)
			agility = MIN_INITIAL_AGILITY;
		else if (agility > MAX_INITIAL_AGILITY)
			agility = MAX_INITIAL_AGILITY;
		this.setAgility(agility);

		if (strength < MIN_INITIAL_STRENGTH)
			strength = MIN_INITIAL_STRENGTH;
		else if (strength > MAX_INITIAL_STRENGTH)
			strength = MIN_INITIAL_STRENGTH;
		this.setStrength(strength);

		if (toughness < MIN_INITIAL_TOUGHNESS)
			toughness = MIN_INITIAL_TOUGHNESS;
		else if (toughness > MAX_INITIAL_TOUGHNESS)
			toughness = MAX_INITIAL_TOUGHNESS;
		this.setToughness(toughness);

		if (weight < this.getMinWeight())
			weight = this.getMinWeight();
		if (weight > MAX_INITIAL_WEIGHT)
			weight = MAX_INITIAL_WEIGHT;
		this.setWeight(weight);

		this.setName(name);
		this.setMaxHitpoints();
		this.setMaxStamina();
		this.setStamina(this.getmaxStamina());
		this.setHitpoints(this.getmaxHitpoints());
		this.setStatus(Status.IDLE);
		this.setTimeUntilRest(RESTING_INTERVAL);
		this.orientation = Math.PI;
		this.sprinting=false;
		this.numberSpeed=0;
		this.defaultBehaviorBoolean=true;
	}

	private double numberSpeed;
	private boolean defaultBehaviorBoolean;
	/**
	 * Return the defaultbehaviorboolean of this Unit.
	 */
	@Basic @Raw
	public boolean getdefaultbehaviorboolean() {
		return this.defaultBehaviorBoolean;
	}
	public void setDefaultBehaviorBoolean(boolean defaultBehaviorBoolean) {
		this.defaultBehaviorBoolean = defaultBehaviorBoolean;
	}

	private boolean sprinting;
	public boolean getSprinting(){
		return this.sprinting;
	}


	public void setSprinting(boolean sprinting)throws IllegalStateException {
		if (this.getStatus()!=Status.MOVING) {
			throw new IllegalStateException();
		}
		else {
			this.sprinting = sprinting;

		}
	}
	/**
	 * Return the position of this Unit.
	 */
	@Basic @Raw
	public Vector getPosition() {
		return this.position;
	}

	/**
	 * Return the x-coordinate of the cube currently occupied by the Unit
	 */
	@Basic @Raw
	public double getCubeX(){
		return Math.floor(this.getPosition().getX());
	}

	/**
	 * Return the y-coordinate of the cube currently occupied by the Unit
	 */
	@Basic @Raw
	public double getCubeY(){
		return Math.floor(this.getPosition().getY());
	}

	/**
	 * Return the z-coordinate of the cube currently occupied by the Unit
	 */
	@Basic @Raw
	public double getCubeZ(){
		return Math.floor(this.getPosition().getZ());
	}

	/**
	 * Check whether the given position is a valid position for
	 * any Unit.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return 
	 *       | result == (position != null)
	 *       |			 for each component in position.toArray():
	 *       |				(component >= MIN_COORDINATE) &&
	 *       |				(component < MAX_COORDINATE)
	 */
	public static boolean isValidPosition(Vector position) {
		if (position == null)
			return false;
		for (double component:position.toArray()){
			if ((component < MIN_COORDINATE) || (component >= MAX_COORDINATE))
				return false;
		}
		return true;
	}

	/**
	 * Set the position of this Unit to the given position.
	 * 
	 * @param  position
	 *         The new position for this Unit.
	 * @post   The position of this new Unit is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *         The given position is not a valid position for any
	 *         Unit.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(Vector position) 
			throws IllegalArgumentException {
		if (! isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}

	public static final int MAX_COORDINATE = 50;

	public static final int MIN_COORDINATE = 0;

	public static final double CUBELENGTH = 1;

	/**
	 * Variable registering the position of this Unit.
	 */
	private Vector position;

	/**
	 * Return the speed of this Unit.
	 */
	@Basic @Raw
	public Vector getSpeed() {
		return this.speed;
	}

	/**
	 * Check whether the given speed is a valid speed for
	 * any Unit.
	 *  
	 * @param  speed
	 *         The speed to check.
	 * @return 
	 *       | result == (speed == null) ||
	 *       |			 for each component in speed.toArray():
	 *       |				(component != Double.POSITIVE_INFINITY) &&
	 *       |				(component != Double.NEGATIVE_INFINITY)	
	 */
	public static boolean isValidSpeed(Vector speed) {
		if (speed == null)
			return true;
		for (double component:speed.toArray()){
			if ((component == Double.POSITIVE_INFINITY) || (component == Double.NEGATIVE_INFINITY))
				return false;
		}
		return true;
	}

	/**
	 * Set the speed of this Unit to the given speed.
	 * 
	 * @param  speed
	 *         The new speed for this Unit.
	 * @post   The speed of this new Unit is equal to
	 *         the given speed.
	 *       | new.getSpeed() == speed
	 * @throws IllegalArgumentException
	 *         The given speed is not a valid speed for any
	 *         Unit.
	 *       | ! isValidSpeed(getSpeed())
	 */
	@Raw
	public void setSpeed(Vector speed) 
			throws IllegalArgumentException {
		if (! isValidSpeed(speed))
			throw new IllegalArgumentException();
		this.speed = speed;
	}

	/**
	 * Variable registering the speed of this Unit.
	 */
	private Vector speed;

	/**
	 * Return the agility of this Unit.
	 */
	@Basic
	@Raw
	public int getAgility() {
		return this.agility;
	}

	/**
	 * Return the NearTarget of this Unit.
	 */
	@Basic @Raw
	public Vector getNearTarget() {
		return this.NearTarget;
	}

	/**
	 * Check whether the given NearTarget is a valid NearTarget for
	 * any Unit.
	 *  
	 * @param  NearTarget
	 *         The NearTarget to check.
	 * @return 
	 *       | result == (target == null) ||
	 *       				(isValidPosition(target))
	 */
	public static boolean isValidNearTarget(Vector target) {
		if (target == null)
			return true;
		return isValidPosition(target);
	}

	/**
	 * Set the NearTarget of this Unit to the given NearTarget.
	 * 
	 * @param  target
	 *         The new NearTarget for this Unit.
	 * @post   The NearTarget of this new Unit is equal to
	 *         the given nearTarget.
	 *       | new.getNearTarget() == target
	 * @throws IllegalArgumentException
	 *         The given NearTarget is not a valid NearTarget for any
	 *         Unit.
	 *       | ! isValidNearTarget(getNearTarget())
	 */
	@Raw
	public void setNearTarget(Vector target) 
			throws IllegalArgumentException {
		if (! isValidNearTarget(target))
			throw new IllegalArgumentException();
		this.NearTarget = target;
	}

	/**
	 * Variable registering the nearTarget of this Unit.
	 */
	private Vector NearTarget;

	/**
	 * Return the DistantTarget of this Unit.
	 */
	@Basic @Raw
	public Vector getDistantTarget() {
		return this.DistantTarget;
	}

	/**
	 * Check whether the given DistantTarget is a valid DistantTarget for
	 * any Unit.
	 *  
	 * @param  DistantTarget
	 *         The DistantTarget to check.
	 * @return 
	 *       | result == (target == null) ||
	 *       |				(isValidPosition(target))
	 */
	public static boolean isValidDistantTarget(Vector target) {
		if (target == null)
			return true;
		return isValidPosition(target);
	}

	/**
	 * Set the DistantTarget of this Unit to the given DistantTarget.
	 * 
	 * @param  target
	 *         The new DistantTarget for this Unit.
	 * @post   The DistantTarget of this new Unit is equal to
	 *         the given DistantTarget.
	 *       | new.getDistantTarget() == target
	 * @throws IllegalArgumentException
	 *         The given DistantTarget is not a valid DistantTarget for any
	 *         Unit.
	 *       | ! isValidDistantTarget(getDistantTarget())
	 */
	@Raw
	public void setDistantTarget(Vector target) 
			throws IllegalArgumentException {
		if (! isValidDistantTarget(target))
			throw new IllegalArgumentException();
		this.DistantTarget = target;
	}

	/**
	 * Variable registering the DistantTarget of this Unit.
	 */
	private Vector DistantTarget;

	/**
	 * Check whether the given agility is a valid agility for any Unit.
	 * 
	 * @param agility
	 *            The agility to check.
	 * @return | result == (agility >= MIN_AGILITY) && (agility <=
	 *         MAX_AGILITY)
	 */
	public static boolean isValidAgility(int agility) {
		return (agility >= MIN_AGILITY) && (agility <= MAX_AGILITY);
	}

	/**
	 * Returns the smallest legal value for a Unit's agility
	 * 
	 * @return The smallest possible agility for all Units in the class.
	 */
	public static final int MIN_AGILITY = 1;

	/**
	 * Returns the largest legal value for a Unit's agility
	 * 
	 * @return The largest possible agility for all Units in the class.
	 */
	public static int MAX_AGILITY = 200;

	/**
	 * Return the smallest legal value for a new Unit's agility
	 */
	public static final int MIN_INITIAL_AGILITY = 25;

	/**
	 * Return the largest legal value for a new Unit's agility
	 */
	public static final int MAX_INITIAL_AGILITY = 100;

	/**
	 * Set the agility of the Unit to the given agility
	 * 
	 * @param newAgility
	 *            The new agility to be set for this Unit
	 * @post If the given agility lies between the minimum and maximum legal
	 *       agility, the Unit's agility equals the given agility | if
	 *       (newAgility >= MIN_AGILITY) && (newAgility <= MAX_AGILITY)
	 *       | then new.getAgility() == newAgility
	 * @post If the given agility is larger than the maximum legal agility, the
	 *       Unit's agility equals the maximum legal agility | if (newAgility >
	 *       MAX_AGILITY) | then new.getAgility() == MAX_AGILITY
	 * @post If the given agility is smaller than the minimum legal agility, the
	 *       Unit's agility equals the minimum legal agility | if (newAgility <
	 *       MIN_AGILITY) | then new.getAgility() == MIN_AGILITY
	 */
	public void setAgility(int newAgility) {
		if (newAgility < MIN_AGILITY) {
			newAgility = MIN_AGILITY;
		} else if (newAgility > MAX_AGILITY) {
			newAgility = MAX_AGILITY;
		}
		this.agility = newAgility;
	}

	/**
	 * Variable registering the agility of this Unit.
	 */
	private int agility;

	/**
	 * Return the strength of this Unit.
	 */
	@Basic
	@Raw
	public int getStrength() {
		return this.strength;
	}

	/**
	 * Check whether the given strength is a valid strength for any Unit.
	 * 
	 * @param strength
	 *            The strength to check.
	 * @return | result == (strength >= MIN_STRENGTH) && (strength <=
	 *         MAX_STRENGTH)
	 */
	public static boolean isValidStrength(int strength) {
		return (strength >= MIN_STRENGTH) && (strength <= MAX_STRENGTH);
	}

	/**
	 * Returns the smallest legal value for a Unit's strength
	 * 
	 * @return The smallest possible strength for all Units in the class.
	 */
	public static final int MIN_STRENGTH = 1;

	/**
	 * Returns the largest legal value for a Unit's strength
	 * 
	 * @return The largest possible strength for all Units in the class.
	 */
	public static final int MAX_STRENGTH = 200;

	/**
	 * Return the smallest legal value for a new Unit's strength
	 */
	public static final int MIN_INITIAL_STRENGTH = 25;

	/**
	 * Return the largest legal value for a new Unit's strength
	 */
	public static final int MAX_INITIAL_STRENGTH = 100;

	/**
	 * Set the strength of the Unit to the given strength
	 * 
	 * @param newStrength
	 *            The new strength to be set for this Unit
	 * @post If the given strength lies between the minimum and maximum legal
	 *       strength, the Unit's strength equals the given strength | if
	 *       (newStrength >= MIN_STRENGTH) && (newStrength <=
	 *       MAX_STRENGTH) | then new.getStrength() == newStrength
	 * @post If the given strength is larger than the maximum legal strength,
	 *       the Unit's strength equals the maximum legal strength | if
	 *       (newStrength > MAX_STRENGTH) | then new.getStrength() ==
	 *       MAX_STRENGTH
	 * @post If the given strength is smaller than the minimum legal strength,
	 *       the Unit's strength equals the minimum legal strength | if
	 *       (newStrength < MIN_STRENGTH) | then new.getStrength() ==
	 *       MIN_STRENGTH
	 */
	public void setStrength(int newStrength) {
		if (newStrength < MIN_STRENGTH) {
			newStrength = MIN_STRENGTH;
		} else if (newStrength > MAX_STRENGTH) {
			newStrength = MAX_STRENGTH;
		}
		this.strength = newStrength;
	}

	/**
	 * Variable registering the strength of this Unit.
	 */
	private int strength;

	/**
	 * Return the weight of this Unit.
	 */
	@Basic
	@Raw
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Check whether the given weight is a valid weight for this Unit.
	 * 
	 * @param weight
	 *            The weight to check.
	 * @return | result == (weight >= this.getMinWeight()) && (weight <=
	 *         MAX_WEIGHT)
	 */
	public boolean isValidWeight(int weight) {
		return (weight >= this.getMinWeight()) && (weight <= MAX_WEIGHT);
	}

	/**
	 * Returns the smallest legal value for a Unit's weight
	 * 
	 * @return The smallest possible weight for this Unit.
	 */
	@Basic
	public int getMinWeight() {
		double temp = this.getAgility() / 2.0 + this.getStrength() / 2.0;
		return (int) Math.ceil(temp);
	}

	/**
	 * Returns the largest legal value for a Unit's weight
	 * 
	 * @return The largest possible weight for all Units in the class.
	 */
	public static final int MAX_WEIGHT = 200;

	/**
	 * Return the largest legal value for a new Unit's weight
	 */
	public static final int MAX_INITIAL_WEIGHT = 100;

	/**
	 * Set the weight of the Unit to the given weight
	 * 
	 * @param newWeight
	 *            The new weight to be set for this Unit
	 * @post If the given weight lies between the minimum and maximum legal
	 *       weight, the Unit's weight equals the given weight | if (newWeight
	 *       >= this.getMinWeight()) && (newWeight <= MAX_WEIGHT) | then
	 *       new.getWeight() == newWeight
	 * @post If the given weight is larger than the maximum legal weight, the
	 *       Unit's weight equals the maximum legal weight | if (newWeight >
	 *       MAX_WEIGHT) | then new.getWeight() == MAX_WEIGHT
	 * @post If the given weight is smaller than the minimum legal weight, the
	 *       Unit's weight equals the minimum legal weight | if (newWeight <
	 *       this.getMinWeight()) | then new.getWeight() == this.getMinWeight()
	 */
	public void setWeight(int newWeight) {
		if (newWeight < this.getMinWeight()) {
			newWeight = this.getMinWeight();
		} else if (newWeight > MAX_WEIGHT) {
			newWeight = MAX_WEIGHT;
		}
		this.weight = newWeight;
	}

	/**
	 * Variable registering the weight of this Unit.
	 */
	private int weight;

	/**
	 * Return the name of this Unit.
	 */
	@Basic @Raw
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether the given name is a valid name for
	 * any Unit.
	 *  
	 * @param  name
	 *         The name to check.
	 * @return 
	 *       | result == (Character.isUpperCase(name.charAt(0))) &&
	 *       |			 (name.length() >= 2) &&
	 *       | 			 for each char in name: (Character.isUpperCase(char)) ||
	 *       |									(Character.isLowerCase(char) ||
	 *       |									(char == ' ') ||
	 *       |									(char == '"') ||
	 *       |									(char == '\'')
	 *       
	 */
	public static boolean isValidName(String name) {
		if (!(name.length() >= 2) || !(Character.isUpperCase(name.charAt(0)))) 
			return false;
		else {
			for (char ch: name.toCharArray()) {
				if (!(Character.isUpperCase(ch)) && !(ch == ' ') && !(ch == '"')
						&& !(Character.isLowerCase(ch)) && !(ch == '\''))
					return false;
			}
		}
		return true;
	}

	/**
	 * Set the name of this Unit to the given name.
	 * 
	 * @param  name
	 *         The new name for this Unit.
	 * @post   The name of this new Unit is equal to
	 *         the given name.
	 *       | new.getName() == name
	 * @throws IllegalArgumentException
	 *         The given name is not a valid name for any
	 *         Unit.
	 *       | ! isValidName(getName())
	 */
	@Raw
	public void setName(String name) 
			throws IllegalArgumentException {
		if (! isValidName(name))
			throw new IllegalArgumentException();
		this.name = name;
	}

	/**
	 * Variable registering the name of this Unit.
	 */
	private String name;

	@Basic
	public int getmaxStamina() {
		return this.maxStamina;
	}

	/*
	 * maxstamina bepalen en opnieuw oppassen vanwege floor
	 */
	private void setMaxStamina() {
		// if (200*toughness*weight%10000==0) {
		// this.maxHitpoints=200*toughness/100*weight/100;
		// }
		// else
		// this.maxHitpoints=200*toughness/100*weight/100+1;
		// }
		double temp = (double) this.getToughness() * (double) this.getWeight() / 50.0;
		this.maxStamina = (int) Math.ceil(temp);
	}

	private int maxStamina;

	@Basic
	public int getmaxHitpoints() {
		return this.maxHitpoints;
	}

	/*
	 * maxhitpoints bepalen en opnieuw oppassen vanwege floor
	 */
	private void setMaxHitpoints() {
		// if (200*toughness*weight%10000==0) {
		//
		//
		// this.maxHitpoints=200*toughness/100*weight/100;
		// }
		// else
		// this.maxHitpoints=200*toughness/100*weight/100+1;
		double temp = (double) this.getToughness() * (double) this.getWeight() / 50.0;
		this.maxHitpoints = (int) Math.ceil(temp);
	}

	private int maxHitpoints;

	/**
	 * Return the toughness of this Unit.
	 */
	@Basic
	@Raw
	public int getToughness() {
		return this.toughness;
	}

	/**
	 * Check whether the given toughness is a valid toughness for any Unit.
	 * 
	 * @param toughness
	 *            The toughness to check.
	 * @return | result == (toughness >= MIN_TOUGHNESS) && (toughness <=
	 *         MAX_TOUGHNESS)
	 */
	public static boolean isValidToughness(int toughness) {
		return (toughness >= MIN_TOUGHNESS) && (toughness <= MAX_TOUGHNESS);
	}

	/**
	 * Returns the smallest legal value for a Unit's toughness
	 * 
	 * @return The smallest possible toughness for all Units in the class.
	 */
	public static final int MIN_TOUGHNESS = 1;

	/**
	 * Returns the largest legal value for a Unit's toughness
	 * 
	 * @return The largest possible toughness for all Units in the class.
	 */
	public static final int MAX_TOUGHNESS = 200;

	/**
	 * Return the smallest legal value for a new Unit's toughness
	 */
	public static final int MIN_INITIAL_TOUGHNESS = 25;

	/**
	 * Return the largest legal value for a new Unit's toughness
	 */
	public static final int MAX_INITIAL_TOUGHNESS = 100;

	/**
	 * Set the toughness of the Unit to the given toughness
	 * 
	 * @param newToughness
	 *            The new toughness to be set for this Unit
	 * @post If the given toughness lies between the minimum and maximum legal
	 *       toughness, the Unit's toughness equals the given toughness | if
	 *       (newToughness >= MIN_TOUGHNESS) && (newToughness <=
	 *       MAX_TOUGHNESS) | then new.getToughness() == newToughness
	 * @post If the given toughness is larger than the maximum legal toughness,
	 *       the Unit's toughness equals the maximum legal toughness | if
	 *       (newToughness > MAX_TOUGHNESS) | then new.getToughness() ==
	 *       MAX_TOUGHNESS
	 * @post If the given toughness is smaller than the minimum legal toughness,
	 *       the Unit's toughness equals the minimum legal toughness | if
	 *       (newToughness < MIN_TOUGHNESS) | then new.getToughness() ==
	 *       MIN_TOUGHNESS
	 */
	public void setToughness(int newToughness) {
		if (newToughness < MIN_TOUGHNESS) {
			newToughness = MIN_TOUGHNESS;
		} else if (newToughness > MAX_TOUGHNESS) {
			newToughness = MAX_TOUGHNESS;
		}
		this.toughness = newToughness;
	}

	/**
	 * Variable registering the toughness of this Unit.
	 */
	private int toughness;

	@Basic
	public int getHitpoints() {
		return this.hitpoints;
	}

	private void setHitpoints(int newHitpoints) {
		this.hitpoints = newHitpoints;
	}

	private int hitpoints;

	@Basic
	public int getStamina() {
		return this.stamina;
	}

	private void setStamina(int newstamina) {
		this.stamina = newstamina;
	}

	private int stamina;

	/*
	 * orientatie nog niet echt uitgewerkt, aangezien ik niet goed uit het
	 * document kon opmaken wat de geldige waardes zijn
	 */
	private double orientation;

	@Basic
	public double getOrientation() {
		return this.orientation;
	}

	private void setOrientation(double orientation) {
		if ((orientation < -Math.PI) || (orientation > Math.PI)) {
			this.orientation = (Math.PI / 2);
		} else {
			this.orientation = orientation;
		}
	}

	/**
	 * Return the status of this Unit.
	 */
	@Basic
	@Raw
	public Status getStatus() {
		return this.status;
	}

	/**
	 * Check whether the given status is a valid status for any Unit.
	 * 
	 * @param status
	 *            The status to check.
	 * @return | result == (status == Status.WORKING) || (status ==
	 *         Status.RESTING) || (status == Status.MOVING) || (status ==
	 *         Status.IDLE)
	 */
	public static boolean isValidStatus(Status status) {
		return (status == Status.WORKING) || (status == Status.RESTING) || (status == Status.MOVING)
				|| (status == Status.IDLE);
	}

	/**
	 * Set the status of this Unit to the given status.
	 * 
	 * @param status
	 *            The new status for this Unit.
	 * @post The status of this new Unit is equal to the given status. |
	 *       new.getStatus() == status
	 * @throws IllegalArgumentException
	 *             The given status is not a valid status for any Unit. | !
	 *             isValidStatus(getStatus())
	 */
	@Raw
	private void setStatus(Status status) throws IllegalArgumentException {
		if (!isValidStatus(status))
			throw new IllegalArgumentException();
		this.status = status;
	}

	/**
	 * Variable registering the status of this Unit.
	 */
	private Status status;

	/** TO BE ADDED TO CLASS HEADING
	 * @invar  The TimeUntilRest of each Unit must be a valid TimeUntilRest for any
	 *         Unit.
	 *       | isValidTimeUntilRest(getTimeUntilRest())
	 */

	/**
	 * Return the TimeUntilRest of this Unit.
	 */
	@Basic @Raw
	public double getTimeUntilRest() {
		return this.timeUntilRest;
	}

	/**
	 * Check whether the given TimeUntilRest is a valid TimeUntilRest for
	 * any Unit.
	 *  
	 * @param  TimeUntilRest
	 *         The TimeUntilRest to check.
	 * @return 
	 *       | result == (time > 0) && (time <= RESTING_INTERVAL)
	 */
	public static boolean isValidTimeUntilRest(double time) {
		return (time > 0) && (time <= RESTING_INTERVAL);
	}

	/**
	 * Set the TimeUntilRest of this Unit to the given TimeUntilRest.
	 * 
	 * @param  	time
	 *         	The new TimeUntilRest for this Unit.
	 * @post   	The TimeUntilRest of this new Unit is equal to
	 *         	the given TimeUntilRest.
	 *       	| new.getTimeUntilRest() == time
	 * @throws 	IllegalArgumentException
	 *         	The given TimeUntilRest is not a valid TimeUntilRest for any
	 *         	Unit.
	 *       	| ! isValidTimeUntilRest(getTimeUntilRest())
	 */
	@Raw
	public void setTimeUntilRest(double time) 
			throws IllegalArgumentException {
		if (! isValidTimeUntilRest(time))
			throw new IllegalArgumentException();
		this.timeUntilRest = time;
	}

	/**
	 * Variable registering the TimeUntilRest of this Unit.
	 */
	private double timeUntilRest;

	public static final double RESTING_INTERVAL = 180;

	/** TO BE ADDED TO CLASS HEADING
	 * @invar  The ActivityTime of each Unit must be a valid ActivityTime for any
	 *         Unit.
	 *       | isValidActivityTime(getActivityTime())
	 */


	/**
	 * Initialize this new Unit with given ActivityTime.
	 *
	 * @param  ActivityTime
	 *         The ActivityTime for this new Unit.
	 * @effect The ActivityTime of this new Unit is set to
	 *         the given ActivityTime.
	 *       | this.setActivityTime(ActivityTime)
	 */
	public Unit(double ActivityTime)
			throws IllegalArgumentException {
		this.setActivityTime(ActivityTime);
	}


	/**
	 * Return the ActivityTime of this Unit.
	 */
	@Basic @Raw
	public double getActivityTime() {
		return this.ActivityTime;
	}

	/**
	 * Check whether the given ActivityTime is a valid ActivityTime for
	 * any Unit.
	 *  
	 * @param  ActivityTime
	 *         The ActivityTime to check.
	 * @return 
	 *       | result == (ActivityTime >= 0)
	 */
	public static boolean isValidActivityTime(double ActivityTime) {
		return (ActivityTime >= 0);
	}

	/**
	 * Set the ActivityTime of this Unit to the given ActivityTime.
	 * 
	 * @param  ActivityTime
	 *         The new ActivityTime for this Unit.
	 * @post   The ActivityTime of this new Unit is equal to
	 *         the given ActivityTime.
	 *       | new.getActivityTime() == ActivityTime
	 * @throws IllegalArgumentException
	 *         The given ActivityTime is not a valid ActivityTime for any
	 *         Unit.
	 *       | ! isValidActivityTime(getActivityTime())
	 */
	@Raw
	public void setActivityTime(double ActivityTime) 
			throws IllegalArgumentException {
		if (! isValidActivityTime(ActivityTime))
			throw new IllegalArgumentException();
		this.ActivityTime = ActivityTime;
	}

	/**
	 * Variable registering the ActivityTime of this Unit.
	 */
	private double ActivityTime;

	public void preparing_attack(Unit other){
		this.updatePosition(other);
		other.updatePosition(this);
		if (timeUntilRest>=1){
			other.defend(this);
		}

	}


	public void defend(Unit attacker){
		if (Math.random() <= 0.20 * this.getAgility() / attacker.getAgility()) {
			attacker.dodge();
		} else if (Math.random() <= 0.25 * (this.getStrength() + this.getAgility())
				/ (attacker.getStrength() + attacker.getAgility())) {
			// unit parryt
		} else {
			// unit krijgt damage
			this.hitpoints=this.getHitpoints()-attacker.getStrength()/10;
		}
	}

	public void dodge() {



	}


	private void updatePosition(Unit other){
		this.orientation=Math.atan2(other.getPosition().getY()-this.getPosition().getY(), other.getPosition().getX()-this.getPosition().getX());
	}


	/*
	 * berekent de snelheid van de unit
	 * 
	 */
	public double calculateBaseSpeed() {
		double baseSpeed = 1.5 * (this.getStrength() + this.getAgility()) / (200 * this.getWeight() / 100);
		return baseSpeed;
	}

	/**
	 * verplaatst de Unit
	 */
	public boolean ismoving(){
		if (this.getStatus()==Status.MOVING){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isresting(){
		if (this.getStatus()==Status.RESTING){
			return true;
		}
		else {
			return false;
		}

	}
	public boolean isAttacking() {
		if (this.getStatus()==Status.ATTACKING){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isWorking() {
		if (this.getStatus()==Status.WORKING){
			return true;
		}
		else {
			return false;
		}
	}
	public void setToWork() {
		this.status=Status.WORKING;
		this.setActivityTime(calculatingWorkTime());
	}


	public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException{
		Vector target = new Vector(this.getCubeX() + dx + CUBELENGTH/2,
				this.getCubeY() + dy + CUBELENGTH/2,
				this.getCubeZ() + dz + CUBELENGTH/2);
		if (! isValidPosition(target))
			throw new IllegalArgumentException();
		if (this.getStatus() == Status.MOVING)
			return;
		double speed = this.calculateBaseSpeed();
		if (Util.fuzzyEquals(this.getPosition().getZ() - target.getZ(), -1))
			speed = 0.5*speed;
		else if (Util.fuzzyEquals(this.getPosition().getZ() - target.getZ(), 1))
			speed = 1.2*speed;
		double d = Math.sqrt(Math.pow((this.getPosition().getX() - target.getX()), 2) 
				+ Math.pow((this.getPosition().getY() - target.getY()), 2)
				+ Math.pow((this.getPosition().getZ() - target.getZ()), 2));
		double speed_x = speed*(target.getX() - this.getPosition().getX())/d;
		double speed_y = speed*(target.getY() - this.getPosition().getY())/d;
		double speed_z = speed*(target.getZ() - this.getPosition().getZ())/d;
		this.setUnitSpeed(speed);
		this.setStatus(Status.MOVING);
		this.setNearTarget(target);
		this.setSpeed(new Vector(speed_x, speed_y, speed_z));
		this.setOrientation(Math.atan2(speed_y, speed_x));
	}

	public void moveTo(int cubeX, int cubeY, int cubeZ)
			throws IllegalArgumentException{
		Vector target = new Vector(cubeX + CUBELENGTH/2, cubeY + CUBELENGTH/2, cubeZ + CUBELENGTH/2);
		if (! isValidPosition(target))
			throw new IllegalArgumentException();
		if (this.getStatus() == Status.MOVING){
			this.setDistantTarget(target);
			return;
		}
		this.setDistantTarget(target);
		this.moveToNextCube();
	}

	private void moveToNextCube(){
		double cubeX = Math.floor(this.getDistantTarget().getX());
		double cubeY = Math.floor(this.getDistantTarget().getY());
		double cubeZ = Math.floor(this.getDistantTarget().getZ());
		int dx; int dy; int dz;
		if (this.getCubeX() == cubeX)
			dx = 0;
		else if (this.getCubeX() < cubeX)
			dx = 1;
		else
			dx = - 1;
		if (this.getCubeY() == cubeY)
			dy = 0;
		else if (this.getCubeY() < cubeY)
			dy = 1;
		else
			dy = -1;
		if (this.getCubeZ() == cubeZ)
			dz = 0;
		else if (this.getCubeZ() < cubeZ)
			dz = 1;
		else
			dz = -1;
		this.moveToAdjacent(dx, dy, dz);
	}

	public double getUnitSpeed() {
		return this.numberSpeed;
	}
	public void setUnitSpeed(double speed){
		this.numberSpeed=speed;
	}
	/*
	 * berekent de tijd nodig voor een "werkje"
	 */
	public double calculatingWorkTime() {
		return 500 / (double) this.getStrength();
	}

	public double calculateMinRestTime(){
		return 40/(double)toughness;
	}
	public void resting()throws IllegalArgumentException{
		if (timeUntilRest<calculateMinRestTime()){
			throw new IllegalArgumentException();
		}
		if (hitpoints<maxHitpoints){}
		else if(stamina<maxStamina) {

		}

	}


	/*
	 * advance time functie
	 */
	public void advanceTime(double time) throws IllegalArgumentException{
		if (time<0||time>0.2)
			throw new IllegalArgumentException();

		/*
		 * eerst testen of we aangevallen worden
		 */
		//	 if (isGettingattacked) {
		//	 this.defend;
		//	
		//	 }
		//	 /*
		//	 * testen of we aan het bewegen zijn
		//	 */
		//	 if (this.isMoving()) {
		//	 this.moveToAdjacent(targetx, targety, targetz, sprinting, time);
		//	
		//	 }
		//	 /*
		//	 * als we niks doen een random activity uitkiezen
		//	 */
		//	 if (isDoingNothing) {
		//	 this.ChooseActivity;
		//	
		//	 }

		switch(this.getStatus()){
		case IDLE:
			break;
		case MOVING:
			this.move(time);
		case RESTING:
			break;
		case WORKING:
			break;
		default:
			break;
		}

	}

	private void move(double time) {
		Vector displacement;
		if (this.getSprinting()){
			displacement = this.getSpeed().scalarMultiply(2*time);
			this.setStamina(this.getStamina()-(int) (10*time));
			if (this.getStamina() == 0)
				this.setSprinting(false);
		} else 
			displacement = this.getSpeed().scalarMultiply(time);
		Vector new_pos = this.getPosition().add(displacement);
		if ((this.getNearTarget().liesBetween(this.getPosition(), new_pos)) ||
				(this.getNearTarget().equals(new_pos))){
			this.setPosition(this.getNearTarget());
			this.setSpeed(null);
			this.setSprinting(false);
			this.setStatus(Status.IDLE);
			if (this.getDistantTarget() != null){
				if (this.getDistantTarget().equals(this.getNearTarget())){
					this.setDistantTarget(null);
					this.setNearTarget(null);
				}
				else
					this.setNearTarget(null);
					this.moveToNextCube();
			} else
				this.setNearTarget(null);
		} else
			this.setPosition(new_pos);
//		if ((this.getDistantTarget() != null) &&(this.getDistantTarget().liesBetween(this.getPosition(), new_pos)) ||
//				(this.getDistantTarget().equals(new_pos))){
//			this.setPosition(this.getDistantTarget());
//			this.setDistantTarget(null);
//			this.setNearTarget(null);
//			this.setSpeed(null);
//			this.setSprinting(false);
//			this.setStatus(Status.IDLE);
//		}
//		else if ((this.getNearTarget().liesBetween(this.getPosition(), new_pos)) ||
//				(this.getNearTarget().equals(new_pos))){
//			this.setPosition(this.getNearTarget());
//			this.setSpeed(null);
//			this.setNearTarget(null);
//			this.setSprinting(false);
//			this.setStatus(Status.IDLE);
//			if (this.getDistantTarget() != null)
//				this.moveToNextCube();
//		} else
//			this.setPosition(new_pos);
	}

	private void defaultbehavior(){
		if(this.getStatus()==Status.IDLE){
			Random randomgenerator= new Random();
			int randomnumber=randomgenerator.nextInt(3);
			if (randomnumber==0){
				this.moveTo(randomgenerator.nextInt(MAX_COORDINATE),
						randomgenerator.nextInt(MAX_COORDINATE),
						randomgenerator.nextInt(MAX_COORDINATE));
				int randomnumber1=randomgenerator.nextInt(2);
				if (randomnumber1==1) {
					setSprinting(true);
				}
				else{
					setSprinting(false);

				}}
			if (randomnumber==1){
				this.setToWork();}
			if (randomnumber==2){
				this.resting();}
		}
	}


}
