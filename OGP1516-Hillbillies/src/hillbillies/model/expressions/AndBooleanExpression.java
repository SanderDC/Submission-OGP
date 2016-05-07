package hillbillies.model.expressions;

public class AndBooleanExpression extends BinaryBooleanExpression {

	public AndBooleanExpression(BooleanExpression leftExpression, BooleanExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	@Override
	public boolean evaluate() {
		return (this.getLeftExpression().evaluate()) && (this.getRightExpression().evaluate());
	}

}
