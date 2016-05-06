package hillbillies.model.expressions;

import hillbillies.model.Unit;

public class IsEnemyBoolean extends UnitBoolean {

	public IsEnemyBoolean(UnitExpression unit) {
		super(unit);
	}

	@Override
	public boolean evaluate() {
		Unit target = this.getTarget().evaluate();
		if (target.getWorld() != this.getUnit().getWorld())
			return false;
		else
			return !this.getUnit().getFaction().hasAsUnit(target);
	}

}
