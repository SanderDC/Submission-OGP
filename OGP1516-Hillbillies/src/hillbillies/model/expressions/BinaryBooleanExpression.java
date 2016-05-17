package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

public abstract class BinaryBooleanExpression extends Expression implements IBooleanExpression {

	public BinaryBooleanExpression(IBooleanExpression leftExpression, IBooleanExpression rightExpression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	protected IBooleanExpression getLeftExpression(){
		return this.leftExpression;
	}
	
	private IBooleanExpression leftExpression;
	
	protected IBooleanExpression getRightExpression(){
		return this.rightExpression;
	}
	
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.leftExpression.addToTask(task);
		this.rightExpression.addToTask(task);
	}
	
	private IBooleanExpression rightExpression;
	
	public abstract BinaryBooleanExpression clone();
}
