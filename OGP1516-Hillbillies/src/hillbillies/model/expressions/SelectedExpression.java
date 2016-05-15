package hillbillies.model.expressions;

import hillbillies.model.Vector;
import hillbillies.part3.programs.SourceLocation;

public class SelectedExpression extends Expression implements PositionExpression {

	public SelectedExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector evaluate() {
		return null;
	}
	
	@Override
	public LiteralPositionExpression clone() {
		return new LiteralPositionExpression(x, y, z, sourceLocation);
	}

}
