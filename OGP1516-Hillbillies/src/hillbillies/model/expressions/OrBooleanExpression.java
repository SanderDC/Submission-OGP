package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class OrBooleanExpression extends BinaryBooleanExpression {

	public OrBooleanExpression(BooleanExpression leftExpression, BooleanExpression rightExpression, SourceLocation sourceLocation) {
		super(leftExpression, rightExpression, sourceLocation);
	}

	@Override
	public boolean evaluate() {
		return (this.getLeftExpression().evaluate()) || (this.getRightExpression().evaluate());
	}

}
