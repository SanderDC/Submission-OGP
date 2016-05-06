package hillbillies.model.expressions;

public class IsSolidBoolean extends PositionBoolean {
	
	public IsSolidBoolean(PositionExpression position) {
		super(position);
	}

	@Override
	public boolean evaluate() {
		return this.getUnit().getWorld().isSolidGround(this.getTarget().evaluate());
	}
	
}
