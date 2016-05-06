package hillbillies.model.expressions;

public abstract class PositionBoolean extends Expression implements BooleanExpression {

	public PositionBoolean(PositionExpression position) {
		this.target = position;
	}
	
	protected PositionExpression getTarget(){
		return this.target;
	}
	
	private final PositionExpression target;
}
