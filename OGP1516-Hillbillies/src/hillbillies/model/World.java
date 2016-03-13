package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;

public class World {
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
	
	/** TO BE ADDED TO THE CLASS INVARIANTS
	 * @invar   Each World must have proper Units.
	 *        | hasProperUnits()
	 */

	/**
	 * Initialize this new World as a non-terminated World with 
	 * no Units yet.
	 * 
	 * @post   This new World has no Units yet.
	 *       | new.getNbUnits() == 0
	 */
	@Raw
	public World() {
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
}
