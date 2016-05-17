package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class IsSolidBoolean extends PositionBoolean {
	
	public IsSolidBoolean(PositionExpression position, SourceLocation sourceLocation) {
		super(position, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return this.getUnit().getWorld().isSolidGround(this.getTarget().evaluate());
	}
	
	@Override
	public IsSolidBoolean clone(){
		return new IsSolidBoolean(getTarget().clone(), getSourceLocation());
	}
	
	
}
