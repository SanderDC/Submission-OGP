package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class TrueBooleanExpression extends Expression implements BooleanExpression {

	public TrueBooleanExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public boolean evaluate() {
		return true;
	}

}
