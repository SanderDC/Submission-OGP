package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class IsPassableBoolean extends PositionBoolean {

	public IsPassableBoolean(IPositionExpression position, SourceLocation sourceLocation) {
		super(position, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return this.getUnit().getWorld().isSolidGround(this.getTarget().evaluate());
	}
	
	@Override
	public IsPassableBoolean clone(){
		return new IsPassableBoolean(getTarget().clone(), getSourceLocation());
	}

}
