package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

public abstract class BinaryBooleanExpression extends Expression implements BooleanExpression {

	public BinaryBooleanExpression(BooleanExpression leftExpression, BooleanExpression rightExpression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	protected BooleanExpression getLeftExpression(){
		return this.leftExpression;
	}
	
	private BooleanExpression leftExpression;
	
	protected BooleanExpression getRightExpression(){
		return this.rightExpression;
	}
	
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.leftExpression.addToTask(task);
		this.rightExpression.addToTask(task);
	}
	
	private BooleanExpression rightExpression;
	
	public abstract BinaryBooleanExpression clone();
}
