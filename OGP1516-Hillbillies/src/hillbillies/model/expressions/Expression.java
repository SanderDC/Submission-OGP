package hillbillies.model.expressions;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Task;
import hillbillies.model.Unit;

/**
 * 
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 */
public abstract class Expression {
	
	public void addToTask(Task task) {
		this.setTask(task);
	}
	
	@Basic @Raw
	public Task getTask(){
		return this.task;
	}
	
	@Raw
	protected void setTask(Task task){
		this.task = task;
	}

	private Task task;	
	
	protected Unit getUnit(){
		return this.getTask().getUnit();
	}
}
