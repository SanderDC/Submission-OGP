package hillbillies.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * 
 * @author Sander Declercq
 * @author Bram Belpaire
 * @invar   Each Scheduler must have proper Tasks.
 *        | hasProperTasks()
 * @invar	Each Scheduler must have a valid Faction
 * 		  | canHaveAsFaction(getFaction())
 */
public class Scheduler implements Iterable<Task>{

	/**
	 * Initialize this new Scheduler as a non-terminated Scheduler with 
	 * no Tasks yet.
	 * 
	 * @post   	This new Scheduler has no Tasks yet.
	 *        | new.getNbTasks() == 0
	 * @post	This new Scheduler has the given faction as its faction
	 * 		  | (new this).getFaction() == faction
	 * @post	The given faction has this Scheduler as its Scheduler
	 * 		  | (new faction).getScheduler() == this
	 * @throws	IllegalArgumentException
	 * 			The given Faction is the null reference
	 * 		  | (faction == null)
	 */
	public Scheduler(Faction faction) throws IllegalArgumentException {
		if (faction == null)
			throw new IllegalArgumentException();
		faction.setScheduler(this);
	}

	/**
	 * Return the Task associated with this Scheduler at the
	 * given index.
	 * 
	 * @param  index
	 *         The index of the Task to return.
	 * @throws IndexOutOfBoundsException
	 *         The given index is not positive or it exceeds the
	 *         number of Tasks for this Scheduler.
	 *       | (index < 1) || (index > getNbTasks())
	 */
	@Basic
	@Raw
	public Task getTaskAt(int index) throws IndexOutOfBoundsException {
		return tasks.get(index - 1);
	}

	/**
	 * Return the number of Tasks associated with this Scheduler.
	 */
	@Basic
	@Raw
	public int getNbTasks() {
		return tasks.size();
	}

	/**
	 * Return the Task with the highest priority that is not currently being executed
	 * @throws NoSuchElementException
	 * 			There is no Task that is not being executed
	 * 			| this.getNbTasks() == 0 ||
	 * 			| for each task in tasks:
	 * 			|			task.isBeingExecuted()
	 */
	public Task getTopPriorityTask() throws NoSuchElementException{
		Optional<Task> result = this.tasks.stream().filter((Task t) -> !t.isBeingExecuted())
				.max((Task t1, Task t2) -> t1.compareTo(t2));
		if (result.isPresent())
			return result.get();
		else
			throw new NoSuchElementException();
	}

	/**
	 * Check whether this Scheduler can have the given Task
	 * as one of its Tasks.
	 * 
	 * @param  task
	 *         The Task to check.
	 * @return True if and only if the given Task is effective
	 *         and that Task can have this Scheduler as its Scheduler.
	 *       | result ==
	 *       |   (task != null) &&
	 *       |   task.canHaveAsScheduler(this) &&
	 *       |	 !task.isTerminated()
	 */
	@Raw
	public boolean canHaveAsTask(Task task) {
		return (task != null) && (!task.isTerminated()) && (task.canHaveAsScheduler(this));
	}

	/**
	 * Check whether this Scheduler can have the given Task
	 * as one of its Tasks at the given index.
	 * 
	 * @param  task
	 *         The Task to check.
	 * @return False if the given index is not positive or exceeds the
	 *         number of Tasks for this Scheduler + 1.
	 *       | if ( (index < 1) || (index > getNbTasks()+1) )
	 *       |   then result == false
	 *         Otherwise, false if this Scheduler cannot have the given
	 *         Task as one of its Tasks.
	 *       | else if ( ! this.canHaveAsTask(task) )
	 *       |   then result == false
	 *         Otherwise, true if and only if the given Task is
	 *         not registered at another index than the given index.
	 *       | else result ==
	 *       |   for each I in 1..getNbTasks():
	 *       |     (index == I) || (getTaskAt(I) != task)
	 */
	@Raw
	public boolean canHaveAsTaskAt(Task task, int index) {
		if ((index < 1) || (index > getNbTasks() + 1))
			return false;
		if (!this.canHaveAsTask(task))
			return false;
		for (int i = 1; i < getNbTasks(); i++)
			if ((i != index) && (getTaskAt(i) == task))
				return false;
		return true;
	}

	/**
	 * Check whether this Scheduler has proper Tasks attached to it.
	 * 
	 * @return True if and only if this Scheduler can have each of the
	 *         Tasks attached to it as a Task at the given index,
	 *         and if each of these Tasks references this Scheduler as
	 *         the Scheduler to which they are attached.
	 *       | result ==
	 *       |   for each I in 1..getNbTasks():
	 *       |     ( this.canHaveAsTaskAt(getTaskAt(I) &&
	 *       |       (getTaskAt(I).getScheduler() == this) )
	 */
	public boolean hasProperTasks() {
		for (int i = 1; i <= getNbTasks(); i++) {
			if (!canHaveAsTaskAt(getTaskAt(i), i))
				return false;
		}
		return true;
	}

	/**
	 * Check whether this Scheduler has all given Tasks as part of its
	 * Tasks.
	 * 
	 * @param  tasks
	 *         The Tasks to check.
	 * @return The given Tasks are registered at some position as
	 *         Tasks of this Scheduler.
	 *       | for each task in tasks:
	 *       | 			for some I in 1..getNbTasks():
	 *       |   		getTaskAt(I) == task
	 */
	public boolean hasAsTasks(Collection<Task> tasks) {
		return this.tasks.containsAll(tasks);
	}
	/**
	 * Check whether this Scheduler has the given Task as part of its
	 * Tasks.
	 * 
	 * @param  task
	 *         The Task to check.
	 * @return The given Task is registered at some position as
	 *         Tasks of this Scheduler.
	 *       | 			for some I in 1..getNbTasks():
	 *       |   		getTaskAt(I) == task
	 */
	public boolean hasAsTask( Task task) {
		if(!this.tasks.contains(task))
			return false;
		return true;		
	}

	/**
	 * Add the given Task to the list of Tasks of this Scheduler.
	 * 
	 * @param  tasks
	 *         The Tasks to be added.
	 * @post	Every Task that is a valid Task for this Scheduler
	 * 		  	and is not yet one of this Scheduler's tasks
	 * 			is added to this Scheduler
	 * 			| for each task in tasks:
	 * 			|			if (this.canHaveAsTask(task) && !this.hasAsTask(task))
	 * 			|			then (new this).hasAsTask(task) &&
	 * 			|					(new task).hasAsScheduler(this)
	 * @post   The number of Tasks of this Scheduler is
	 *         incremented by the amount of given tasks or less.
	 *       | new.getNbTasks() <= getNbTasks() + tasks.size()
	 */
	public void addTasks(@Raw Task... tasks) {
		for (Task task:tasks){
			if(this.canHaveAsTask(task) && !this.hasAsTask(task)){
				this.tasks.add(task);
				task.addScheduler(this);
			}
		}
	}

	/**
	 * Remove the given Task from the list of Tasks of this Scheduler.
	 * 
	 * @param  tasks
	 *         The Tasks to be removed.
	 * @post   The number of Tasks of this Scheduler is
	 *         decremented by the amount of given Tasks or less.
	 *       | new.getNbTasks() >= getNbTasks() - tasks.size()
	 * @post   This Scheduler no longer has the given Tasks as
	 *         some of its Tasks and the given Tasks no longer have this Scheduler
	 *         as one of their Schedulers
	 *       | for each task in tasks:
	 *       | 	! (new this).hasAsTask(task)
	 *       |	! (new task).hasAsScheduler(this)
	 */
	@Raw
	public void removeTasks(Task... tasks) {
		for (Task task:tasks){
			if(task != null && this.hasAsTask(task)){
				this.tasks.remove(task);
				task.removeScheduler(this);
			}
		}
	}

	/**
	 * Replace a given Task with a given new Task
	 * @param oldTask
	 * 			The Task to be replaced
	 * @param newTask
	 * 			The new Task to take the place of the old Task
	 * @effect	If the Task to be replaced is currently being executed,
	 * 			it is interrupted
	 * 			| if (oldTask.isBeingExecuted())
	 * 			| then oldTask.interrupt()
	 * @post	This Scheduler no longer has the given oldTask as one of its Tasks
	 * 			| ! new.hasAsTask(oldTask)
	 * @post	This Scheduler has the given newTask as one of its Tasks
	 * 			| new.hasAsTask(newTask)
	 * @throws	IllegalArgumentException
	 * 			This Scheduler does not have the given oldTask as one of its Tasks,
	 * 			or this Scheduler cannot have the given newTask as one of its Tasks
	 * 			| !this.hasAsTask(oldTask) || !this.canHaveAsTask(newTask)
	 */
	public void replaceTask(Task oldTask, Task newTask) throws IllegalArgumentException{
		if (!this.hasAsTask(oldTask) || !this.canHaveAsTask(newTask))
			throw new IllegalArgumentException();
		if (oldTask.isBeingExecuted())
			oldTask.removeFromUnit();
		int index = this.tasks.indexOf(oldTask);
		this.removeTasks(oldTask);
		this.tasks.add(index, newTask);
	}

	/**
	 * Variable referencing a list collecting all the Tasks
	 * of this Scheduler.
	 * 
	 * @invar  The referenced list is effective.
	 *       | tasks != null
	 * @invar  Each Task registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each task in tasks:
	 *       |   ( (task != null) &&
	 *       |     (! task.isTerminated()) )
	 * @invar  No Task is registered at several positions
	 *         in the referenced list.
	 *       | for each I,J in 0..tasks.size()-1:
	 *       |   ( (I == J) ||
	 *       |     (tasks.get(I) != tasks.get(J))
	 */
	private final List<Task> tasks = new ArrayList<Task>();

	/**
	 * Assign the given Task to the given Unit
	 * @param unit
	 * 			The Unit to assign the given Task to
	 * @param task
	 * 			The Task to assign to the given Unit
	 * @effect	The given Task is assigned to the given Unit
	 * 		  | task.assignToUnit(unit)
	 * @throws	IllegalArgumentException
	 * 			The given Unit is not effective
	 * 		  | (unit == null)
	 * @throws	IllegalArgumentException
	 * 			The given Task is not effective
	 * 		  | (task == null)
	 * @throws	IllegalArgumentException
	 * 			This Scheduler does not have the given Task as one of its Tasks
	 * 		  | !this.hasAsTask(task)
	 * @throws	IllegalArgumentException
	 * 			The given Unit does not belong to this Scheduler's Faction
	 * 		  | unit.getFaction() != this.getFaction()
	 * @throws	IllegalStateException
	 * 			The given Task is already being executed
	 * 		  | task.isBeingExecuted()
	 */
	public void assignTaskToUnit(Unit unit, Task task) throws IllegalArgumentException, IllegalStateException {
		if (unit == null || unit.getFaction() != this.getFaction() || task == null || !this.hasAsTask(task))
			throw new IllegalArgumentException();
		if (task.isBeingExecuted()) 
			throw new IllegalStateException();
		task.assignToUnit(unit);
	}
	
	/**
	 * Remove the Task the given Unit is currently executing from that Unit
	 * @param unit
	 * 			The Unit to remove a Task from
	 * @effect	The given Unit has its Task removed
	 * 		  | unit.getTask().removeFromUnit()
	 * @throws	IllegalArgumentException
	 * 			The given Unit is null
	 * 		  | (unit == null)
	 * @throws	IllegalStateException
	 * 			The given Unit is not executing a Task
	 * 		  | (!unit.hasTask())
	 */
	public void removeTaskFromUnit(Unit unit) throws IllegalArgumentException, IllegalStateException {
		if (unit == null)
			throw new IllegalArgumentException();
		if (!unit.hasTask())
			throw new IllegalStateException();
		unit.getTask().removeFromUnit();
	}
	
	/**
	 * Return the Faction of this Scheduler
	 */
	public Faction getFaction(){
		return this.faction;
	}

	/**
	 * Check whether this Scheduler can have the given Faction as its Faction
	 * @param faction
	 * 			The Faction for which to check whether it is a valid Faction for this Scheduler
	 * @return	true if and only if the given Faction is not null
	 * 		  | result == (faction != null)
	 */
	public boolean canHaveAsFaction(Faction faction){
		return (faction != null);
	}

	/**
	 * Add this Scheduler to the given Faction
	 * @param faction
	 * 			The Faction to add this Scheduler to
	 * @pre		The given Faction is effective and already has this Scheduler as its Scheduler
	 * 			| (faction != null && faction.getScheduler() == this)
	 * @post	This Scheduler has the given Faction as its Faction
	 * 			| (new this).getFaction() == faction
	 */
	void addToFaction(Faction faction){
		assert (faction != null && faction.getScheduler() == this);
		this.faction = faction;
	}

	/**
	 * Variable registering the Faction of this Scheduler
	 */
	private Faction faction;
	
	/**
	 * Return the Tasks managed by this Scheduler that satisfy a given condition
	 * @param predicate
	 * 			The predicate representing the condition the Tasks to be returned need to satisfy
	 * @return A List of Tasks that satisfy the given condition
	 * 			| result == this.tasks.stream().filter(predicate).collect(Collectors.toList())
	 */
	public List<Task> selectByCondition(Predicate <? super Task> predicate){
		return this.tasks.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * Return an iterator delivering all tasks managed by this Scheduler
	 * in order of descending priority.
	 */
	public Iterator<Task> iterator(){
		return this.tasks.stream().sorted((Task t1, Task t2) -> -1*t1.compareTo(t2)).iterator();
	}
}
