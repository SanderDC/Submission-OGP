package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class FalseBooleanExpression extends Expression implements IBooleanExpression {

	public FalseBooleanExpression(SourceLocation sourceLocation) throws IllegalArgumentException {
		super(sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return false;
	}
	
	@Override
	public FalseBooleanExpression clone(){
		return new FalseBooleanExpression(getSourceLocation());
	}

}
