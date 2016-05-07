package hillbillies.model.expressions;

public class OrBooleanExpression extends BinaryBooleanExpression {

	public OrBooleanExpression(BooleanExpression leftExpression, BooleanExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	@Override
	public boolean evaluate() {
		return (this.getLeftExpression().evaluate()) || (this.getRightExpression().evaluate());
	}

}
