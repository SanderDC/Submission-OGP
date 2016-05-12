package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class FalseBooleanExpression extends Expression implements BooleanExpression {

	public FalseBooleanExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public boolean evaluate() {
		return false;
	}
	
	@Override
	public FalseBooleanExpression clone(){
		return new FalseBooleanExpression(getSourceLocation());
	}

}
