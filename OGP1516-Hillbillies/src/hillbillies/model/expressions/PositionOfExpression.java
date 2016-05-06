package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Vector;

public class PositionOfExpression extends PositionExpression {
	
	public PositionOfExpression(UnitExpression target) {
		this.target = target;
	}

	@Override
	public Vector evaluate() throws NoSuchElementException {
		return target.evaluate().getPosition();
	}
	
	private final UnitExpression target;
}
