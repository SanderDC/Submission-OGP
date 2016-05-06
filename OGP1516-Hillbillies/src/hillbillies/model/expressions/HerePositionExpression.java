package hillbillies.model.expressions;

import hillbillies.model.Vector;

public class HerePositionExpression extends Expression implements PositionExpression {

	@Override
	public Vector evaluate() {
		return this.getUnit().getPosition();
	}

}
