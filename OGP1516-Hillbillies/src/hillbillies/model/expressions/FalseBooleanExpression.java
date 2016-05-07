package hillbillies.model.expressions;

public class FalseBooleanExpression extends Expression implements BooleanExpression {

	@Override
	public boolean evaluate() {
		return false;
	}

}
