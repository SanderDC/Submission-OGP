package hillbillies.model;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
/** TO BE ADDED TO CLASS HEADING
 * @invar  The property_name_Eng of each object_name_Eng must be a valid property_name_Eng for any
 *         object_name_Eng.
 *       | isValidPropertyName_Java(getPropertyName_Java())
 */
import javafx.collections.transformation.SortedList;
public class Scheduler {
	
//	public Scheduler(Faction faction ){
//		this.faction = faction;
//	}


	/**
	 * Return the tasks of this List<Task>.
	 */
	@Basic @Raw
	public List<Task> getTasks() {
		return this.tasks;
	}

	/**
	 * Check whether the given tasks is a valid tasks for
	 * any List<Task>.
	 *  
	 * @param  tasks
	 *         The tasks to check.
	 * @return 
	 *       | result == 
	 */
	public static boolean isValidTasks(List tasks) {
		return false;
	}



	/**
	 * Variable registering the property_name_Eng of this List<Task>.
	 */
	private List <Task> tasks=new ArrayList<>();



	public void AddOneTask(Task task){
		this.tasks.add(task);
	}
	public void RemoveOneTask(Task task) {
		this.tasks.remove(task);
	}

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

	public Task getTopPriorityTask(){
		return null;
	}
	public List<Task> getConditionalTask(){
		return null;
	}
	public void replaceTask(){

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
	
}
