package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class AndBooleanExpression extends BinaryBooleanExpression {

	public AndBooleanExpression(IBooleanExpression leftExpression, IBooleanExpression rightExpression, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(leftExpression, rightExpression, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return (this.getLeftExpression().evaluate()) && (this.getRightExpression().evaluate());
	}

	@Override
	public AndBooleanExpression clone() {
		return new AndBooleanExpression(getLeftExpression().clone(), getRightExpression().clone(), getSourceLocation());
	}

}
