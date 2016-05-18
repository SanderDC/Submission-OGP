package hillbillies.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.expressions.IExpression;
import hillbillies.model.statements.IExecutableStatement;
import hillbillies.model.statements.Statement;

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
 * @invar  The name of each Task must be a valid name for any
 *         Task.
 *       | isValidName(getName())
 * @invar  The selectedPosition of each Task must be a valid selectedPosition for any
 *         Task.
 *       | isValidSelectedPosition(getSelectedPosition())
 */
public class Task implements Comparable<Task>{

	/**
	 * Initialize this new Task as a non-terminated Task with 
	 * given name, priority and activities, and no Schedulers yet.
	 * @param  name
	 *         The name for this new Task.
	 * @param activitylist
	 * 	  	   The statement to be executed in this Task
	 * @param  priority
	 *         The priority for this new Task.
	 * @effect The priority of this new Task is set to
	 *         the given priority.
	 *       | this.setPriority(priority)
	 * @effect The name of this new Task is set to
	 *         the given name.
	 *       | this.setName(name)
	 * @post   This new Task has no Schedulers yet.
	 *       | new.getNbSchedulers() == 0
	 */
	public Task(String name, int priority,Statement activitylist)
			throws IllegalArgumentException {
		this.setPriority(priority);
		this.setName(name);
		this.statements=activitylist;
		if (activitylist!=null) {
			this.statements.addToTask(this);
		}
		
		this.selectedPosition = null;
	}

	/**
	 * Initialize this new Task with given selectedPosition.
	 * 
	 * @param  selectedPosition
	 *         The selectedPosition for this new Task.
	 * @param  name
	 *         The name for this new Task.
	 * @param  activitylist
	 * 		   The statement to be executed in this Task
	 * @param  priority
	 *         The priority for this new Task.
	 * @effect The priority of this new Task is set to
	 *         the given priority.
	 *       | this.setPriority(priority)
	 * @effect The name of this new Task is set to
	 *         the given name.
	 *       | this.setName(name)
	 * @post   This new Task has no Schedulers yet.
	 *       | new.getNbSchedulers() == 0
	 * @post   If the given selectedPosition is a valid selectedPosition for any Task,
	 *         the selectedPosition of this new Task is equal to the given
	 *         selectedPosition. Otherwise, the selectedPosition of this new Task is equal
	 *         to new Vector(0,0,0).
	 *       | if (isValidSelectedPosition(selectedPosition))
	 *       |   then new.getSelectedPosition() == selectedPosition
	 *       |   else for each component in new.getSelectedPosition():
	 *       |									(component >= 0)
	 */
	public Task(String name, int priority,Statement activitylist, Vector selectedPosition) {
		this.setPriority(priority);
		this.setName(name);
		
		this.statements=activitylist;
		if (activitylist!=null) {
			this.statements.addToTask(this);
		}
		if (canHaveAsSelectedPosition(selectedPosition))
			this.selectedPosition = selectedPosition;
		else {
			double[] components = selectedPosition.toArray();
			for (int i = 0; i < 3; i++){
				if (components[i] < 0)
					components[i] = 0;
			}
			this.selectedPosition = new Vector(components[0], components[1], components[2]);
		}
	}

	/**
	 * Check whether this Task is currently being executed
	 */
	public boolean isBeingExecuted(){
		return inExecution;
	}
	/**
	 * 
	 * @param unit
	 * @effect	if the Unit does not currently have an assigned unit, it will set this task's unit to 
	 * 			the given Unit and the Given Unit will have it's task set to this task
	 * 			|new.getunit==unit
	 * 			|unit.setTask(this)
	 * 			|isBeingexecuted==true
	 * 			|new.getiterator==this.statements.iterator()
	 */
	public void assignToUnit(Unit unit){
		if (this.isBeingExecuted() || unit.getTask() != null)
			throw new IllegalStateException();
		this.inExecution = true;
		this.setUnit(unit);
		unit.assignTask(this);
		this.iterator = this.statements.iterator();
	}
	/**
	 * 
	 * @effect	the Unit's task will be set null and this Task's Unit will also be set to null
	 * 			it will no longer be flagged as being executed and its priority will be reduced by 1
	 * 			|unit.setTask(null)
	 * 			|new.getUnit()==null
	 * 			|this.inexecution==false
	 * 			|new.getPriority()==oldpriority-1
	 * 		
	 */
	public void removeFromUnit()throws IllegalStateException{
		if (!this.isBeingExecuted()) {
			throw new IllegalStateException();
		}
		this.inExecution = false;
		Unit oldUnit = this.getUnit();
		this.setUnit(null);
		oldUnit.removeTask();
		this.setPriority(this.getPriority()-1);
	}

	

	/**
	 * Variable registering whether this Task is currently being executed
	 */
	private boolean inExecution=false;

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
	private void setUnit(Unit unit) 
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
	 * Return the name of this Task.
	 */
	@Basic @Raw
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether the given name is a valid name for
	 * any Task.
	 *  
	 * @param  name
	 *         The name to check.
	 * @return true
	 *       | result == true
	 */
	public static boolean isValidName(String name) {
		return true;
	}

	/**
	 * Set the name of this Task to the given name.
	 * 
	 * @param  name
	 *         The new name for this Task.
	 * @post   The name of this new Task is equal to
	 *         the given name.
	 *       | new.getName() == name
	 * @throws IllegalArgumentException
	 *         The given name is not a valid name for any
	 *         Task.
	 *       | ! isValidName(getName())
	 */
	@Raw
	public void setName(String name) 
			throws IllegalArgumentException {
		if (! isValidName(name))
			throw new IllegalArgumentException();
		this.name = name;
	}

	/**
	 * Variable registering the name of this Task.
	 */
	private String name;

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
	 *       |   !scheduler.isTerminated()
	 */
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler) {
		return (scheduler != null) && (!scheduler.isTerminated());
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
			if (!scheduler.hasAsTask(this))
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
		assert (scheduler != null) && (scheduler.hasAsTask(this));
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
		assert this.hasAsScheduler(scheduler) && (!scheduler.hasAsTask(this));
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


	public Set<Scheduler> getSchedulers() {
		return schedulers;
	}

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
		if (this.getUnit() != null) {
			this.removeFromUnit();
		}
		this.inExecution = false;
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

	/**
	 * Return the Statement of the Task
	 */
	public Statement getstatement(){
		return this.statements;
	}

	public void setStatement(Statement statement){
		this.statements=statement;
	}
	//	public int getNbStatements(){
	//		return this.getstatement().size();
	//	}
	//	
	//	
	//	/**
	//	 * Return an iterator delivering all tasks managed by this Scheduler
	//	 * in order of descending priority.
	//	 */
	//	public Iterator<Statement> iterator(){
	//		
	//		return new Iterator<Statement>(){
	//			
	//			private List<Statement> statements = Task.this.getstatement();
	//			
	//			private int nbItemsHandled = 0;
	//			
	//			@Override
	//			public boolean hasNext() {
	//				return nbItemsHandled < getNbStatements();
	//			}
	//
	//			@Override
	//			public Statement next() {
	//				Statement result = statements.get(nbItemsHandled);
	//				nbItemsHandled += 1;
	//				return result;
	//			}
	//			
	//		};
	//	}
	
	public IExpression getVariableExpression(String name){
		return this.variables.get(name);
	}

	public void storeVariableExpression(String name, IExpression expression){
		this.variables.put(name, expression);
	}

	private HashMap<String,IExpression> variables = new HashMap<>();

	/**
	 * @invar  Each Task can have its selectedPosition as selectedPosition .
	 *       | canHaveAsSelectedPosition(this.getSelectedPosition())
	 */

	/**
	 * Return the selectedPosition of this Task.
	 */
	@Basic @Raw @Immutable
	public Vector getSelectedPosition() {
		return this.selectedPosition;
	}

	/**
	 * Check whether this Task can have the given selectedPosition as its selectedPosition.
	 *  
	 * @param  selectedPosition
	 *         The selectedPosition to check.
	 * @return 
	 *       | result == for each component in selectedPosition.toArray():
	 *       |												(component >= 0)
	 */
	@Raw
	public boolean canHaveAsSelectedPosition(Vector selectedPosition) {
		for (double comp:selectedPosition.toArray())
			if (comp < 0)
				return false;
		return true;
	}

	/**
	 * Variable registering the selectedPosition of this Task.
	 */
	private Vector selectedPosition;

	void advanceTask(double time){
		if (!this.iterator.hasNext()){
			this.removeFromUnit();
			this.terminate();
			return;
		}
		int nbStatements = (int) Math.ceil(time/0.001);
		for (int i = 0; i < nbStatements; i++){
			if (this.getUnit().getStatus() == Status.IDLE)
				try {
					this.iterator.next().execute();
				} catch (Exception e){
//					e.printStackTrace();
					this.removeFromUnit();
					return;
				}
			if (!this.iterator.hasNext())
				break;
		}
	}
	
	/**
	 * Check whether the current Task has been completed.
	 * @return true if and only if this Task's iterator has no next Statement
	 * 		  | result == !(this.iterator.hasNext())
	 */
	public boolean isFinished(){
		return !this.iterator.hasNext();
	}

	/**
	 * Variable registering the Statement of this Task.
	 */
	private Statement statements;
	
	/**
	 * variable registering the iterator this task is using
	 */
	private Iterator<IExecutableStatement> iterator;
	
	
	public boolean wellformed(){
		return this.statements.isWellFormed(new HashSet<String>());
//		Set<String> assignedVariables = new HashSet<>();
//		for (Statement statement : this.getstatement().getStatements()) {
//			if (statement instanceof BreakStatement) {
//				if (!statement.checkIfInWhile()) {
//					return false;
//				}
//			}
//			if (statement instanceof AssignmentStatement) {
//				if (!assignedVariables.contains(((AssignmentStatement) statement).getVariableName()))
//					assignedVariables.add(((AssignmentStatement) statement).getVariableName());
//			}
//			if (statement instanceof )
//		}
//	
//	return true;
		
	}
	
		
	

}
