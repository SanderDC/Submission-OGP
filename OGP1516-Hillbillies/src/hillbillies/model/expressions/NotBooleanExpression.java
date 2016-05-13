package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class NotBooleanExpression extends Expression implements BooleanExpression {

	public NotBooleanExpression(BooleanExpression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.target = expression;
	}

	@Override
	public Boolean evaluate() {
		return !(this.getTarget().evaluate());
	}
	
	protected BooleanExpression getTarget(){
		return this.target;
	}
	
	private BooleanExpression target;
	
	@Override
	public NotBooleanExpression clone(){
		return new NotBooleanExpression(getTarget().clone(), getSourceLocation());
	}
}
