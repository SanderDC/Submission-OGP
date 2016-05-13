package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class IsFriendBoolean extends UnitBoolean {

	public IsFriendBoolean(UnitExpression unit, SourceLocation sourceLocation) {
		super(unit, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return this.getUnit().getFaction().hasAsUnit(this.getTarget().evaluate());
	}
	
	@Override
	public IsFriendBoolean clone(){
		return new IsFriendBoolean(getTarget().clone(), getSourceLocation());
	}

}
