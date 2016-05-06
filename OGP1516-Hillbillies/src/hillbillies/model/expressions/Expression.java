package hillbillies.model.expressions;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Task;

/**
 * 
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 */
public abstract class Expression {
	
	@Basic @Raw
	public Task getTask(){
		return this.task;
	}
	
	@Raw
	public void setTask(Task task){
		this.task = task;
	}

	private Task task;	
}
