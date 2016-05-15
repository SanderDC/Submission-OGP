package hillbillies.model.expressions;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

public abstract class PositionBoolean extends Expression implements BooleanExpression {

	public PositionBoolean(PositionExpression position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.target = position;
	}
	
	protected PositionExpression getTarget(){
		return this.target;
	}
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		target.addToTask(task);
		
	}
	private final PositionExpression target;
	
	public abstract PositionBoolean clone();
}
