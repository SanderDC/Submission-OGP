package hillbillies.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

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
 * @invar  The collapseTime of each World must be a valid collapseTime for any
 *         World.
 *       | isValidCollapseTime(getCollapseTime())
 *
 */
public class World {

	public static final double CUBELENGTH = 1;

	/**
	 * Initialize a new World with given coordinates and without any Units, Factions or GameObjects
	 * and with a collapse time of 0
	 * @param Coordinates
	 * 			The given coordinates for this new World
	 * @post   This new World has no Units yet.
	 * @post   This new World has no Factions yet.
	 * @post   This new World has no GameObjects yet.
	 * @post   The collapseTime of this new World is 0.
	 */
	public World(int[][][] Coordinates, TerrainChangeListener modelListener){
		this.setCollapseTime(0);
		this.modelListener = modelListener;
		this.Coordinates=Coordinates;
		this.connectedToBorder = new ConnectedToBorder(nbCoordinateX(), nbCoordinateY(), nbCoordinateZ());
		for (int x=0;x<nbCoordinateX();x++){
			for (int y=0;y<nbCoordinateY();y++){
				for (int z=0;z<nbCoordinateZ();z++){
					if(!isValidMaterial(Coordinates[x][y][z])){
						Coordinates[x][y][z]=0;
					}
					if (unitCanStandAt(x, y, z)){
						this.addStandablePosition(new Vector(x,y,z));
						if ((z==0)||(isSolidGround(x, y, z-1)))
							this.addSpawnablePosition(new Vector(x,y,z));
					}
					if (getCubeType(x, y, z) == 0 || getCubeType(x, y, z) == 3){
						this.connectedToBorder.changeSolidToPassable(x, y, z);
					}
				}
			}
		}
	}
	
	/**
	 * Advances the gametime for this World by the given time
	 * @param time
	 * 			The time to advance the gametime with.
	 * @effect	The gametime is advanced for every Unit that is not being terminated
	 * 			and every GameObject in this World
	 * @effect	If the sum of this World's collapsetime and the time to advance the gametime with
	 * 			is larger than or equal to 5, all cubes that are disconnected from the borders collapse
	 * @effect	All Units that need to be terminated are removed from this World
	 * @throws IllegalArgumentException
	 * 			The given time is an illegal time.
	 * 			| (time < 0) || (time > 0.2)
	 */
	public void advanceTime(double time)throws IllegalArgumentException {
		if (time<0||time>0.2)
			throw new IllegalArgumentException();
		if (this.getCollapseTime() + time < 5){
			this.setCollapseTime(this.getCollapseTime() + time);
		} else {
			this.setCollapseTime(this.getCollapseTime() + time - 5);
			for (Vector vector:toBeCollapsed){
				int oldCubeType = this.getCubeType(vector);
				this.setCubeType(vector.getCubeX(), vector.getCubeY(), vector.getCubeZ(), 0);
				if (Math.random() <= 0.25){
					if (oldCubeType == 1)
						new Boulder(vector.add(new Vector(CUBELENGTH/2,CUBELENGTH/2, CUBELENGTH/2)), this);
					else if (oldCubeType == 2)
						new Log(vector.add(new Vector(CUBELENGTH/2,CUBELENGTH/2, CUBELENGTH/2)), this);
				}
			}
			toBeCollapsed.clear();
		}
//		for (Unit unit : TerminatedUnits) {
//			unit.removeFromWorld();
//		}
//		TerminatedUnits.clear();
		
		Set<GameObject> objects = new HashSet<>();
		objects.addAll(this.getGameObjects());
		for (GameObject gObject:objects){
			gObject.advanceTime(time);
		}
	}
	
	public boolean isSolidConnectedToBorder(Vector vector) {
		return this.connectedToBorder.isSolidConnectedToBorder(vector.getCubeX(), vector.getCubeY(), vector.getCubeZ());
	}
	
	/**
	 * Variable registering the ConnectedToBorder instance for this World
	 */
	private ConnectedToBorder connectedToBorder;
	
	/**
	 * Variable registering the TerrainChangeListener for this World
	 */
	private TerrainChangeListener modelListener;
	/**
	 * Return the coordinates matrix for this World
	 */
	private int[][][]getCoordinates () {
		return this.Coordinates;
	}
	
	/**
	 * 
	 * @return return the number of cubes in the z-direction
	 * 		result==getCoordinates().length
	 */
	public int nbCoordinateX() {
		return getCoordinates().length;
	}
	
	/**
	 * 
	 * @return return the number of cubes in the y-direction
	 * 		result==getCoordinates()[0].length
	 */
	public int nbCoordinateY(){
		return getCoordinates()[0].length;
	}
	
	/**
	 * 
	 * @return return the number of zcubes
	 * 		result==getCoordinates()[0][0].length
	 */
	public int nbCoordinateZ() {
		return getCoordinates()[0][0].length;
	}
	/**
	 * Return an array containing the highest cube coordinate in the x-, y- and z-directions
	 * @return returns array of the maxCoordinateCubes
	 * 		result[0]==nbCoordinateX()-1
	 * 		result[1]==nbCoordinateY()-1
	 * 		result[2]==nbCoordinateZ()-1
	 */
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
	public boolean isInsideWorld(Vector position){
		double[] positionArray = position.toArray();
		for (int i = 0; i < positionArray.length; i++){
			if ((positionArray[i] < 0) || (positionArray[i] >= this.maxCoordinates()[i] + 1))
				return false;
		}
		return true;
	}

	/**
	 * Return the type of the cube at the given position in this World
	 * @param x
	 * 			The x-coordinate of the give cube
	 * @param y
	 * 			The y-coordinate of the give cube
	 * @param z
	 * 			The z-coordinate of the give cube
	 * @return the type of cube at the given position
	 */
	public int getCubeType(int x,int y, int z) {
		return getCoordinates()[x][y][z];

	}
	
	/**
	 * Return the type of material of the cube in which the given position lies
	 * @param position
	 * 			The position whose cube to get the type of material of.
	 * @return the type of material of the cube containing the given material
	 */
	int getCubeType(Vector position){
		return this.getCubeType(position.getCubeX(), position.getCubeY(), position.getCubeZ());
	}
	
	/**
	 * Check whether the given value is a valid material type
	 * @param i
	 * 			The type to check
	 * @return true if the given type is an integer from 0 to 3
	 */
	private boolean isValidMaterial(int i) {
		if (i>=0&&i<=3) {
			return true;
		}
		else {
			return false;
		}

	}

	/**
	 * Set the type of the cube at the given position to the given value
	 * @param x
	 * 			The x-coordinate of the cube for which to set a new type
	 * @param y
	 * 			The y-coordinate of the cube for which to set a new type
	 * @param z
	 * 			The z-coordinate of the cube for which to set a new type
	 * @param value
	 * 			The type to set the given cube to
	 * @post	The type of the given cube equals the given type
	 * @effect If the given cube is changing to passable material,
	 * 		   the positions where Units can stand and can spawn are updated accordingly
	 * @throws IllegalArgumentException
	 * 			The given type is not a valid cube type
	 */
	public void setCubeType(int x,int y, int z, int value) throws IllegalArgumentException {
		if (!isValidMaterial(value))
			throw new IllegalArgumentException();
		this.getCoordinates()[x][y][z]=value;
		this.modelListener.notifyTerrainChanged(x, y, z);
		if (value == 0 || value == 3){
			Vector position = new Vector(x,y,z);
			for (Vector vector:this.getAdjacentPositions(position)){
				if (this.getSpawnablePositions().contains(vector)){
					if (!unitCanSpawnAt(vector))
						this.getSpawnablePositions().remove(vector);
				} else {
					if (unitCanSpawnAt(vector))
						this.addSpawnablePosition(vector);
				}
				if (this.getStandablePositions().contains(vector)){
					if (!unitCanStandAt(vector))
						this.getStandablePositions().remove(vector);
				} else {
					if (unitCanStandAt(vector))
						this.addStandablePosition(vector);
				}
			}
			if (unitCanStandAt(position))
				this.addStandablePosition(position);
			if (unitCanSpawnAt(position))
				this.addSpawnablePosition(position);
		}
	}
	
	/**
	 * variable keeping track of the values of the cubetypes
	 */
	private int [][][] Coordinates;

	/**
	 * Return the collapseTime of this World.
	 */
	@Basic @Raw
	private double getCollapseTime() {
		return this.collapseTime;
	}

	/**
	 * Check whether the given collapseTime is a valid collapseTime for
	 * any World.
	 *  
	 * @param  collapseTime
	 *         The collapseTime to check.
	 * @return 
	 *       | result == (collapseTime >= 0) && (collapseTime < 5)
	 */
	private static boolean isValidCollapseTime(double collapseTime) {
		return (collapseTime >= 0) && (collapseTime < 5);
	}

	/**
	 * Set the collapseTime of this World to the given collapseTime.
	 * 
	 * @param  collapseTime
	 *         The new collapseTime for this World.
	 * @pre    The given collapseTime must be a valid collapseTime for any
	 *         World.
	 *       | isValidCollapseTime(collapseTime)
	 * @post   The collapseTime of this World is equal to the given
	 *         collapseTime.
	 *       | new.getCollapseTime() == collapseTime
	 */
	@Raw
	private void setCollapseTime(double collapseTime) {
		assert isValidCollapseTime(collapseTime);
		this.collapseTime = collapseTime;
	}

	/**
	 * Variable registering the collapseTime of this World.
	 */
	private double collapseTime;

	/**
	 * Check whether this World has the given Unit as one of its
	 * Units.
	 * @param  Unit
	 *         The Unit to check.
	 * @return true if this World has the given Unit as one of its Units
	 */
	@Basic
	@Raw
	public boolean hasAsUnit(@Raw Unit Unit) {
		return this.gameObjects.contains(Unit);
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
	 *       |   Unit.canHaveAsWorld(this)
	 */
	@Raw
	private boolean canHaveAsUnit(Unit Unit) {
		return (Unit != null) && (Unit.canHaveAsWorld(this));
	}
//
//	/**
//	 * Check whether this World has proper Units attached to it.
//	 * 
//	 * @return True if and only if this World can have each of the
//	 *         Units attached to it as one of its Units,
//	 *         and if each of these Units references this World as
//	 *         the World to which they are attached.
//	 *       | for each Unit in Unit:
//	 *       |   if (hasAsUnit(Unit))
//	 *       |     then canHaveAsUnit(Unit) &&
//	 *       |          (Unit.getWorld() == this)
//	 */
//	public boolean hasProperUnits() {
//		for (Unit Unit : Units) {
//			if (!canHaveAsUnit(Unit))
//				return false;
//			if (Unit.getWorld() != this)
//				return false;
//		}
//		return true;
//	}

	/**
	 * Return all Units currently living in this World.
	 */
	public Set<Unit> getUnits(){
		Set<Unit> result = new HashSet<>();
		for (GameObject obj:this.getGameObjects())
			if (obj instanceof Unit)
				result.add((Unit) obj);
		return result;
	}

	/**
	 * Return the number of Units associated with this World.
	 *
	 * @return  The total number of Units collected in this World.
	 *        | result ==
	 *        |   card({Unit:Unit | hasAsUnit({Unit)})
	 */
	private int getNbUnits() {
		return this.getUnits().size();
	}

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
	private boolean canHaveAsFaction(Faction Faction) {
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
	 * @post   If this World did not already have 5 active factions,
	 * 			This World has the given Faction as one of its Factions.
	 * @throws IllegalArgumentException
	 * 			This world cannot have the given Faction as one of its Factions.
	 */
	public void addFaction(@Raw Faction Faction) throws IllegalArgumentException {
		if (! this.canHaveAsFaction(Faction))
			throw new IllegalArgumentException();
		if (this.getActiveFactions().size() >= 5)
			return;
		else {
		Factions.add(Faction);
		Faction.addToWorld(this);
		}
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
	void removeFaction(Faction Faction) {
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
	boolean hasAsGameObject(@Raw GameObject gameObject) {
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
	 * Return a Set containing all GameObjects at a given position in this World.
	 * @param position
	 * 			The position of the GameObjects to return
	 * @throws IllegalArgumentException
	 * 			The given position is outside of the World
	 * 			| isInsideWorld(position)
	 */
	public Set<GameObject> getGameObjectsAt(Vector position) throws IllegalArgumentException{
		if (!isInsideWorld(position))
			throw new IllegalArgumentException();
		position = position.getCubePosition();
		Set<GameObject> result = new HashSet<>();
		for (GameObject object: this.getGameObjects()){
			if (object.getPosition().getCubePosition().equals(position)){
				result.add(object);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @return returns all the logs in this World
	 * 		result==Set<Log>(alllogs)
	 */
	public Set<Log> GetAllLogs() {
		Set<Log> Logs= new HashSet<>();
		for (GameObject log : gameObjects) {
			if (log instanceof Log) {
				Logs.add((Log)log);
			}
		}
		return Logs;
	}
	
	/**
	 * 
	 * @return returns all the Boulders in this World
	 * 		result==Set<Log>(allBoulders)
	 */
	public Set<Boulder> GetAllBoulders() {
		Set<Boulder> Boulders= new HashSet<>();
		for (GameObject bObject : gameObjects) {
			if (bObject instanceof Boulder) {
				Boulders.add((Boulder)bObject);
			}
		}
		return Boulders;
	}
	/**
	 * 
	 * @return all gameobjects in this world
	 * 		result== this.gameobjects
	 */
	public Set<GameObject> getGameObjects(){
		return this.gameObjects;
	}
	
	/**
	 * Check whether the cube of the given position contains at least one GameObject.
	 * @param position
	 * 			The position whose cube needs to be checked for GameObjects.
	 * @return true if there is at least one GameObject occupying the cube of the given position.
	 */
	boolean containsGameObject(Vector position){
		return this.getGameObjectsAt(position).size() > 0;
	}
	
	/**
	 * Check whether the given cube contains at least one GameObject.
	 * @param x
	 * 			The x-coordinate of the cube to be checked.
	 * @param y
	 * 			The y-coordinate of the cube to be checked.
	 * @param z
	 * 			The z-coordinate of the cube to be checked.
	 * @return true if there is at least one GameObject occupying the cube of the given position.
	 */
	boolean containsGameObject(int x, int y, int z){
		return this.containsGameObject(new Vector(x,y,z));
	}
	
	/**
	 * Check whether the cube of the given position contains at least one Log and at least one Boulder.
	 * @param position
	 * 			The cube in which this position lies needs to be checked
	 * @return true if at least one Log and one Boulder occupy the cube of the given position.
	 */
	boolean containsLogAndBoulder(Vector position){
		boolean boulder = false;
		boolean log = false;
		for (GameObject object:getGameObjectsAt(position)){
			if (object instanceof Log)
				log = true;
			if (object instanceof Boulder)
				boulder = true;
		}
		return (boulder && log);
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
	public void addGameObject(GameObject gameObject) {
		if (gameObject == null || !gameObject.canHaveAsWorld(this))
			throw new IllegalArgumentException();
		if (gameObject instanceof Unit)
			if (this.getNbUnits() >= 100)
				return;
		gameObjects.add(gameObject);
		gameObject.addToWorld(this);
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
	void removeGameObject(GameObject gameObject) {
		assert this.hasAsGameObject(gameObject);
		gameObjects.remove(gameObject);
		gameObject.removeFromWorld();
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


	/**
	 * 
	 * @param i
	 * @return returns whether this material is solid or not
	 * 		result==(i==1||i==2)
	 */
	private boolean IsSolidMaterial(int i){
		if(i==1||i==2)
			return true;
		else {
			return false;
		}
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return	returns whether this cube is made of solid material
	 * 		result==IsSolidMaterial(getCubeType(x,y,z))
	 */
	boolean isSolidGround(int x, int y, int z) {
		return IsSolidMaterial(getCubeType(x,y,z));
	}

	/**
	 * Check whether the cube at the given position is solid material
	 * @param position
	 * 			The position to check for solid material
	 * @return true if and only if the material at the given position is either Wood or Rock
	 * 			| this.isSolidGround(position.getCubeX(), position.getCubeY(), position.getCubeZ())
	 */
	public boolean isSolidGround(Vector position) {
		return this.isSolidGround(position.getCubeX(), position.getCubeY(), position.getCubeZ());
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

	void caveIn(int x, int y, int z) {
		int value = this.getCubeType(x, y, z);
		setCubeType(x, y, z, 0);
		List<int[]> collapsing = this.connectedToBorder.changeSolidToPassable(x, y, z);
		for (int[] cube:collapsing){
			toBeCollapsed.add(new Vector(cube[0],cube[1],cube[2]));
		}
		if (Math.random()<=0.25) {
			if (value==1){
				new Boulder(new Vector(x+World.CUBELENGTH/2, y+World.CUBELENGTH/2, z+World.CUBELENGTH/2), this);
			}
			else if (value == 2){
				new Log(new Vector(x+World.CUBELENGTH/2, y+World.CUBELENGTH/2, z+World.CUBELENGTH/2), this);
			}
		}
	}

	/**
	 * Variable registering the cubes that need to be collapsed within at most 5 seconds of gametime.
	 */
	private Set<Vector> toBeCollapsed = new HashSet<>();

	/**
	 * Return a set containing all cubes directly adjacent to the given position
	 * that are not outside the gameworld.
	 */
	Set<Vector> getDirectlyAdjacentPositions(Vector position){
		Set<Vector> result = new HashSet<>();
		position = position.getCubePosition();
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
	 * Return a Set containing all cubes adjacent to the given position
	 * @param position
	 *			The position for which to get the adjacent positions.
	 *
	 */
	Set<Vector> getAdjacentPositions(Vector position){
		Set<Vector> result = new HashSet<>();
		position = position.getCubePosition();
		for (int x = -1; x <= 1; x++){
			for (int y = -1; y <= 1; y++){
				for(int z = -1; z <= 1; z++){
					if(Math.abs(x)+Math.abs(y)+Math.abs(z) != 0){
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
	 * Return a list containing all positions in this game World where a Unit can spawn.
	 */
	List<Vector> getSpawnablePositions(){
		return this.spawnablePositions;
	}

	/**
	 * Check whether a Unit can spawn at the given position
	 * @param position
	 * 			The position to check for whether a Unit can spawn there.
	 * @return true if and only if the given position is inside the gameworld,
	 * 			does not contain solid material and the ground below it is
	 * 			either the bottom or solid ground.
	 * 			| result == (isInsideWorld(position)) &&
	 * 			|			(!isSolidGround(position.getCubeX(), position.getCubeY(), position.getCubeZ())) &&
	 * 			|			((position.getCubeZ() == 0) || (isSolidGround(position.getCubeX(), position.getCubeY(), position.getCubeZ()-1)))
	 */
	boolean unitCanSpawnAt(Vector position){
		if (!isInsideWorld(position))
			return false;
		if (isSolidGround(position.getCubeX(), position.getCubeY(), position.getCubeZ()))
			return false;
		return (position.getCubeZ() == 0) || (isSolidGround(position.getCubeX(), position.getCubeY(), position.getCubeZ()-1));
	}

	/**
	 * Add a position to the List of positions where a Unit can spawn.
	 * @param position
	 * 			The position to be added to the list of spawnable positions.
	 * @pre		The cube below the Unit is either the lowest level or solid ground.
	 * 			| (position.getCubeZ() == 0) || (isSolidGround(position.getCubeX(), position.getCubeY(), position.getCubeZ()))
	 * @post	The given position has been added to the list of spawnable positions.
	 * 			| this.getSpawnablePositions().contains(position)
	 */
	private void addSpawnablePosition(Vector position){
		assert (unitCanSpawnAt(position));
		this.spawnablePositions.add(position);
	}

	/**
	 * A List containing all positions in this game World where a Unit can stand.
	 */
	private List<Vector> spawnablePositions = new ArrayList<>();

	/**
	 * Return a list containing all positions in this game World where a Unit can stand.
	 */
	List<Vector> getStandablePositions(){
		return this.standablePositions;
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
	public boolean unitCanStandAt(Vector position){
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
	 * Add a position to the List of positions where a Unit can stand.
	 * @param position
	 * 			The position to be added to the list of standable positions.
	 * @pre		A Unit must be able to stand at the given position.
	 * 			| unitCanStandAt(position)
	 * @post	The given position has been added to the list of standable positions.
	 * 			| this.getStandablePositions().contains(position)
	 */
	private void addStandablePosition(Vector position){
		assert (unitCanStandAt(position));
		this.standablePositions.add(position);
	}

	/**
	 * A List containing all positions in this game World where a Unit can stand.
	 */
	private List<Vector> standablePositions = new ArrayList<>();
	
	/**
	 * Return the cubes that are directly adjacent to the given cube and are solid
	 * @param X
	 * 			The x-position of the cube to be checked
	 * @param Y
	 * 			The y-position of the cube to be checked
	 * @param Z
	 * 			The z-position of the cube to be checked
	 * @return A Set of Vectors containing all directly adjacent solid cubes
	 */
	Set <Vector>  CheckadjacentValidPositions(int X, int Y, int Z) {
		Set <Vector>  validpositions = new HashSet<>() ;
		for(int x=-1; x<=1;x++){
			if (x==0) {

			}
			else {
				if (isInsideWorld(new Vector(x+X, Y, Z))&&isSolidGround(x+X, Y, Z)) {
					validpositions.add(new Vector(x+X, Y, Z));
				}
			}

		}

		for(int y=-1;y<=1;y++){
			if (y==0) {

			}
			else {
				if (isInsideWorld(new Vector(X, Y+y, Z))&&isSolidGround(X, Y+y, Z)) {
					validpositions.add(new Vector(X, Y+y, Z));
				}

			}
		}

		for(int z=-1; z<=1;z++){
			if (z==0) {

			}
			else {
				if (isInsideWorld(new Vector(X, Y, Z+z))&&isSolidGround(X, Y, Z+z)) {
					validpositions.add(new Vector(X, Y, Z+z));
				}

			}
		}
		return validpositions;
	}
	
	/**
	 * TODO: documentatie
	 * @param coll
	 * @param unit
	 * @return
	 */
	public static <T extends GameObject> T getNearestObject(Collection<T> coll, Unit unit){
		Heap<Node> open = new Heap<>();
		List<Node> closed = new ArrayList<>();
		Node start = new Node(unit.getPosition());
		open.add(start);
		while (open.size() > 0){
			Node current = open.pop();
			closed.add(current);
			for (T object : coll){
				if (object != unit)
					if (object.getPosition().getCubePosition().equals(current.getCubeCoordinates()))
						return object;
			}
			for (Node neighbour:current.getNeighbouringNodes()){
				if (!unit.getWorld().unitCanStandAt(neighbour.getCubeCoordinates()) ||
						closed.contains(neighbour))
					continue;
				neighbour.setGCost(current.getGCost()+Node.calculateDistance(current, neighbour));
				neighbour.setHCost(0);
				if (!open.contains(neighbour)){
					open.add(neighbour);
				}
				else {
					int index = open.getIndex(neighbour);
					if (neighbour.compareTo(open.get(index)) < 0){
						open.replace(index, neighbour);
					}
				}
			}
		}
		throw new NoSuchElementException();
	}
	
	
	
	
	
	
}
