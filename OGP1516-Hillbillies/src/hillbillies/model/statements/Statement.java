package hillbillies.model.statements;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Task;
import hillbillies.model.Unit;

/**
 * 
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 */
public abstract class Statement {

	public abstract void addToTask(Task task);

	/**
	 * Return the Task of this Statement.
	 */
	@Basic @Raw
	protected Task getTask() {
		return this.task;
	}
	
	@Raw
	protected void setTask(Task task) {
		this.task = task;
	}

	/**
	 * Variable registering the Task of this Statement.
	 */
	private Task task;	
	
	protected Unit getUnit(){
		return this.getTask().getUnit();
	}
	
	public abstract void execute();
	
	public abstract Statement clone();	
}
