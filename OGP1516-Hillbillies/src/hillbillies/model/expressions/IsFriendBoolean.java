package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class IsFriendBoolean extends UnitBoolean {

	public IsFriendBoolean(UnitExpression unit, SourceLocation sourceLocation) {
		super(unit, sourceLocation);
	}

	@Override
	public boolean evaluate() {
		return this.getUnit().getFaction().hasAsUnit(this.getTarget().evaluate());
	}

}
