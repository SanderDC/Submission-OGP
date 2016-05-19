package hillbillies.model.expressions;

import java.util.Set;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

public class NotBooleanExpression extends Expression implements IBooleanExpression {

	public NotBooleanExpression(IBooleanExpression expression, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(sourceLocation);
		if (target == null)
			throw new IllegalArgumentException();
		this.target = expression;
	}

	@Override
	public Boolean evaluate() {
		return !(this.getTarget().evaluate());
	}
	
	protected IBooleanExpression getTarget(){
		return this.target;
	}
	
	private IBooleanExpression target;
	
	@Override
	public NotBooleanExpression clone(){
		return new NotBooleanExpression(getTarget().clone(), getSourceLocation());
	}
	
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.target.addToTask(task);
	}

	@Override
	public boolean isWellFormed(Set<String> variables) {
		return this.target.isWellFormed(variables);
	}
}
