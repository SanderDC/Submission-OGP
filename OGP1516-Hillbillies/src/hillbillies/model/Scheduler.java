package hillbillies.model;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * 
 * @author Sander Declercq
 * @author Bram Belpaire
 * @invar   Each Scheduler must have proper Tasks.
 *        | hasProperTasks()
 */
public class Scheduler {

	/**
	 * Initialize this new Scheduler as a non-terminated Scheduler with 
	 * no Tasks yet.
	 * 
	 * @post   This new Scheduler has no Tasks yet.
	 *       | new.getNbTasks() == 0
	 */
	@Raw
	public Scheduler() {
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
		Task result = null;
		for (Task task:this.tasks){
			if (result == null){
				if (!task.isBeingExecuted()){
					result = task;
				}
			} else {
				if (!task.isBeingExecuted() && task.getPriority() > result.getPriority()){
					result = task;
				}
			}
		}
		if (result == null)
			throw new NoSuchElementException();
		return result;
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
	 *       |   Task.isValidScheduler(this)
	 */
	@Raw
	public boolean canHaveAsTask(Task task) {
		return (task != null);
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
	public boolean hasAsTasks(@Raw Task... tasks) {
		for (Task task:tasks)
			if(!this.tasks.contains(task))
				return false;
		return true;		
	}

	/**
	 * Add the given Task to the list of Tasks of this Scheduler.
	 * 
	 * @param  tasks
	 *         The Tasks to be added.
	 * @post	Every Task that is effective and is not yet one of this Scheduler's tasks
	 * 			is added to this Scheduler
	 * 			| for each task in tasks:
	 * 			|			if (task != null && !this.hasAsTask(task))
	 * 			|			then (new this).hasAsTask(task)
	 * @post   The number of Tasks of this Scheduler is
	 *         incremented by the amount of given tasks or less.
	 *       | new.getNbTasks() <= getNbTasks() + tasks.size()
	 */
	public void addTasks(@Raw Task... tasks) {
		for (Task task:tasks){
			if((task != null)
					&& (!this.hasAsTasks(task)))
				this.tasks.add(task);
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
	 *         some of its Tasks.
	 *       | for each task in tasks:
	 *       | 	! new.hasAsTask(task)
	 */
	@Raw
	public void removeTasks(Task... tasks) {
		for (Task task:tasks){
			if(task != null && this.hasAsTasks(task))
				this.tasks.remove(task);
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
		if (!this.hasAsTasks(oldTask) || !this.canHaveAsTask(newTask))
			throw new IllegalArgumentException();
		if (oldTask.isBeingExecuted())
			oldTask.interrupt();
		int index = this.tasks.indexOf(oldTask);
		this.tasks.remove(index);
		this.tasks.add(index, newTask);
	}
	
	/**
	 * Return a new list containing all Tasks currently managed by this Scheduler,
	 * ordered by descending priority
	 */
	private List<Task> getTasksSorted(){
		List<Task> result = new ArrayList<>();
		result.addAll(tasks);
		result.sort((Task t1, Task t2)->-1*t1.compareTo(t2));
		return result;
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

	public void AssignTaskToUnit(Unit unit, Task task){
		if (!task.isBeingExecuted()) {
			task.AssignTaskToUnit(unit);
			unit.setTask(task);
		}

	}
	public void unAssignTaskOfUnit(Unit unit) {
		unit.getTask().AssignTaskToUnit(null);
		unit.setTask(null);

	}

	public List<Task> getConditionalTask(){
		return null;
	}

	Faction getFaction(){
		return this.faction;
	}

	/**
	 * Check whether this Scheduler can have the given Faction as its Faction
	 * @param faction
	 * @return
	 */
	public boolean canHaveAsFaction(Faction faction){
		if (!this.isTerminated())
			return (faction != null) && (!faction.isTerminated());
		else
			return faction == null;
	}

	/**
	 * Add this Scheduler to the given Faction
	 * @param faction
	 * 			The Faction to add this Scheduler to
	 * @pre		The given Faction already has this Scheduler as its Scheduler
	 * 			| faction.getScheduler() == this
	 * @post	This Scheduler has the given Faction as its Faction
	 * 			| (new this).getFaction() == faction
	 * @throws IllegalArgumentException
	 * 			The given Faction is not a valid Faction for this Scheduler
	 * 			| (! this.canHaveAsFaction(faction))
	 */
	void addToFaction(Faction faction) throws IllegalArgumentException{
		if (!this.canHaveAsFaction(faction))
			throw new IllegalArgumentException();
		assert (faction.getScheduler() == this);
		this.faction = faction;
	}

	/**
	 * Variable registering the Faction of this Scheduler
	 */
	private Faction faction;

	public boolean isTerminated(){
		return this.isTerminated();
	}

	/**
	 * Terminate this Scheduler
	 * @pre		This Scheduler's Faction has been terminated
	 * 			| this.getFaction().isTerminated()
	 * @post	This Scheduler is terminated
	 * 			| new.isTerminated()
	 * @post	This Scheduler has been removed from its Faction
	 * 			| (new this).getFaction() == null
	 */
	void terminate(){
		assert (this.getFaction().isTerminated());
		this.terminated = true;
		this.faction = null;
	}

	/**
	 * Variable registering whether this Scheduler has been terminated
	 */
	private boolean terminated;
	
	/**
	 * Return an iterator delivering all tasks managed by this Scheduler
	 * in order of descending priority.
	 */
	public Iterator<Task> iterator(){
		
		return new Iterator<Task>(){
			
			private List<Task> tasks = Scheduler.this.getTasksSorted();
			
			private int nbItemsHandled = 0;
			
			@Override
			public boolean hasNext() {
				return nbItemsHandled < getNbTasks();
			}

			@Override
			public Task next() {
				Task result = tasks.get(nbItemsHandled);
				nbItemsHandled += 1;
				return result;
			}
			
		};
	}
}
