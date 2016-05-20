package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class ReadBooleanExpression extends ReadVariable implements IBooleanExpression {

	public ReadBooleanExpression(String variableName, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(variableName, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		assert this.getVariableValue() instanceof Boolean;
		return (Boolean) this.getVariableValue();
	}
	
	@Override
	public ReadBooleanExpression clone(){
		return new ReadBooleanExpression(getVariableName(), getSourceLocation());
	}

}
