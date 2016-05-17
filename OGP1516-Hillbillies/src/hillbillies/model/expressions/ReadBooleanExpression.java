package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class ReadBooleanExpression extends ReadVariable implements IBooleanExpression {

	public ReadBooleanExpression(String variableName, SourceLocation sourceLocation) {
		super(variableName, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return ((IBooleanExpression) this.getExpression()).evaluate();
	}
	
	@Override
	public ReadBooleanExpression clone(){
		return new ReadBooleanExpression(getVariableName(), getSourceLocation());
	}

}
