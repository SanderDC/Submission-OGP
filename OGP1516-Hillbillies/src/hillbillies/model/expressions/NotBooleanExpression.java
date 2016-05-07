package hillbillies.model.expressions;

public class NotBooleanExpression extends Expression implements BooleanExpression {

	public NotBooleanExpression(BooleanExpression expression) {
		this.target = expression;
	}

	@Override
	public boolean evaluate() {
		return !(this.getTarget().evaluate());
	}
	
	protected BooleanExpression getTarget(){
		return this.target;
	}
	
	private BooleanExpression target;
}
