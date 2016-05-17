package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class IsAliveBoolean extends UnitBoolean {

	public IsAliveBoolean(IUnitExpression unit, SourceLocation sourceLocation) {
		super(unit, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return (!this.getTarget().evaluate().isTerminated());
	}
	
	@Override
	public IsAliveBoolean clone(){
		return new IsAliveBoolean(getTarget().clone(), getSourceLocation());
	}
}
