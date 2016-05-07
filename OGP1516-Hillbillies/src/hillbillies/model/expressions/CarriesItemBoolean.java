package hillbillies.model.expressions;

import hillbillies.model.Unit;

public class CarriesItemBoolean extends UnitBoolean {

	public CarriesItemBoolean(UnitExpression unit) {
		super(unit);
	}

	@Override
	public boolean evaluate() {
		Unit unit = this.getTarget().evaluate();
		return unit.isCarryingLog() || unit.isCarryingBoulder();
	}

}
