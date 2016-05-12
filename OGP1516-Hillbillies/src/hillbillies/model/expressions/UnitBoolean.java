package hillbillies.model.expressions;

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
	
	public abstract UnitBoolean clone();
}
