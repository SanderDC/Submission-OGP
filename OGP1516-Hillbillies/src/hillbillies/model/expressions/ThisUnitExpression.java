package hillbillies.model.expressions;

import hillbillies.model.Unit;

public class ThisUnitExpression extends UnitExpression {

//	public ThisUnitExpression(Unit unit) throws IllegalArgumentException {
//		super(unit);
//	}

	@Override
	public Unit evaluate() {
		return this.getUnit();
	}

}
