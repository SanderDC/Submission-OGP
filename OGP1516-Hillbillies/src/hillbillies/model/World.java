package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class defining a game World for Units to exist in
 * @author Sander Declercq
 * @author Bram Belpaire
 * @invar   Each World must have proper Units.
 *        | hasProperUnits()
 *
 */
public class World {

	/**
	 * Initialize a new World with given coordinates and without any Units or Factions
	 * @param Coordinates
	 * 			The given coordinates for this new World
	 * @post   This new World has no Units yet.
	 * @post   This new World has no Factions yet.
	 */
	public World(int[][][] Coordinates){
		this.Coordinates=Coordinates;
	}
	private int [][][] Coordinates;

	private int[][][]getCoordinates () {
		return this.Coordinates;
	}
	public int maxCoordinateX() {
		return getCoordinates().length;
	}
	public int maxCoordinateY(){
		return getCoordinates()[0].length;

	}
	public int maxCoordinateZ() {
		return getCoordinates()[0][1].length;

	}
	public void advanceTime(double time)throws IllegalArgumentException {
		if (time<0||time>0.2)
			throw new IllegalArgumentException();
	}
	public  int getCubeType(int x,int y, int z) {
		return getCoordinates()[x][y][z];

	}
	public void setCubeType(int x,int y, int z, int value) {
		this.getCoordinates()[x][y][z]=value;
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

	/** TO BE ADDED TO THE CLASS INVARIANTS
	 * @invar   Each World must have proper Factions.
	 *        | hasProperFactions()
	 */

	/**
	 * Initialize this new World as a non-terminated World with 
	 * no Factions yet.
	 * 
	 * @post   This new World has no Factions yet.
	 *       | new.getNbFactions() == 0
	 */
	@Raw
	public World() {
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

}
