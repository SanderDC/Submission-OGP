package hillbillies.model.expressions;

public class IsAliveBoolean extends UnitBoolean {

	public IsAliveBoolean(UnitExpression unit) {
		super(unit);
	}

	@Override
	public boolean evaluate() {
		return (!this.getTarget().evaluate().isTerminated());
	}

}
