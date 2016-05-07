package hillbillies.model.expressions;

import hillbillies.model.Vector;
import hillbillies.part3.programs.SourceLocation;

public class HerePositionExpression extends Expression implements PositionExpression {

	public HerePositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Vector evaluate() {
		return this.getUnit().getPosition();
	}

}
