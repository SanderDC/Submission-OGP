package hillbillies.model.expressions;

import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.Task;
import hillbillies.model.Vector;
import hillbillies.part3.programs.SourceLocation;

public class PositionOfExpression extends Expression implements IPositionExpression {
	
	public PositionOfExpression(IUnitExpression target, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(sourceLocation);
		if (target == null)
			throw new IllegalArgumentException();
		this.target = target;
	}

	@Override
	public Vector evaluate() throws NoSuchElementException {
		return target.evaluate().getPosition();
	}
	
	private final IUnitExpression target;
	
	@Override
	public PositionOfExpression clone(){
		return new PositionOfExpression(target.clone(), getSourceLocation());
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
