package hillbillies.model.expressions;

public class IsFriendBoolean extends UnitBoolean {

	public IsFriendBoolean(UnitExpression unit) {
		super(unit);
	}

	@Override
	public boolean evaluate() {
		return this.getUnit().getFaction().hasAsUnit(this.getTarget().evaluate());
	}

}
