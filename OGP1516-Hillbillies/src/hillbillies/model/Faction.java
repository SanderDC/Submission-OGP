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
 */
public class Faction {

	/**
	 * Initialize this new Faction as a non-terminated Faction with 
	 * no Units yet.
	 * 
	 * @post   This new Faction has no Units yet.
	 *       | new.getNbUnits() == 0
	 */
	@Raw
	public Faction() {
		this.terminated = false;
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
	 */
	@Raw
	public void removeUnit(Unit Unit) {
		assert this.hasAsUnit(Unit) && (Unit.getFaction() == null);
		Units.remove(Unit);
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
	 * Variable registering whether this faction has been terminated.
	 */
	private boolean terminated;

}
