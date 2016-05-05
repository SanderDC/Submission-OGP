package hillbillies.model;

import java.util.List;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @author Sander Declercq
 * @author Bram Belpaire
 * @invar  The priority of each Task must be a valid priority for any
 *         Task.
 *       | isValidPriority(getPriority())
 * @invar  The priority of each Task must be a valid priority for any
 *         Task.
 *       | isValidPriority(getPriority())
 */
public class Task implements Comparable<Task>{

	/**
	 * 
	 * @param name
	 * @param activitylist
	 * @param unit
	 * @param  priority
	 *         The priority for this new Task.
	 * @effect The priority of this new Task is set to
	 *         the given priority.
	 *       | this.setPriority(priority)
	 */
	public Task(String name, int priority, List activitylist, Unit unit){
		this.setPriority(priority);
	}
	
	/**
	 * Check whether this Task is currently being executed
	 */
	public boolean isBeingExecuted(){
		return inExecution;
	}
	
	/**
	 * Variable registering whether this Task is currently being executed
	 */
	private boolean inExecution=false;

	/**
	 * Interrupt this Task
	 * @post	This Task is no longer being executed
	 * 			| ! new.isBeingExecuted()
	 * @post	This Task is removed from its Unit
	 * 			| (new this.getUnit()).getTask() == null
	 * @post	This Task's Unit is the null reference
	 * 			| new.getUnit() == null
	 * @post	This Task's priority has been decremented by 1
	 * 			| new.getPriority() == this.getPriority() - 1
	 * @throws IllegalStateException
	 */
	void interrupt() throws IllegalStateException{
		if (!this.isBeingExecuted())
			throw new IllegalStateException();
		this.getUnit().setTask(null);
		this.inExecution = false;
		this.setUnit(null);
		this.setPriority(getPriority()-1);
	}

	public void AssignTaskToUnit(Unit u){

		if (u==null) {
			this.unit=null;
		}
		if (unit==null) {
			this.unit=u;
		}

	}

	/**
	 * Initialize this new Task with given priority.
	 *
	 * @param  priority
	 *         The priority for this new Task.
	 * @effect The priority of this new Task is set to
	 *         the given priority.
	 *       | this.setPriority(priority)
	 */
	public Task(int priority)
			throws IllegalArgumentException {
		this.setPriority(priority);
	}


	/**
	 * Return the priority of this Task.
	 */
	@Basic @Raw
	public int getPriority() {
		return this.priority;
	}

	/**
	 * Check whether the given priority is a valid priority for
	 * any Task.
	 *  
	 * @param  priority
	 *         The priority to check.
	 * @return true
	 *       | result == true
	 */
	public static boolean isValidPriority(int priority) {
		return true;
	}

	/**
	 * Set the priority of this Task to the given priority.
	 * 
	 * @param  priority
	 *         The new priority for this Task.
	 * @post   The priority of this new Task is equal to
	 *         the given priority.
	 *       | new.getPriority() == priority
	 * @throws IllegalArgumentException
	 *         The given priority is not a valid priority for any
	 *         Task.
	 *       | ! isValidPriority(getPriority())
	 */
	@Raw
	private void setPriority(int priority) 
			throws IllegalArgumentException {
		if (! isValidPriority(priority))
			throw new IllegalArgumentException();
		this.priority = priority;
	}

	/**
	 * Variable registering the priority of this Task.
	 */
	private int priority;

	/**
	 * Initialize this new Task with given Unit.
	 *
	 * @param  unit
	 *         The Unit for this new Task.
	 * @effect The Unit of this new Task is set to
	 *         the given Unit.
	 *       | this.setUnit(unit)
	 */
	public Task(Unit unit)
			throws IllegalArgumentException {
		this.setUnit(unit);
	}


	/**
	 * Return the Unit of this Task.
	 */
	@Basic @Raw
	public Unit getUnit() {
		return this.unit;
	}

	/**
	 * Check whether the given Unit is a valid Unit for
	 * this Task.
	 *  
	 * @param  Unit
	 *         The Unit to check.
	 * @return true if this Task is being executed and the given Unit is effective and not terminated,
	 * 			or if this Task is not being executed and the given Unit is the null reference
	 *       | if (this.isBeingExecuted())
	 *       | then result == (unit != null) && (!unit.isTerminated())
	 *       | else result == (unit == null)
	 */
	public boolean canHaveAsUnit(Unit unit) {
		if (this.isBeingExecuted())
			return (unit != null) && (!unit.isTerminated());
		else
			return unit == null;
	}

	/**
	 * Set the Unit of this Task to the given Unit.
	 * 
	 * @param  unit
	 *         The new Unit for this Task.
	 * @post   The Unit of this new Task is equal to
	 *         the given Unit.
	 *       | new.getUnit() == unit
	 * @throws IllegalArgumentException
	 *         The given Unit is not a valid Unit for any
	 *         Task.
	 *       | ! isValidUnit(getUnit())
	 */
	@Raw
	public void setUnit(Unit unit) 
			throws IllegalArgumentException {
		if (! canHaveAsUnit(unit))
			throw new IllegalArgumentException();
		this.unit = unit;
	}

	/**
	 * Variable registering the Unit of this Task.
	 */
	private Unit unit;
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	@Override
	public int compareTo(Task other) {
		if (this.getPriority() < other.getPriority())
			return -1;
		else if (this.getPriority() > other.getPriority())
			return 1;
		else
			return 0;
	}

}
