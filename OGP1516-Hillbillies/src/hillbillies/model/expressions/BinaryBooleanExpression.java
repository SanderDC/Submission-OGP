package hillbillies.model.expressions;

public abstract class BinaryBooleanExpression extends Expression implements BooleanExpression {

	public BinaryBooleanExpression(BooleanExpression leftExpression, BooleanExpression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	protected BooleanExpression getLeftExpression(){
		return this.leftExpression;
	}
	
	private BooleanExpression leftExpression;
	
	protected BooleanExpression getRightExpression(){
		return this.rightExpression;
	}
	
	private BooleanExpression rightExpression;
}
