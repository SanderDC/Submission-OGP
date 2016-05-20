package hillbillies.model.expressions;

import java.util.Set;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

public abstract class UnitBoolean extends Expression implements IBooleanExpression {
	
	public UnitBoolean(IUnitExpression unit, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(sourceLocation);
		if (unit == null)
			throw new IllegalArgumentException();
		this.target = unit;
	}
	
	protected IUnitExpression getTarget(){
		return this.target;
	}
	
	private final IUnitExpression target;
	
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		target.addToTask(task);
	}
	
	public abstract UnitBoolean clone();

	@Override
	public boolean isWellFormed(Set<String> variables) {
		return this.target.isWellFormed(variables);
	}
}
