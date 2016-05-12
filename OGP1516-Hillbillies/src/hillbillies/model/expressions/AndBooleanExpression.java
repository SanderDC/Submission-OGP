package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class AndBooleanExpression extends BinaryBooleanExpression {

	public AndBooleanExpression(BooleanExpression leftExpression, BooleanExpression rightExpression, SourceLocation sourceLocation) {
		super(leftExpression, rightExpression, sourceLocation);
	}

	@Override
	public boolean evaluate() {
		return (this.getLeftExpression().evaluate()) && (this.getRightExpression().evaluate());
	}

	@Override
	public AndBooleanExpression clone() {
		return new AndBooleanExpression(getLeftExpression().clone(), getRightExpression().clone(), getSourceLocation());
	}

}
