package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

public abstract class UnitBoolean extends Expression implements BooleanExpression {
	
	public UnitBoolean(UnitExpression unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.target = unit;
	}
	
	protected UnitExpression getTarget(){
		return this.target;
	}
	
	private final UnitExpression target;
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		target.addToTask(task);
		
	}
	public abstract UnitBoolean clone();
}
