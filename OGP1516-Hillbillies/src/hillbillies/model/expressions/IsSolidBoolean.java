package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class IsSolidBoolean extends PositionBoolean {
	
	public IsSolidBoolean(PositionExpression position, SourceLocation sourceLocation) {
		super(position, sourceLocation);
	}

	@Override
	public boolean evaluate() {
		return this.getUnit().getWorld().isSolidGround(this.getTarget().evaluate());
	}
	
}
