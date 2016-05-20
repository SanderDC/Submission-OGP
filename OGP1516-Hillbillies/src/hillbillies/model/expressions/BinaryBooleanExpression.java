package hillbillies.model.expressions;

import java.util.Set;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

public abstract class BinaryBooleanExpression extends Expression implements IBooleanExpression {

	public BinaryBooleanExpression(IBooleanExpression leftExpression, IBooleanExpression rightExpression, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(sourceLocation);
		if (leftExpression == null || rightExpression == null)
			throw new IllegalArgumentException();
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	protected IBooleanExpression getLeftExpression(){
		return this.leftExpression;
	}
	
	private final IBooleanExpression leftExpression;
	
	protected IBooleanExpression getRightExpression(){
		return this.rightExpression;
	}
	
	private final IBooleanExpression rightExpression;
	
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.leftExpression.addToTask(task);
		this.rightExpression.addToTask(task);
	}
	
	public abstract BinaryBooleanExpression clone();
	
	public boolean isWellFormed(Set<String> variables){
		return (this.leftExpression.isWellFormed(variables) && this.rightExpression.isWellFormed(variables));
	}
}
