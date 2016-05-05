package hillbillies.model;

import java.util.*;

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
 * @invar   Each Task must have proper Schedulers.
 *        | hasProperSchedulers()
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
	 * @post   This new Task has no Schedulers yet.
	 *       | new.getNbSchedulers() == 0
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
	
	/** TO BE ADDED TO THE CLASS INVARIANTS
	 * @invar   Each Task must have proper Schedulers.
	 *        | hasProperSchedulers()
	 */

	/**
	 * Initialize this new Task as a non-terminated Task with 
	 * no Schedulers yet.
	 * 
	 * @post   This new Task has no Schedulers yet.
	 *       | new.getNbSchedulers() == 0
	 */
	@Raw
	public Task() {
	}

	/**
	 * Check whether this Task has the given Scheduler as one of its
	 * Schedulers.
	 * 
	 * @param  scheduler
	 *         The Scheduler to check.
	 */
	@Basic
	@Raw
	public boolean hasAsScheduler(@Raw Scheduler scheduler) {
		return schedulers.contains(scheduler);
	}

	/**
	 * Check whether this Task can have the given Scheduler
	 * as one of its Schedulers.
	 * 
	 * @param  scheduler
	 *         The Scheduler to check.
	 * @return True if and only if the given Scheduler is effective
	 *         and that Scheduler is a valid Scheduler for a Task.
	 *       | result ==
	 *       |   (scheduler != null) &&
	 *       |   scheduler.canHaveAsTask(this)
	 */
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler) {
		return (scheduler != null) && (scheduler.canHaveAsTask(this));
	}

	/**
	 * Check whether this Task has proper Schedulers attached to it.
	 * 
	 * @return True if and only if this Task can have each of the
	 *         Schedulers attached to it as one of its Schedulers,
	 *         and if each of these Schedulers has this Task as one of its Tasks
	 *       | for each scheduler in Scheduler:
	 *       |   if (hasAsScheduler(scheduler))
	 *       |     then canHaveAsScheduler(scheduler) &&
	 *       |          (scheduler.hasAsTasks(this))
	 */
	public boolean hasProperSchedulers() {
		for (Scheduler scheduler : schedulers) {
			if (!canHaveAsScheduler(scheduler))
				return false;
			if (!scheduler.hasAsTasks(this))
				return false;
		}
		return true;
	}

	/**
	 * Return the number of Schedulers associated with this Task.
	 *
	 * @return  The total number of Schedulers collected in this Task.
	 *        | result ==
	 *        |   card({scheduler:Scheduler | hasAsScheduler({scheduler)})
	 */
	public int getNbSchedulers() {
		return schedulers.size();
	}

	/**
	 * Add the given Scheduler to the set of Schedulers of this Task.
	 * 
	 * @param  scheduler
	 *         The Scheduler to be added.
	 * @pre    The given Scheduler is effective and already references
	 *         this Task.
	 *       | (scheduler != null) && (scheduler.hasAsTasks(this))
	 * @post   This Task has the given Scheduler as one of its Schedulers.
	 *       | new.hasAsScheduler(scheduler)
	 */
	public void addScheduler(@Raw Scheduler scheduler) {
		assert (scheduler != null) && (scheduler.hasAsTasks(this));
		schedulers.add(scheduler);
	}

	/**
	 * Remove the given Scheduler from the set of Schedulers of this Task.
	 * 
	 * @param  scheduler
	 *         The Scheduler to be removed.
	 * @pre    This Task has the given Scheduler as one of
	 *         its Schedulers, and the given Scheduler does not
	 *         reference this Task.
	 *       | this.hasAsScheduler(scheduler) &&
	 *       | (!scheduler.hasAsTasks(this))
	 * @post   This Task no longer has the given Scheduler as
	 *         one of its Schedulers.
	 *       | ! new.hasAsScheduler(scheduler)
	 */
	@Raw
	public void removeScheduler(Scheduler scheduler) {
		assert this.hasAsScheduler(scheduler) && (!scheduler.hasAsTasks(this));
		schedulers.remove(scheduler);
	}

	/**
	 * Variable referencing a set collecting all the Schedulers
	 * of this Task.
	 * 
	 * @invar  The referenced set is effective.
	 *       | schedulers != null
	 * @invar  Each Scheduler registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each scheduler in schedulers:
	 *       |   ( (scheduler != null) &&
	 *       |     (! scheduler.isTerminated()) )
	 */
	private final Set<Scheduler> schedulers = new HashSet<Scheduler>();
	
	/**
	 * Terminate this Task.
	 *
	 * @post   This Task  is terminated.
	 *       | new.isTerminated()
	 * @post   	The Task of the Unit that was executing this Task is the null reference
	 * 		   	| (new this.getUnit()).getTask() == null
	 * @post	This Task's executing Unit is the null reference
	 * 			| (new this).getUnit() == null
	 * @effect This Task is removed from all of its Schedulers
	 * 		   | for each scheduler in schedulers:
	 * 		   | 			scheduler.removeTasks(this)
	 */
	 public void terminate() {
		 for (Scheduler scheduler:this.schedulers)
			 scheduler.removeTasks(this);
		 this.getUnit().setTask(null);
		 this.setUnit(null);
		 this.isTerminated = true;
	 }
	 
	 /**
	  * Return a boolean indicating whether or not this Task
	  * is terminated.
	  */
	 @Basic @Raw
	 public boolean isTerminated() {
		 return this.isTerminated;
	 }
	 
	 /**
	  * Variable registering whether this person is terminated.
	  */
	 private boolean isTerminated = false;
	 
}
