package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class implementing factions that Units in the game Hillbillies can belong to.
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 * @invar   Each Faction must have proper Units.
 *        | hasProperUnits()
 * @invar	The World of each Faction must be a valid World for that Faction
 * 			| canHaveAsWorld(getWorld())
 */
public class Faction {

	/**
	 * Initialize this new Faction in a given World as a non-terminated Faction with 
	 * no Units yet.
	 * 
	 * @post   This new Faction has no Units yet.
	 *       | new.getNbUnits() == 0
	 * @post	This new Faction has been added to the given World.
	 * 			| (new this).getWorld() == world && (new world).hasAsFaction(this)
	 */
	@Raw
	public Faction(World world) {
		this.terminated = false;
		world.addFaction(this);
	}

	/**
	 * Check whether this Faction has the given Unit as one of its
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
	 * Check whether this Faction can have the given Unit
	 * as one of its Units.
	 * 
	 * @param  Unit
	 *         The Unit to check.
	 * @return True if and only if the given Unit is effective
	 *         and that Unit is a valid Unit for a Faction.
	 *       | result ==
	 *       |   (Unit != null) &&
	 *       |   Unit.isValidFaction(this)
	 */
	@Raw
	public boolean canHaveAsUnit(Unit Unit) {
		return (Unit != null) && (!Unit.isTerminated()) && (Unit.canHaveAsFaction(this));
	}

	/**
	 * Check whether this Faction has proper Units attached to it.
	 * 
	 * @return True if and only if this Faction can have each of the
	 *         Units attached to it as one of its Units,
	 *         and if each of these Units references this Faction as
	 *         the Faction to which they are attached.
	 *       | for each Unit in Unit:
	 *       |   if (hasAsUnit(Unit))
	 *       |     then canHaveAsUnit(Unit) &&
	 *       |          (Unit.getFaction() == this)
	 */
	public boolean hasProperUnits() {
		for (Unit Unit : Units) {
			if (!canHaveAsUnit(Unit))
				return false;
			if (Unit.getFaction() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * Return all Units in this Faction.
	 */
	public Set<Unit> getUnits(){
		return this.Units;
	}

	/**
	 * Return the number of Units associated with this Faction.
	 *
	 * @return  The total number of Units collected in this Faction.
	 *        | result ==
	 *        |   card({Unit:Unit | hasAsUnit({Unit)})
	 */
	public int getNbUnits() {
		return Units.size();
	}

	/**
	 * Add the given Unit to the set of Units of this Faction.
	 * 
	 * @param  Unit
	 *         The Unit to be added.
	 * @pre    The given Unit is effective and already references
	 *         this Faction.
	 *       | (Unit != null) && (Unit.getFaction() == this)
	 * @post   This Faction has the given Unit as one of its Units.
	 *       | new.hasAsUnit(Unit)
	 */
	public void addUnit(@Raw Unit Unit) {
		assert (Unit != null) && (Unit.getFaction() == this);
		Units.add(Unit);
	}

	/**
	 * Remove the given Unit from the set of Units of this Faction.
	 * 
	 * @param  Unit
	 *         The Unit to be removed.
	 * @pre    This Faction has the given Unit as one of
	 *         its Units, and the given Unit does not
	 *         reference any Faction.
	 *       | this.hasAsUnit(Unit) &&
	 *       | (Unit.getFaction() == null)
	 * @post   This Faction no longer has the given Unit as
	 *         one of its Units.
	 *       | ! new.hasAsUnit(Unit)
	 * @post	If this Faction is empty, it is terminated
	 * 			| if new.getUnits().size == 0
	 * 			| then new.isTerminated()
	 */
	@Raw
	public void removeUnit(Unit Unit) {
		assert this.hasAsUnit(Unit) && (Unit.getFaction() == null);
		Units.remove(Unit);
		if (Units.size() == 0)
			this.terminate();
	}
	
	/**
	 * Check whether this faction is currently active. A faction is active when it has at least 1 member.
	 * @return	true if and only if the amount of members is larger than or equal to 1
	 * 			result == (Units.size() >= 1)
	 */
	public boolean isActive(){
		return (Units.size() >= 1);
	}

	/**
	 * Variable referencing a set collecting all the Units
	 * of this Faction.
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
	 * Return a boolean reflecting whether the faction has been terminated.
	 */
	@Basic
	public boolean isTerminated(){
		return this.terminated;
	}
	
	/**
	 * Terminate this Faction.
	 * @pre		This Faction does not have any Units.
	 * 			| this.getNbUnits() == 0
	 * @post	This Faction has been terminated
	 * 			and removed from the World it existed in.
	 * 			| (new this).isTerminated() &&
	 * 			| (new this).getWorld == null && !(new this.getWorld()).hasAsFaction(this)
	 */
	private void terminate(){
		assert (this.getNbUnits() == 0);
		this.terminated = true;
		this.removeFromWorld();
	}
	
	/**
	 * Variable registering whether this faction has been terminated.
	 */
	private boolean terminated;
	
	/**
	 * Check whether the given World is a valid game World for this Faction
	 * @param world
	 * 			The World to check
	 * @return	If this Faction has not been terminated, true if the given World
	 * 			is not the null reference
	 * 			If this Faction has been terminated, true if the given World
	 * 			is the null reference
	 */
	@Raw
	public boolean canHaveAsWorld(World world) {
		if (this.isTerminated())
			return (world == null);
		else
			return (world != null);
	}
	
	/**
	 * Return this Faction's current game World.
	 */
	@Basic @Raw
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Add this Faction to the given World
	 * @param world
	 * 			The World this Faction will exist in
	 * @pre		The given world is effective and already has this Faction
	 * 			as one of its Factions.
	 * @post	This Faction has been added to the given World
	 * @throws	IllegalArgumentException
	 * 			The given game World is not a valid game World for this Faction
	 */
	void addToWorld(World world) throws IllegalArgumentException{
		if (! this.canHaveAsWorld(world))
			throw new IllegalArgumentException("This is not a valid gameworld!");
		assert (world != null && world.hasAsFaction(this));
		this.world = world;
	}
	
	/**
	 * Remove this Faction from its World
	 * @pre		This faction's World is not the null reference
	 * 			| this.getWorld() != null
	 * @post	This Faction has been removed from its game World.
	 * 			| (new this).getWorld() == null && !(new this.getWorld()).hasAsFaction(this)
	 */
	private void removeFromWorld(){
		assert (this.getWorld() != null);
		World oldWorld = this.getWorld();
		this.world = null;
		oldWorld.removeFaction(this);
	}
	
	/**
	 * Variable registering the current game World for this Faction.
	 */
	private World world;
	
	
	//deel 3
	
	private Scheduler scheduler;
	public Scheduler getScheduler() {
		return this.scheduler;
	}
	private void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

}
