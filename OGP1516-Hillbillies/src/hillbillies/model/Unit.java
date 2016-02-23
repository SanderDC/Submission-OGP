package hillbillies.model;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * Documentatie
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 */
public class Unit {
	
	/**
	 * Initialize a new Unit with given parameters
	 * 
	 * @param x
	 * 		The x-coordinate of the new unit
	 * @param y
	 * 		The y-coordinate of the new unit
	 * @param z
	 * 		The z-coordinate of the new unit
	 * @param agility
	 * 		The agility of the new unit
	 * @param strength
	 * 		The strength of the new unit
	 * @param weight
	 * 		The weight of the new unit
	 * @param name
	 * 		The name of the new unit
	 * @param hitpoints
	 * 		The hitpoints of the new unit
	 * @param stamina
	 * 		The stamina of the new unit
	 * @param toughness
	 * 		The toughness of the new unit
	 * @param maxHitpoint
	 * 		The maximum amount of hitpoints of the new unit
	 * @param maxStamina
	 * 		The maximum amount of stamina of the new unit
	 * @param orientation
	 * 		The orientation of the new unit
	 * 
	 * @effect	The x-coordinate of the new unit is set to x
	 * 			| this.setX(x)
	 * @effect	The y-coordinate of the new unit is set to y
	 * 			| this.setY(y)
	 * @effect	The z-coordinate of the new unit is set to z
	 * 			| this.setZ(z)
	 * @effect	The agility of the new unit is initialised to the given agility
	 * 			| this.initialiseAgility(agility,25,100)
	 * @effect	The strength of the new unit is initialised to the given strength
	 * 			| this.initialiseStrength(strength,25,100)
	 * @effect	The toughness of the new unit is initialised to the given toughness
	 * 			| this.initialiseToughness(toughness,25,100)
	 * @effect	The name of the new unit is set to the given name
	 * 			| this.setName(name)
	 * @effect	The maximum amount of hitpoints for this Unit is set.
	 * 			| this.setMaxHitPoints()
	 * @effect	The maximum amount of stamina for this Unit is set.
	 * 			| this.setMaxStamina()
	 * @effect	The current amount of hitpoints is set to the given amount of hitpoints
	 * 			| this.setHitpoints(hitpoints)
	 * @effect	The current amount of stamina is set to the given amount of stamina
	 * 			| this.setStamina(stamina)
	 *		
	 */
	public Unit(double x,double y, double z, int agility,
		 int strength, int weight,String name,int hitpoints, int stamina, 
		 int toughness)
		 throws IllegalArgumentException {
	
	this.setX(x);
	this.setY(y);
	this.setZ(z);
		
	this.initialiseAgility(agility, 25, 100);
	this.initialiseStrength(strength, 25, 100);
	this.initialiseToughness(toughness, 25, 100);
	double minWeight = ((double) this.getStrength() + (double) this.getAgility())/2.0;
	if (minWeight >= 25){
		this.initialiseWeight(weight,(int) Math.ceil(minWeight), 100);
	} else {
		this.initialiseWeight(weight, 25, 100);
	}
	//TODO: alternatief wanneer agility/... Niet valid is
	
	this.setName(name);
	this.setMaxHitpoints();
	this.setMaxStamina();
	this.setStamina(stamina);
	this.setHitpoints(hitpoints);
	
	this.setWorking(false);
	this.setResting(false);
	this.setMoving(false);
	this.orientation=(float) ((Math.PI)/2);
	}
	
	/**
	 * Returns the current x-coordinate of the Unit
	 */
	@Basic
	public double getX() {
		return Math.floor( this.x);
	}
	
	/**
	 * Set the x-coordinate of the Unit to the given x-coordinate
	 * @param newx
	 * 		The new x-coordinate for the Unit
	 * @throws IllegalArgumentException
	 * 			The given x-coordinate is outside of the gameworld
	 * 			| newx < getMinCoordinate() || newx >= getMaxCoordinate()
	 */
	public void setX(double newx) throws IllegalArgumentException{
		if ((newx<getMinCoordinate())||(newx>=getMaxCoordinate())) 
			throw new IllegalArgumentException();
		else{
			this.x=newx;
		}
	}
	
	private double x;
	
	/**
	 * Returns the current y-position of the Unit
	 */
	@Basic
	public double getY() {
		return Math.floor( this.y);
	}
	
	/**
	 * Set the y-coordinate of the Unit to the given y-coordinate
	 * @param newy
	 * 		The new y-coordinate for the Unit
	 * @throws IllegalArgumentException
	 * 		The given y-coordinate is outside of the gameworld
	 * 		| newy < getMinCoordinate() || newy >= getMaxCoordinate()
	 */
	public void setY(double newy) throws IllegalArgumentException {
		if ((newy<getMinCoordinate())||(newy>=getMaxCoordinate())) {
			throw new IllegalArgumentException();
		}
		else{
			this.y=newy;
		}
	}
	
	private double y;
	
	/**
	 * Returns the current z-coordinate of the Unit
	 */
	@Basic
	public double getZ() {
		return  Math.floor(this.z);}
	
	/**
	 * Set the z-coordinate of the Unit to the given z-coordinate
	 * @param newz
	 * 		The new z-coordinate for the Unit
	 * @throws IllegalArgumentException
	 * 			The given z-coordinate is outside of the gameworld
	 * 			| newz < getMinCoordinate() || newz >= getMaxCoordinate()
	 */
	public void setZ(double newz) throws IllegalArgumentException {
		if ((newz<getMinCoordinate())||(newz>getMaxCoordinate())) {
			throw new IllegalArgumentException();
		}
		else{
			this.z=newz;
		}
	}
	
	private double z;
	
	public static int getMaxCoordinate(){
		return 50;
	}
	
	public static int getMinCoordinate(){
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	@Basic
	public int getAgility(){
		return this.agility;
	}
	
	@Basic
	public static int getMinAgility(){
		return 1;
	}
	
	@Basic
	public static int getMaxAgility(){
		return 200;
	}
	
	/*
	 * agility setten, en checken voor geldige waarden
	 */
	public void setAgility( int newAgility) {
		if ((newAgility<1)||(newAgility>200) ) {
			this.agility=1;
		}
		else{
			this.agility=newAgility;
		}	
	}
	
	/**
	 * Initialises the agility of this Unit to the given agility.
	 * 
	 * @param agility
	 * 		The new agility for this Unit.
	 * @param minAgility
	 * 		The minimum initial agility for this unit.
	 * @param maxAgility
	 * 		The maximum initial agility for this unit.
	 * 
	 * @post	If the given agility lies between the minimum and maximum initial agility,
	 * 			the agility of this Unit equals the given agility.
	 * 			| if (agility >= minAgility) && (agility <= maxAgility)
	 * 			| then new.getAgility() == agility
	 * @post	If the given agility is larger than the maximum initial agility,
	 * 			the agility of this Unit equals the maximum initial agility.
	 * 			| if (agility > maxAgility)
	 * 			| then new.getAgility() == maxAgility
	 * @post	If the given agility is smaller than the minimum initial agility,
	 * 			the agility of this Unit equals the minimum initial agility.
	 * 			| if (agility < minAgility)
	 * 			| then new.getAgility() == minAgility
	 */
	public void initialiseAgility(int agility, int minAgility, int maxAgility){
		if (agility < minAgility)
			agility = minAgility;
		if (agility > maxAgility)
			agility = maxAgility;
		this.agility = agility;
	}
	
	private int agility;
	
	@Basic
	public int getStrength() {
		return this.strength;
	}
	
	@Basic
	public static int getMinStrength(){
		return 1;
	}
	
	@Basic
	public static int getMaxStrength(){
		return 200;
	}
	
	/**
	 * Strength setten, en checken voor geldige waarden
	 */
	public void setStrength( int newStrength) {
		if ((newStrength<1)||(newStrength>200) ) {
			
		}
		else{
			this.strength=newStrength;
		}
		
	}
	
	/**
	 * Initialises the strength for this Unit to the given strength.
	 * 
	 * @param strength
	 * 		The new strength for this Unit.
	 * @param minStrength
	 * 		The minimum initial strength for this unit.
	 * @param maxStrength
	 * 		The maximum initial strength for this unit.
	 * 
	 * @post	If the given strength lies between the minimum and maximum initial strength,
	 * 			the strength of this Unit equals the given strength.
	 * 			| if (strength >= minStrength) && (strength <= maxStrength)
	 * 			| then new.getStrength() == strength
	 * @post	If the given strength is larger than the maximum initial strength,
	 * 			the strength of this Unit equals the maximum initial strength.
	 * 			| if (strength > maxStrength)
	 * 			| then new.getStrength() == maxStrength
	 * @post	If the given strength is smaller than the minimum initial strength,
	 * 			the strength of this Unit equals the minimum initial strength.
	 * 			| if (strength < minStrength)
	 * 			| then new.getStrength() == minStrength
	 */
	public void initialiseStrength(int strength, int minStrength, int maxStrength){
		if (strength < minStrength)
			strength = minStrength;
		if (strength > maxStrength)
			strength = maxStrength;
		this.strength = strength;
	}
	
	private int strength;
	
	@Basic
	public int getWeight() {
		return this.weight;
	}
	
	@Basic
	public static int getMinWeight(){
		return 1;
	}
	
	@Basic
	public static int getMaxWeight(){
		return 200;
	}
	
	/*
	 * weight setten, en checken voor geldige waarden, zeker oppassen want deling floort altijd
	 */
	public void setWeight( int newWeight) {
		if ((newWeight<1)||(newWeight>200) ) {
			
			
		}
		else{
			if ((newWeight>=(this.strength+this.agility)/2) && (this.strength+this.agility%2==0)) {
				this.weight=newWeight;
			}
			else {
				if ((newWeight>(this.strength+this.agility)/2) && (this.strength+this.agility%2==1)) {
					this.weight=newWeight;
				}
			}
		}
	}
	
	/**
	 * Initialises the weight of this Unit to the given weight.
	 * 
	 * @param weight
	 * 		The new weight for this Unit.
	 * @param minWeight
	 * 		The minimum initial weight for this unit.
	 * @param maxWeight
	 * 		The maximum initial weight for this unit.
	 * 
	 * @post	If the given weight lies between the minimum and maximum initial weight,
	 * 			the weight of this Unit equals the given weight.
	 * 			| if (weight >= minWeight) && (weight <= maxWeight)
	 * 			| then new.getWeight() == weight
	 * @post	If the given weight is larger than the maximum initial weight,
	 * 			the weight of this Unit equals the maximum initial weight.
	 * 			| if (weight > maxWeight)
	 * 			| then new.getWeight() == maxWeight
	 * @post	If the given weight is smaller than the minimum initial weight,
	 * 			the weight of this Unit equals the minimum initial weight.
	 * 			| if (weight < minWeight)
	 * 			| then new.getWeight() == minWeight
	 */
	public void initialiseWeight(int weight, int minWeight, int maxWeight){
		if (weight < minWeight)
			weight = minWeight;
		if (weight > maxWeight)
			weight = maxWeight;
		this.weight = weight;
	}
	
	private int weight;
	

		


	@Basic
	public String getName() {
		return this.name;
	}
	
	/*
	 * zet de naam en checkt ofdat de lengt groter is dan 2 en de eerste letter een hoofdletter is, als hieraan voldaan is,
	 *zal hij kijken ofdat de rest van de naam geldige waardes bevat
	 */
	public void setName(String name) throws IllegalArgumentException {
		if ((name.length()>=2)||(Character.isUpperCase(name.charAt(0)))){
			for (int i=name.length();i>=1;i--){
				if((Character.isUpperCase(name.charAt(i)))||
						(name.charAt(i)==' ')||
						(name.charAt(i)=='"' )||
						(Character.isLowerCase(name.charAt(i)))||
						(name.charAt(i)=='\'')){

				}
				else{
					throw new IllegalArgumentException();
				}
			}
			this.name = name;
		}

		else{
			throw new IllegalArgumentException();
		}
	}
	
	private String name;
	
	@Basic
	public int getmaxStamina(){
		return this.maxStamina;
	}
	
	/*
	 * maxstamina bepalen en opnieuw oppassen vanwege floor
	 */
	public void setMaxStamina(){
		if (200*toughness*weight%10000==0) {
			
		
		this.maxHitpoints=200*toughness/100*weight/100;
		}
		else
			this.maxHitpoints=200*toughness/100*weight/100+1;
	}
	
	private int maxStamina;
	
	@Basic
	public int getmaxHitpoints() {
		return this. maxHitpoints;
	}
	
	/*
	 * maxhitpoints bepalen en opnieuw oppassen vanwege floor
	 */
	public void setMaxHitpoints() {
		if (200*toughness*weight%10000==0) {
			
			
			this.maxHitpoints=200*toughness/100*weight/100;
			}
			else
				this.maxHitpoints=200*toughness/100*weight/100+1;
	}
	
	private int maxHitpoints;
	
	@Basic
	public int getToughness() {
		return this.toughness;
	}
	
	@Basic
	public static int getMinToughness(){
		return 1;
	}
	
	@Basic
	public static int getMaxToughness(){
		return 200;
	}
	
	/*
	 * toughness setten, en checken voor geldige waarden
	 */
	public void setToughness(int Toughness) {
		if ((Toughness<1)||(Toughness>200) ) {
			
			
		}
		else{
			this.toughness=Toughness;
		}
	}
	
	/**
	 * Initialises the toughness of this Unit to the given toughness.
	 * 
	 * @param toughness
	 * 		The new toughness for this Unit.
	 * @param minToughness
	 * 		The minimum initial toughness for this unit.
	 * @param maxToughness
	 * 		The maximum initial toughness for this unit.
	 * 
	 * @post	If the given toughness lies between the minimum and maximum initial toughness,
	 * 			the toughness of this Unit equals the given toughness.
	 * 			| if (toughness >= minToughness) && (toughness <= maxToughness)
	 * 			| then new.getToughness() == toughness
	 * @post	If the given toughness is larger than the maximum initial toughness,
	 * 			the toughness of this Unit equals the maximum initial toughness.
	 * 			| if (toughness > maxToughness)
	 * 			| then new.getToughness() == maxToughness
	 * @post	If the given toughness is smaller than the minimum initial toughness,
	 * 			the toughness of this Unit equals the minimum initial toughness.
	 * 			| if (toughness < minToughness)
	 * 			| then new.getToughness() == minToughness
	 */
	public void initialiseToughness(int toughness, int minToughness, int maxToughness){
		if (toughness < minToughness)
			toughness = minToughness;
		if (toughness > maxToughness)
			toughness = maxToughness;
		this.toughness = toughness;
	}
	
	private int toughness;
	
	@Basic
	public int getHitpoints(){
		return this.hitpoints;
	}
	
	public void setHitpoints(int newHitpoints) {
		this.hitpoints=newHitpoints;
	}
	
	private int hitpoints;
	
	@Basic
	public int getStamina(){
		return this.stamina;
	}
	
	public void setStamina(int newstamina) {
		this.stamina=newstamina;
	}
	
	private int stamina;
	
	/*
	 * orientatie nog niet echt uitgewerkt, aangezien ik niet goed uit het document kon opmaken wat de geldige waardes zijn
	 */
	@Basic
	public float getOrientation(){
		return this.orientation;
	}
	public void setOrientation(){
		
		
	}
	
	private float orientation;
	
	public boolean getWorking(){
		return this.working;
	}
	
	public void setWorking(boolean working){
		this.working = working;
	}
	
	private boolean working;
	
	public boolean getResting(){
		return this.resting;
	}
	
	public void setResting(boolean resting){
		this.resting = resting;
	}
	
	private boolean resting;
	
	public boolean getMoving(){
		return this.moving;
	}
	
	public void setMoving(boolean moving){
		this.moving = moving;
	}
	
	private boolean moving;
	
	public void attack(Unit other) {
		if (Math.random() <= 0.20*other.getAgility()/this.getAgility()){
			other.dodge();
		}
		else if (Math.random() <= 0.25*(other.getStrength()+other.getAgility())/(this.getStrength()+this.getAgility())){
			//unit parryt
		}
		else {
			//unit krijgt damage
		}
	}
	
	public void dodge(){
		
	}
	/*
	 * berekent de snelheid van de unit
	 */
	public double getSpeed(boolean sprint,double targetz){
		double baseSpeed=1.5*(strength+agility)/(200*weight/100);
		if (Math.floor(targetz)-getZ()==1) { //TODO: targetz moet volgens mij niet gefloord worden (in de opgave staat z' en niet zc')
			baseSpeed=0.5 *baseSpeed;
			
		}
		else if (getZ()-Math.floor(targetz)==1) {
			baseSpeed=1.2*baseSpeed;
		}
		return baseSpeed;
	}
	/*
	 * berekent of een positie bereikbaar is
	 */
	public boolean isValidposition(double x, double y, double z, double maxcoordinate)
		{if ((x>maxcoordinate)||(x<0)||(y>maxcoordinate)||(y<0)||(z>maxcoordinate)||(z<0)){
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * verplaatst de Unit
	 */
	public void moveToAdjacent(double targetx, double targety,double targetz,boolean sprinting, float time) {
		try{
			if (!isValidposition(targetx, targety, targetz, getMaxCoordinate())) {
				throw new IllegalArgumentException();
			}
		double d=Math.sqrt(Math.pow((this.x-targetx), 2)+Math.pow((this.y-targety), 2)+Math.pow((this.z-targetz), 2));
		double speed=getSpeed(sprinting, targetz);
		setX(this.x+time*speed*(this.x-targetx)/d);
		setY(this.y+time*speed*(this.y-targety)/d);
		setZ(this.z+time*speed*(this.z-targetz)/d);
		}
		catch(IllegalArgumentException e){
			
		}
		}
	/*
	 * berekent de tijd nodig voor een "werkje"
	 */
	public double calculatingWorkTime(){
		return 500.0/(double)this.getStrength(); 
	}
}

