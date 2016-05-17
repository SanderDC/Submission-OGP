package hillbillies.model.expressions;

import hillbillies.model.Vector;
import hillbillies.part3.programs.SourceLocation;

public class HerePositionExpression extends Expression implements IPositionExpression {

	public HerePositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Vector evaluate() {
		return this.getUnit().getPosition();
	}
	
	@Override
	public HerePositionExpression clone(){
		return new HerePositionExpression(getSourceLocation());
	}

}
