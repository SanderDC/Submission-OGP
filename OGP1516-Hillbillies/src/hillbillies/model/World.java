package hillbillies.model;

import java.awt.Checkbox;
import java.util.*;

import org.omg.CORBA.PRIVATE_MEMBER;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Vector;

/**
 * A class defining a game World for Units to exist in
 * @author Sander Declercq
 * @author Bram Belpaire
 * @invar   Each World must have proper Units.
 *        | hasProperUnits()
 * @invar   Each World must have proper Factions.
 *        | hasProperFactions()
 * @invar   Each World must have proper GameObjects.
 *        | hasProperGameObjects()
 *
 */
public class World {
	
	public static final double CUBELENGTH = 1;

	/**
	 * Initialize a new World with given coordinates and without any Units, Factions or GameObjects.
	 * @param Coordinates
	 * 			The given coordinates for this new World
	 * @post   This new World has no Units yet.
	 * @post   This new World has no Factions yet.
	 * @post   This new World has no GameObjects yet.
	 */
	public World(int[][][] Coordinates){
		
		
		this.Coordinates=Coordinates;
		for (int x=0;x<nbCoordinateX();x++){
			 for (int y=0;y<nbCoordinateY();y++){
				 for (int z=0;z<nbCoordinateZ();z++){
					 if(!isValidMaterial(Coordinates[x][y][z])){
						 Coordinates[x][y][z]=0;
					 }
					 if (unitCanStandAt(new Vector(x,y,z)))
						 this.addStandablePosition(new Vector(x,y,z));
				 }}}
		for (int x=0;x<nbCoordinateX();x++){
			 for (int y=0;y<nbCoordinateY();y++){
				 for (int z=0;z<nbCoordinateZ();z++){
					 if (isSolidGround(x, y, z)) {
						 if(!isConnectedToBorder(x, y, z)){
							 partOfCaveIn.add(new Vector(x, y, z));
						}
						 }
						 
					 
					 
					 
				 
				 }
			 }
		for (Vector vector : partOfCaveIn) {
			caveIn(vector.getCubeX(), vector.getCubeY(), vector.getCubeZ(), getCubeType(vector.getCubeX(), vector.getCubeY(), vector.getCubeZ()));
		}
		
	}}
	private Set<Vector> partOfCaveIn =new HashSet<>();
	private int[][][]getCoordinates () {
		return this.Coordinates;
	}
	
	public int nbCoordinateX() {
		return getCoordinates().length;
	}
	
	public int nbCoordinateY(){
		return getCoordinates()[0].length;
	}
	
	public int nbCoordinateZ() {
		return getCoordinates()[0][0].length;
	}
	
	public int [] maxCoordinates(){
		int [] maxcoordinates= new int [3];
		maxcoordinates[0]=nbCoordinateX()-1;
		maxcoordinates[1]=nbCoordinateY()-1;
		maxcoordinates[2]=nbCoordinateZ()-1;
		return maxcoordinates;
	}
	
	/**
	 * Check whether a given position is inside this gameworld.
	 * @param position
	 * 			The position to be checked.
	 * @return true if all coordinates of the given Vector lie between
	 * 			this gameworld's minimum and maximum coordinate in that direction.
	 */
	boolean isInsideWorld(Vector position){
		double[] positionArray = position.toArray();
		for (int i = 0; i < positionArray.length; i++){
			if ((positionArray[i] < 0) || (positionArray[i] >= this.maxCoordinates()[i]))
				return false;
		}
		return true;
	}
	
	private int [][][] Coordinates;
	
	public void advanceTime(double time)throws IllegalArgumentException {
		if (time<0||time>0.2)
			throw new IllegalArgumentException();
		for (Unit unit:this.getUnits()){
			unit.advanceTime(time);
		}
		for (GameObject gObject:this.getGameObjects()){
			gObject.advanceTime(time);
		}
	}
	
	public  int getCubeType(int x,int y, int z) {
		return getCoordinates()[x][y][z];

	}
	public void setCubeType(int x,int y, int z, int value) {
		this.getCoordinates()[x][y][z]=value;
	}
	
	public boolean isValidMaterial(int i) {
			
		
		if (i>=0&&i<=3) {
			return true;
		}
		else {
			return false;
		}
		
	}

	/**
	 * Check whether this World has the given Unit as one of its
	 * Units.
	 * 
	 * @param  Unit
	 *         The Unit to check.
	 */
	@Basic
	@Raw
	public boolean hasAsUnit(@Raw Unit Unit) {
		return Units.contains(Unit);
	}

	/**
	 * Check whether this World can have the given Unit
	 * as one of its Units.
	 * 
	 * @param  Unit
	 *         The Unit to check.
	 * @return True if and only if the given Unit is effective
	 *         and that Unit is a valid Unit for a World.
	 *       | result ==
	 *       |   (Unit != null) &&
	 *       |   Unit.isValidWorld(this)
	 */
	@Raw
	public boolean canHaveAsUnit(Unit Unit) {
		return (Unit != null) && (Unit.canHaveAsWorld(this));
	}

	/**
	 * Check whether this World has proper Units attached to it.
	 * 
	 * @return True if and only if this World can have each of the
	 *         Units attached to it as one of its Units,
	 *         and if each of these Units references this World as
	 *         the World to which they are attached.
	 *       | for each Unit in Unit:
	 *       |   if (hasAsUnit(Unit))
	 *       |     then canHaveAsUnit(Unit) &&
	 *       |          (Unit.getWorld() == this)
	 */
	public boolean hasProperUnits() {
		for (Unit Unit : Units) {
			if (!canHaveAsUnit(Unit))
				return false;
			if (Unit.getWorld() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * Return all Units currently living in this World.
	 */
	public Set<Unit> getUnits(){
		return this.Units;
	}

	/**
	 * Return the number of Units associated with this World.
	 *
	 * @return  The total number of Units collected in this World.
	 *        | result ==
	 *        |   card({Unit:Unit | hasAsUnit({Unit)})
	 */
	public int getNbUnits() {
		return Units.size();
	}

	/**
	 * Add the given Unit to the set of Units of this World.
	 * 
	 * @param  Unit
	 *         The Unit to be added.
	 * @pre    The given Unit is effective and already references
	 *         this World.
	 *       | (Unit != null) && (Unit.getWorld() == this)
	 * @post   This World has the given Unit as one of its Units.
	 *       | new.hasAsUnit(Unit)
	 */
	public void addUnit(@Raw Unit Unit) {
		assert (Unit != null) && (Unit.getWorld() == this);
		Units.add(Unit);
	}

	/**
	 * Remove the given Unit from the set of Units of this World.
	 * 
	 * @param  Unit
	 *         The Unit to be removed.
	 * @pre    This World has the given Unit as one of
	 *         its Units, and the given Unit does not
	 *         reference any World.
	 *       | this.hasAsUnit(Unit) &&
	 *       | (Unit.getWorld() == null)
	 * @post   This World no longer has the given Unit as
	 *         one of its Units.
	 *       | ! new.hasAsUnit(Unit)
	 */
	@Raw
	public void removeUnit(Unit Unit) {
		assert this.hasAsUnit(Unit) && (Unit.getWorld() == null);
		Units.remove(Unit);
	}

	/**
	 * Variable referencing a set collecting all the Units
	 * of this World.
	 * 
	 * @invar  The referenced set is effective.
	 *       | Units != null
	 * @invar  Each Unit registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each Unit in Units:
	 *       |   ( (Unit != null) &&
	 *       |     (! Unit.isTerminated()) )
	 */
	private final Set<Unit> Units = new HashSet<Unit>();

	/**
	 * Check whether this World has the given Faction as one of its
	 * Factions.
	 * 
	 * @param  Faction
	 *         The Faction to check.
	 */
	@Basic
	@Raw
	public boolean hasAsFaction(@Raw Faction Faction) {
		return Factions.contains(Faction);
	}

	/**
	 * Check whether this World can have the given Faction
	 * as one of its Factions.
	 * 
	 * @param  Faction
	 *         The Faction to check.
	 * @return True if and only if the given Faction is effective
	 *         and that Faction is a valid Faction for a World.
	 *       | result ==
	 *       |   (Faction != null) &&
	 *       |   Faction.isValidWorld(this)
	 */
	@Raw
	public boolean canHaveAsFaction(Faction Faction) {
		return (Faction != null) && (Faction.canHaveAsWorld(this));
	}

	/**
	 * Check whether this World has proper Factions attached to it.
	 * 
	 * @return True if and only if this World can have each of the
	 *         Factions attached to it as one of its Factions,
	 *         and if each of these Factions references this World as
	 *         the World to which they are attached.
	 *       | for each Faction in Faction:
	 *       |   if (hasAsFaction(Faction))
	 *       |     then canHaveAsFaction(Faction) &&
	 *       |          (Faction.getWorld() == this)
	 */
	public boolean hasProperFactions() {
		for (Faction Faction : Factions) {
			if (!canHaveAsFaction(Faction))
				return false;
			if (Faction.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of Factions associated with this World.
	 *
	 * @return  The total number of Factions collected in this World.
	 *        | result ==
	 *        |   card({Faction:Faction | hasAsFaction({Faction)})
	 */
	public int getNbFactions() {
		return Factions.size();
	}

	/**
	 * Return a Set containing all currently active Factions in this game world.
	 */
	public Set<Faction> getActiveFactions(){
		Set<Faction> result = new HashSet<>();
		for (Faction faction:this.Factions){
			if (faction.isActive())
				result.add(faction);
		}
		return result;
	}

	/**
	 * Add the given Faction to the set of Factions of this World.
	 * 
	 * @param  Faction
	 *         The Faction to be added.
	 * @pre    The given Faction is effective and already references
	 *         this World.
	 *       | (Faction != null) && (Faction.getWorld() == this)
	 * @post   This World has the given Faction as one of its Factions.
	 *       | new.hasAsFaction(Faction)
	 */
	public void addFaction(@Raw Faction Faction) {
		assert (Faction != null) && (Faction.getWorld() == this);
		Factions.add(Faction);
	}

	/**
	 * Remove the given Faction from the set of Factions of this World.
	 * 
	 * @param  Faction
	 *         The Faction to be removed.
	 * @pre    This World has the given Faction as one of
	 *         its Factions, and the given Faction does not
	 *         reference any World.
	 *       | this.hasAsFaction(Faction) &&
	 *       | (Faction.getWorld() == null)
	 * @post   This World no longer has the given Faction as
	 *         one of its Factions.
	 *       | ! new.hasAsFaction(Faction)
	 */
	@Raw
	public void removeFaction(Faction Faction) {
		assert this.hasAsFaction(Faction) && (Faction.getWorld() == null);
		Factions.remove(Faction);
	}

	/**
	 * Variable referencing a set collecting all the Factions
	 * of this World.
	 * 
	 * @invar  The referenced set is effective.
	 *       | Factions != null
	 * @invar  Each Faction registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each Faction in Factions:
	 *       |   ( (Faction != null) &&
	 *       |     (! Faction.isTerminated()) )
	 */
	private final Set<Faction> Factions = new HashSet<Faction>();

	/**
	 * Check whether this World has the given GameObject as one of its
	 * GameObjects.
	 * 
	 * @param  gameObject
	 *         The GameObject to check.
	 */
	@Basic
	@Raw
	public boolean hasAsGameObject(@Raw GameObject gameObject) {
		return gameObjects.contains(gameObject);
	}

	/**
	 * Check whether this World can have the given GameObject
	 * as one of its GameObjects.
	 * 
	 * @param  gameObject
	 *         The GameObject to check.
	 * @return True if and only if the given GameObject is effective
	 *         and that GameObject is a valid GameObject for a World.
	 *       | result ==
	 *       |   (gameObject != null) &&
	 *       |   GameObject.isValidWorld(this)
	 */
	@Raw
	public boolean canHaveAsGameObject(GameObject gameObject) {
		return (gameObject != null) && (gameObject.canHaveAsWorld(this));
	}

	/**
	 * Check whether this World has proper GameObjects attached to it.
	 * 
	 * @return True if and only if this World can have each of the
	 *         GameObjects attached to it as one of its GameObjects,
	 *         and if each of these GameObjects references this World as
	 *         the World to which they are attached.
	 *       | for each gameObject in GameObject:
	 *       |   if (hasAsGameObject(gameObject))
	 *       |     then canHaveAsGameObject(gameObject) &&
	 *       |          (gameObject.getWorld() == this)
	 */
	public boolean hasProperGameObjects() {
		for (GameObject gameObject : gameObjects) {
			if (!canHaveAsGameObject(gameObject))
				return false;
			if (gameObject.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of GameObjects associated with this World.
	 *
	 * @return  The total number of GameObjects collected in this World.
	 *        | result ==
	 *        |   card({gameObject:GameObject | hasAsGameObject({gameObject)})
	 */
	public int getNbGameObjects() {
		return gameObjects.size();
	}

	/**
	 * Add the given GameObject to the set of GameObjects of this World.
	 * 
	 * @param  gameObject
	 *         The GameObject to be added.
	 * @pre    The given GameObject is effective and already references
	 *         this World.
	 *       | (gameObject != null) && (gameObject.getWorld() == this)
	 * @post   This World has the given GameObject as one of its GameObjects.
	 *       | new.hasAsGameObject(gameObject)
	 */
	public void addGameObject(@Raw GameObject gameObject) {
		assert (gameObject != null) && (gameObject.getWorld() == this);
		gameObjects.add(gameObject);
	}

	/**
	 * Remove the given GameObject from the set of GameObjects of this World.
	 * 
	 * @param  gameObject
	 *         The GameObject to be removed.
	 * @pre    This World has the given GameObject as one of
	 *         its GameObjects, and the given GameObject does not
	 *         reference any World.
	 *       | this.hasAsGameObject(gameObject) &&
	 *       | (gameObject.getWorld() == null)
	 * @post   This World no longer has the given GameObject as
	 *         one of its GameObjects.
	 *       | ! new.hasAsGameObject(gameObject)
	 */
	@Raw
	public void removeGameObject(GameObject gameObject) {
		assert this.hasAsGameObject(gameObject) && (gameObject.getWorld() == null);
		gameObjects.remove(gameObject);
	}

	/**
	 * Variable referencing a set collecting all the GameObjects
	 * of this World.
	 * 
	 * @invar  The referenced set is effective.
	 *       | gameObjects != null
	 * @invar  Each GameObject registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each gameObject in gameObjects:
	 *       |   ( (gameObject != null) &&
	 *       |     (! gameObject.isTerminated()) )
	 */
	
	private final Set<GameObject> gameObjects = new HashSet<GameObject>();
	
	public Set<Log> GetAllLogs() {
		Set<Log> Logs= new HashSet<>();
		for (GameObject log : gameObjects) {
			if (log instanceof Log) {
				Logs.add((Log)log);
			}
		}
		return Logs;
	}
	public Set<Boulder> GetAllBoulders() {
		Set<Boulder> Boulders= new HashSet<>();
		for (GameObject bObject : gameObjects) {
			if (bObject instanceof Boulder) {
				Boulders.add((Boulder)bObject);
			}
		}
		return Boulders;
	}
	
	public Set<GameObject> getGameObjects(){
		return this.gameObjects;
	}
	
	
	public boolean IsSolidMaterial(int i){
		if(i==1||i==2)
			return true;
		else {
			return false;
		}
	}
	public boolean isSolidGround(int x, int y, int z) {
		return IsSolidMaterial(getCubeType(x,y,z));
	}
	
	/**
	 * Return a boolean reflecting whether the cube at the given coordinates is passable.
	 * @param x
	 * 			The x-coordinate of the cube to be checked
	 * @param y
	 * 			The y-coordinate of the cube to be checked
	 * @param z
	 * 			The z-coordinate of the cube to be checked
	 * @return true if the terrain type at the given position is air or workshop.
	 * 			| result == (this.getCubeType(x, y, z) == 0) || (this.getCubeType(x, y, z) == 3)
	 */
	boolean isPassable(int x, int y, int z){
		return ((this.getCubeType(x, y, z) == 0) || (this.getCubeType(x, y, z) == 3));
	}
	
	public void caveIn(int x, int y, int z, int value) {
		setCubeType(x, y, z, 0);
		if (Math.random()>=0.25) {
			if (value==1)
				addGameObject(new Boulder(new Vector(x+World.CUBELENGTH/2, y+World.CUBELENGTH/2, z+World.CUBELENGTH/2)));
			else {
				addGameObject(new Log(new Vector(x+World.CUBELENGTH/2, y+World.CUBELENGTH/2, z+World.CUBELENGTH/2)));
			}
		}
	}
	
	/**
	 * Check whether a Unit can stand at the given position.
	 * @param position
	 * 			The position to be checked.
	 * @return true if and only if the given position is inside the gameworld, in passable terrain and at least
	 * 			one directly adjacent cube is solid or the position is at the minimum z - coordinate.
	 * 			result == (isInsideWorld(position))
	 * 						&& for some vector in this.getDirectlyAdjacentPositions():
	 * 							this.getWorld().isSolidGround(vector.getCubeX(),
	 * 															vector.getCubeY(),
	 * 															vector.getCubeZ())
	 * 						|| position.getCubeZ() == 0
	 * 		
	 */
	boolean unitCanStandAt(Vector position){
		if (!isInsideWorld(position))
			return false;
		if (isSolidGround(position.getCubeX(), position.getCubeY(), position.getCubeZ()))
			return false;
		if (position.getCubeZ() == 0)
			return true;
		for (Vector vector:this.getDirectlyAdjacentPositions(position)){
			if (isSolidGround(vector.getCubeX(), vector.getCubeY(), vector.getCubeZ()))
				return true;
		}
		return false;
	}
	
	/**
	 * Check whether a Unit can stand at the cube with the given cube coordinates
	 * @param x
	 * 			The x-coordinate of the cube to check
	 * @param y
	 * 			The y-coordinate of the cube to check
	 * @param z
	 * 			The z-coordinate of the cube to check
	 * @return unitCanStandAt(new Vector(x,y,z))
	 */
	boolean unitCanStandAt(int x, int y, int z){
		return unitCanStandAt(new Vector(x,y,z));
	}
	
	/**
	 * Return a set containing all cubes directly adjacent to the given position
	 * that are not outside the gameworld.
	 */
	public Set<Vector> getDirectlyAdjacentPositions(Vector position){
		Set<Vector> result = new HashSet<>();
		for (int x = -1; x <= 1; x++){
			for (int y = -1; y <= 1; y++){
				for(int z = -1; z <= 1; z++){
					if(Math.abs(x)+Math.abs(y)+Math.abs(z) == 1){
						Vector vector = position.add(new Vector(x,y,z));
						if(isInsideWorld(vector))
							result.add(vector);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Return a Set containing all positions in this game World where a Unit can stand.
	 */
	List<Vector> getStandablePositions(){
		List<Vector> result = new ArrayList<>();
		for (Vector vector:this.standablePositions)
			result.add(vector);
		return result;
	}
	
	/**
	 * Add a position to the Set of positions where a Unit can stand.
	 * @param position
	 * 			The position to be added to the Set of standable positions.
	 * @pre		It is possible for a Unit to stand at this position.
	 * 			| unitCanStandAt(position)
	 * @post	The given position has been added to the Set of standable positions.
	 * 			| this.getStandablePositions().contains(position)
	 */
	private void addStandablePosition(Vector position){
		assert (unitCanStandAt(position));
		this.standablePositions.add(position);
	}
	
	/**
	 * A Set containing all positions in this game World where a Unit can stand.
	 */
	private List<Vector> standablePositions = new ArrayList<>();//TODO: posities toevoegen/verwijderen wanneer World verandert

	
	
	
	public boolean isConnectedToBorder(int x, int y, int z){
		if (!isSolidGround(x, y, z)) {
			return false;
		}
		boolean returnvalue;
		prevPos.add(new Vector(x, y, z));	
		returnvalue=backtrack(x, y, z);
		prevPos.clear();
		return returnvalue;
	}
	
	
	
	public boolean backtrack(int x, int y, int z){			
		
		Set <Vector>  positionsToRemove = new HashSet<>() ;
		if (isBorder(x, y, z)) {
			return true;
		}
		Set <Vector> Positions=CheckadjacentValidPositions(x,y,z);
		for (Vector vector : Positions) {
			for(Vector vector2: prevPos) {
				if (vector.equals(vector2)){
					positionsToRemove.add(vector);
					System.out.println("x");
					System.out.println(vector.getCubeX());
					System.out.println("y");
					System.out.println(vector.getCubeY());
					System.out.println("z");
					System.out.println(vector.getCubeZ());
				}
			}
		}
		for (Vector vector : positionsToRemove) {
			Positions.remove(vector);
		}
		for (Vector vector: Positions) {
			prevPos.add(vector);
			if(backtrack(vector.getCubeX(), vector.getCubeY(), vector.getCubeZ())){
				return true;
				}
			prevPos.remove(vector);
				
			}
		
		return false;
		}
		
	
	public Set <Vector>  CheckadjacentValidPositions(int X, int Y, int Z) {
		Set <Vector>  validpositions = new HashSet<>() ;
		for(int x=-1; x<=1;x++){
			if (x==0) {
				
			}
			else {
				if (isSolidGround(x+X, Y, Z)) {
					validpositions.add(new Vector(x+X, Y, Z));
				}
			}
			
		}
			
		for(int y=-1;y<=1;y++){
			if (y==0) {
				
			}
			else {
				if (isSolidGround(X, Y+y, Z)) {
					validpositions.add(new Vector(X, Y+y, Z));
				}
			
			}
		}
				
		for(int z=-1; z<=1;z++){
			if (z==0) {
				
			}
			else {
				if (isSolidGround(X, Y, Z+z)) {
					validpositions.add(new Vector(X, Y, Z+z));
				}
			
			}
		}
		return validpositions;
	}
	
	private boolean isBorder(int x,int y,int z){
		if ((x==0||x==maxCoordinates()[0]||y==0||y==maxCoordinates()[1]||z==0||z==maxCoordinates()[2])&&(isSolidGround(x, y, z))) {
			return true;
		}
	else {
		return false;
	}
	}
	
	private Set<Vector> prevPos=new HashSet<>();
	
	
	
}
