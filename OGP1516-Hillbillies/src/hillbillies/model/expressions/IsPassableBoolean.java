package hillbillies.model.expressions;

public class IsPassableBoolean extends PositionBoolean {

	public IsPassableBoolean(PositionExpression position) {
		super(position);
	}

	@Override
	public boolean evaluate() {
		return this.getUnit().getWorld().isSolidGround(this.getTarget().evaluate());
	}

}
