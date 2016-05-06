package hillbillies.model.expressions;

import hillbillies.model.Vector;

public class HerePositionExpression extends PositionExpression {

	@Override
	public Vector evaluate() {
		return this.getUnit().getPosition();
	}

}
