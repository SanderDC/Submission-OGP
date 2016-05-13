package hillbillies.model.statements;

import java.util.Iterator;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Task;
import hillbillies.model.Unit;

/**
 * 
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 */
public abstract class Statement implements Iterable<Statement> {

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
	
	private boolean executed=false;
	
	public boolean getexecuted () {
		return this.executed;
	}
	public void setExecuted(boolean e){
		this.executed=e;
	}
	public abstract boolean check();
	protected Statement getParentStatement(){
		return this.parentStatement;
	}
	
	protected void setParentStatement(Statement statement){
		this.parentStatement = statement;
	}
	
	public abstract Iterator<Statement> iterator();
	
	private Statement parentStatement;
	
	public abstract void execute();
	
	public abstract Statement clone();	
}
