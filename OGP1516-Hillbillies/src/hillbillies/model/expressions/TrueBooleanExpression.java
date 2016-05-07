package hillbillies.model.expressions;

public class TrueBooleanExpression extends Expression implements BooleanExpression {

	@Override
	public boolean evaluate() {
		return true;
	}

}
