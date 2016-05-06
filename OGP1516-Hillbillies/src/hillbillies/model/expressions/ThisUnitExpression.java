package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Unit;

public class ThisUnitExpression extends Expression implements UnitExpression {

//	public ThisUnitExpression(Unit unit) throws IllegalArgumentException {
//		super(unit);
//	}

	@Override
	public Unit evaluate() throws NoSuchElementException {
		return this.getUnit();
	}

}
