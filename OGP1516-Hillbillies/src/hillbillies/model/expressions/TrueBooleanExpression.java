package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class TrueBooleanExpression extends Expression implements IBooleanExpression {

	public TrueBooleanExpression(SourceLocation sourceLocation) throws IllegalArgumentException {
		super(sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return true;
	}
	
	@Override
	public TrueBooleanExpression clone(){
		return new TrueBooleanExpression(getSourceLocation());
	}

}
