package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class ReadBooleanExpression extends ReadVariable implements BooleanExpression {

	public ReadBooleanExpression(String variableName, SourceLocation sourceLocation) {
		super(variableName, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return ((BooleanExpression) this.getExpression()).evaluate();
	}
	
	@Override
	public ReadBooleanExpression clone(){
		return new ReadBooleanExpression(getVariableName(), getSourceLocation());
	}

}
