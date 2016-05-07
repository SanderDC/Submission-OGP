package hillbillies.model.expressions;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class CarriesItemBoolean extends UnitBoolean {

	public CarriesItemBoolean(UnitExpression unit, SourceLocation sourceLocation) {
		super(unit, sourceLocation);
	}

	@Override
	public boolean evaluate() {
		Unit unit = this.getTarget().evaluate();
		return unit.isCarryingLog() || unit.isCarryingBoulder();
	}

}
