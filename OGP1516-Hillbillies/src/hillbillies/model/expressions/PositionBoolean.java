package hillbillies.model.expressions;

import java.util.Set;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

public abstract class PositionBoolean extends Expression implements IBooleanExpression {

	public PositionBoolean(IPositionExpression position, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(sourceLocation);
		if (position == null)
			throw new IllegalArgumentException();
		this.target = position;
	}
	
	protected IPositionExpression getTarget(){
		return this.target;
	}
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		target.addToTask(task);
		
	}
	private final IPositionExpression target;
	
	public abstract PositionBoolean clone();

	@Override
	public boolean isWellFormed(Set<String> variables) {
		return this.target.isWellFormed(variables);
	}
}
