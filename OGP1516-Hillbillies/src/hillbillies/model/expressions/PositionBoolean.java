package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public abstract class PositionBoolean extends Expression implements BooleanExpression {

	public PositionBoolean(PositionExpression position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.target = position;
	}
	
	protected PositionExpression getTarget(){
		return this.target;
	}
	
	private final PositionExpression target;
	
	public abstract PositionBoolean clone();
}
