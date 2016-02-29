package hillbillies.model;
import java.nio.DoubleBuffer;
//import java.util.Vector;

import be.kuleuven.cs.som.annotate.*;

/**
 * Documentatie
 * 
 * @invar  The position of each Unit must be a valid position for any
 *         Unit.
 *        | isValidPosition(getPosition())
 * @invar The agility of each Unit must be a valid agility for any Unit. 
 * 		  | isValidAgility(getAgility())
 * @invar The toughness of each Unit must be a valid toughness for any Unit. 
 * 		  | isValidToughness(getToughness())
 * @invar The strength of each Unit must be a valid strength for any Unit.
 * 		  | isValidStrength(getStrength())
 * @invar The weight of each Unit must be a valid weight for any Unit.
 * 		  | isValidWeight(getWeight())
 * @invar The status of each Unit must be a valid status for any Unit.
 * 		  | isValidStatus(getStatus())
 * @invar  The name of each Unit must be a valid name for any
 *         Unit.
 *        | isValidName(getName())
 * @invar  The speed of each Unit must be a valid speed for any
 *         Unit.
 *       | isValidSpeed(getSpeed())
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
	 * @effect The position of this new Unit is set to
	 *         the given position.
	 *       	| this.setPosition(position)
	 * @post 	If the given agility lies between the minimum and maximum initial
	 *       	agility, the agility of this Unit equals the given agility.
	 *       	| if (agility >= getMinInitialAgility()) && (agility <= getMaxInitialAgility())
	 *       	| then new.getAgility() == agility
	 * @post 	If the given agility is larger than the maximum initial agility,
	 *       	the agility of this Unit equals the maximum initial agility.
	 *       	| if (agility > getMaxInitialAgility())
	 *       	| then new.getAgility() == getMaxInitialAgility()
	 * @post 	If the given agility is smaller than the minimum initial agility,
	 *       	the agility of this Unit equals the minimum initial agility.
	 *       	| if (agility < getMinInitialAgility())
	 *       	| then new.getAgility() == getMinInitialAgility()
	 *       
	 * @post 	If the given strength lies between the minimum and maximum initial
	 *       	strength, the strength of this Unit equals the given strength.
	 *       	| if (strength >= getMinInitialStrength()) && (strength <= getMaxInitialStrength())
	 *       	| then new.getStrength() == strength
	 * @post 	If the given strength is larger than the maximum initial strength,
	 *       	the strength of this Unit equals the maximum initial strength.
	 *       	| if (strength > getMaxInitialStrength())
	 *       	| then new.getStrength() == getMaxInitialStrength()
	 * @post 	If the given strength is smaller than the minimum initial strength,
	 *       	the strength of this Unit equals the minimum initial strength. | if
	 *       	(strength < getMinInitialStrength()) | then new.getStrength() ==
	 *       	getMinInitialStrength()
	 *       
	 * @post 	If the given toughness lies between the minimum and maximum initial
	 *       	toughness, the toughness of this Unit equals the given toughness.
	 *       	| if (toughness >= getMinInitialToughness()) && (toughness <= getMaxInitialToughness())
	 *       	| then new.getToughness() == toughness
	 * @post 	If the given toughness is larger than the maximum initial toughness,
	 *       	the toughness of this Unit equals the maximum initial toughness.
	 *       	| if (toughness > getMaxInitialToughness())
	 *       	| then new.getToughness() == getMaxInitialToughness()
	 * @post 	If the given toughness is smaller than the minimum initial toughness,
	 *       	the toughness of this Unit equals the minimum initial toughness.
	 *       	| if (toughness < getMinInitialToughness())
	 *       	| then new.getToughness() == getMinInitialToughness()
	 *       
	 * @post 	If the given weight lies between the minimum and maximum initial
	 *       	weight, the weight of this Unit equals the given weight. 
	 *       	| if (weight >= new.getMinInitialWeight()) && (weight <= getMaxInitialWeight())
	 *       	| then new.getWeight() == weight
	 * @post 	If the given weight is larger than the maximum initial weight,
	 *       	the weight of this Unit equals the maximum initial weight. 
	 *       	| if (weight > getMaxInitialWeight())
	 *       	| then new.getWeight() == getMaxInitialWeight()
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
	 * 			
	 * 
	 */
	public Unit(Vector position, int agility, int strength, int weight, String name, int toughness) throws IllegalArgumentException {

		this.setPosition(position);
		this.setSpeed(null);

		if (agility < getMinInitialAgility())
			agility = getMinInitialAgility();
		else if (agility > getMaxInitialAgility())
			agility = getMaxInitialAgility();
		this.setAgility(agility);

		if (strength < getMinInitialStrength())
			strength = getMinInitialStrength();
		else if (strength > getMaxInitialStrength())
			strength = getMinInitialStrength();
		this.setStrength(strength);

		if (toughness < getMinInitialToughness())
			toughness = getMinInitialToughness();
		else if (toughness > getMaxInitialToughness())
			toughness = getMaxInitialToughness();
		this.setToughness(toughness);

		if (weight < this.getMinWeight())
			weight = this.getMinWeight();
		if (weight > getMaxInitialWeight())
			weight = getMaxInitialWeight();
		this.setWeight(weight);

		// TODO: alternatief wanneer agility/... Niet valid is

		this.setName(name);
		this.setMaxHitpoints();
		this.setMaxStamina();
		this.setStamina(this.getmaxStamina());
		this.setHitpoints(this.getmaxHitpoints());
		this.speed= new Vector(0, 0, 0);
		this.setStatus(Status.IDLE);
		this.orientation = Math.PI;
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
	 *       |				(component >= getMinCoordinate()) &&
	 *       |				(component < getMaxCoordinate())
	 */
	public static boolean isValidPosition(Vector position) {
		if (position == null)
			return false;
		for (double component:position.toArray()){
			if ((component < getMinCoordinate()) || (component >= getMaxCoordinate()))
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

	public static int getMaxCoordinate() {
		return 50;
	}

	public static int getMinCoordinate() {
		return 0;
	}

	/**
	 * Return the agility of this Unit.
	 */
	@Basic
	@Raw
	public int getAgility() {
		return this.agility;
	}

	/**
	 * Check whether the given agility is a valid agility for any Unit.
	 * 
	 * @param agility
	 *            The agility to check.
	 * @return | result == (agility >= getMinAgility()) && (agility <=
	 *         getMaxAgility())
	 */
	public static boolean isValidAgility(int agility) {
		return (agility >= getMinAgility()) && (agility <= getMaxAgility());
	}

	/**
	 * Returns the smallest legal value for a Unit's agility
	 * 
	 * @return The smallest possible agility for all Units in the class.
	 */
	@Basic
	public static int getMinAgility() {
		return 1;
	}

	/**
	 * Returns the largest legal value for a Unit's agility
	 * 
	 * @return The largest possible agility for all Units in the class.
	 */
	@Basic
	public static int getMaxAgility() {
		return 200;
	}

	/**
	 * Return the smallest legal value for a new Unit's agility
	 */
	@Basic
	public static int getMinInitialAgility(){
		return 25;
	}

	/**
	 * Return the largest legal value for a new Unit's agility
	 */
	@Basic
	public static int getMaxInitialAgility(){
		return 100;
	}

	/**
	 * Set the agility of the Unit to the given agility
	 * 
	 * @param newAgility
	 *            The new agility to be set for this Unit
	 * @post If the given agility lies between the minimum and maximum legal
	 *       agility, the Unit's agility equals the given agility | if
	 *       (newAgility >= getMinAgility()) && (newAgility <= getMaxAgility())
	 *       | then new.getAgility() == newAgility
	 * @post If the given agility is larger than the maximum legal agility, the
	 *       Unit's agility equals the maximum legal agility | if (newAgility >
	 *       getMaxAgility()) | then new.getAgility() == getMaxAgility()
	 * @post If the given agility is smaller than the minimum legal agility, the
	 *       Unit's agility equals the minimum legal agility | if (newAgility <
	 *       getMinAgility()) | then new.getAgility() == getMinAgility()
	 */
	public void setAgility(int newAgility) {
		if (newAgility < getMinAgility()) {
			newAgility = getMinAgility();
		} else if (newAgility > getMaxAgility()) {
			newAgility = getMaxAgility();
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
	 * @return | result == (strength >= getMinStrength()) && (strength <=
	 *         getMaxStrength())
	 */
	public static boolean isValidStrength(int strength) {
		return (strength >= getMinStrength()) && (strength <= getMaxStrength());
	}

	/**
	 * Returns the smallest legal value for a Unit's strength
	 * 
	 * @return The smallest possible strength for all Units in the class.
	 */
	@Basic
	public static int getMinStrength() {
		return 1;
	}

	/**
	 * Returns the largest legal value for a Unit's strength
	 * 
	 * @return The largest possible strength for all Units in the class.
	 */
	@Basic
	public static int getMaxStrength() {
		return 200;
	}

	/**
	 * Return the smallest legal value for a new Unit's strength
	 */
	@Basic
	public static int getMinInitialStrength(){
		return 25;
	}

	/**
	 * Return the largest legal value for a new Unit's strength
	 */
	@Basic
	public static int getMaxInitialStrength(){
		return 100;
	}

	/**
	 * Set the strength of the Unit to the given strength
	 * 
	 * @param newStrength
	 *            The new strength to be set for this Unit
	 * @post If the given strength lies between the minimum and maximum legal
	 *       strength, the Unit's strength equals the given strength | if
	 *       (newStrength >= getMinStrength()) && (newStrength <=
	 *       getMaxStrength()) | then new.getStrength() == newStrength
	 * @post If the given strength is larger than the maximum legal strength,
	 *       the Unit's strength equals the maximum legal strength | if
	 *       (newStrength > getMaxStrength()) | then new.getStrength() ==
	 *       getMaxStrength()
	 * @post If the given strength is smaller than the minimum legal strength,
	 *       the Unit's strength equals the minimum legal strength | if
	 *       (newStrength < getMinStrength()) | then new.getStrength() ==
	 *       getMinStrength()
	 */
	public void setStrength(int newStrength) {
		if (newStrength < getMinStrength()) {
			newStrength = getMinStrength();
		} else if (newStrength > getMaxStrength()) {
			newStrength = getMaxStrength();
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
	 *         getMaxWeight())
	 */
	public boolean isValidWeight(int weight) {
		return (weight >= this.getMinWeight()) && (weight <= getMaxWeight());
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
	@Basic
	public static int getMaxWeight() {
		return 200;
	}

	/**
	 * Return the largest legal value for a new Unit's weight
	 */
	@Basic
	public static int getMaxInitialWeight(){
		return 100;
	}

	/**
	 * Set the weight of the Unit to the given weight
	 * 
	 * @param newWeight
	 *            The new weight to be set for this Unit
	 * @post If the given weight lies between the minimum and maximum legal
	 *       weight, the Unit's weight equals the given weight | if (newWeight
	 *       >= this.getMinWeight()) && (newWeight <= getMaxWeight()) | then
	 *       new.getWeight() == newWeight
	 * @post If the given weight is larger than the maximum legal weight, the
	 *       Unit's weight equals the maximum legal weight | if (newWeight >
	 *       getMaxWeight()) | then new.getWeight() == getMaxWeight()
	 * @post If the given weight is smaller than the minimum legal weight, the
	 *       Unit's weight equals the minimum legal weight | if (newWeight <
	 *       this.getMinWeight()) | then new.getWeight() == this.getMinWeight()
	 */
	public void setWeight(int newWeight) {
		if (newWeight < this.getMinWeight()) {
			newWeight = this.getMinWeight();
		} else if (newWeight > getMaxWeight()) {
			newWeight = getMaxWeight();
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
	 * @return | result == (toughness >= getMinToughness()) && (toughness <=
	 *         getMaxToughness())
	 */
	public static boolean isValidToughness(int toughness) {
		return (toughness >= getMinToughness()) && (toughness <= getMaxToughness());
	}

	/**
	 * Returns the smallest legal value for a Unit's toughness
	 * 
	 * @return The smallest possible toughness for all Units in the class.
	 */
	@Basic
	public static int getMinToughness() {
		return 1;
	}

	/**
	 * Returns the largest legal value for a Unit's toughness
	 * 
	 * @return The largest possible toughness for all Units in the class.
	 */
	@Basic
	public static int getMaxToughness() {
		return 200;
	}

	/**
	 * Return the smallest legal value for a new Unit's toughness
	 */
	@Basic
	public static int getMinInitialToughness(){
		return 25;
	}

	/**
	 * Return the largest legal value for a new Unit's toughness
	 */
	@Basic
	public static int getMaxInitialToughness(){
		return 100;
	}

	/**
	 * Set the toughness of the Unit to the given toughness
	 * 
	 * @param newToughness
	 *            The new toughness to be set for this Unit
	 * @post If the given toughness lies between the minimum and maximum legal
	 *       toughness, the Unit's toughness equals the given toughness | if
	 *       (newToughness >= getMinToughness()) && (newToughness <=
	 *       getMaxToughness()) | then new.getToughness() == newToughness
	 * @post If the given toughness is larger than the maximum legal toughness,
	 *       the Unit's toughness equals the maximum legal toughness | if
	 *       (newToughness > getMaxToughness()) | then new.getToughness() ==
	 *       getMaxToughness()
	 * @post If the given toughness is smaller than the minimum legal toughness,
	 *       the Unit's toughness equals the minimum legal toughness | if
	 *       (newToughness < getMinToughness()) | then new.getToughness() ==
	 *       getMinToughness()
	 */
	public void setToughness(int newToughness) {
		if (newToughness < getMinToughness()) {
			newToughness = getMinToughness();
		} else if (newToughness > getMaxToughness()) {
			newToughness = getMaxToughness();
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

	public void preparing_attack(double time,Unit other){
		if (time>=1){
			attack(other);
		}
	}

	public void attack(Unit other) {
		if (Math.random() <= 0.20 * other.getAgility() / this.getAgility()) {
			other.dodge();
		} else if (Math.random() <= 0.25 * (other.getStrength() + other.getAgility())
				/ (this.getStrength() + this.getAgility())) {
			// unit parryt
		} else {
			// unit krijgt damage
		}
	}

	public void dodge() {

	}

	/*
	 * berekent de snelheid van de unit
	 * 
	 */
	public double calculateBaseSpeed() {
		double baseSpeed = 1.5 * (strength + agility) / (200 * weight / 100);
		return baseSpeed;
	}
	
	public void moveToAdjacent(double targetx, double targety, double targetz)
			throws IllegalArgumentException, IllegalStateException{
		if (! isValidPosition(new Vector(targetx, targety, targetz)))
			throw new IllegalArgumentException();
		if (this.getStatus() == Status.MOVING)
			throw new IllegalStateException();
		double speed = this.calculateBaseSpeed();
		if (Util.fuzzyEquals(this.getPosition().getZ() - targetz, -1))
			speed = 0.5*speed;
		else if (Util.fuzzyEquals(this.getPosition().getZ() - targetz, 1))
			speed = 1.2*speed;
		double d = Math.sqrt(Math.pow((this.getPosition().getX() - targetx), 2) + Math.pow((this.getPosition().getY() - targety), 2)
		+ Math.pow((this.getPosition().getZ() - targetz), 2));
		double speed_x = speed*(targetx - this.getPosition().getX())/d;
		double speed_y = speed*(targety - this.getPosition().getY())/d;
		double speed_z = speed*(targetz - this.getPosition().getZ())/d;
		this.setStatus(Status.MOVING);
		this.setSpeed(new Vector(speed_x, speed_y, speed_z));
		this.setOrientation(Math.atan2(speed_y, speed_x));
	}

	/*
	 * berekent de tijd nodig voor een "werkje"
	 */
	public double calculatingWorkTime() {
		return 500 / (double) this.getStrength();
	}
	/*
	 * @ param targetx, targety,targetz
	 * 			the coordinates of the place you want to go to
	 * @ post
	 * 		the position is a valid coordinate position
	 */
	private void pathfinding(double targetx,double targety,double targetz){
		isValidPosition(new Vector(targetx, targety, targetz));
		if (targetx!=this.getPosition().getX()){
			if (this.getPosition().getX()<targetx){
				double intermediatex=(this.getPosition().getX()+1);}
			else{double intermediatex=(this.getPosition().getX()-1);

			}
		}

		if (targety!=this.getPosition().getY()){
			if (this.getPosition().getY()<targety){
				double intermediatey=(this.getPosition().getY()+1);}
			else{double intermediatey=(this.getPosition().getY()-1);
			}
		}

		if (targetz!=this.getPosition().getZ()){
			if (this.getPosition().getZ()<targetz){
				double intermediatez=(this.getPosition().getZ()+1);}
			else{double intermediatez=(this.getPosition().getZ()-1);
			}
		}

	}
	public double calculateMinRestTime(){
		return 40/(double)toughness;
	}
	public void resting(double time)throws IllegalArgumentException{
		if (time<calculateMinRestTime()){
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
		if (this.isSprinting()){
			displacement = this.getSpeed().scalarMultiply(2*time);
			this.setStamina(this.getStamina()-(int) (10*time));
		} else 
			displacement = this.getSpeed().scalarMultiply(time);
		this.setPosition(this.getPosition().add(displacement));
		
	}
	
}
