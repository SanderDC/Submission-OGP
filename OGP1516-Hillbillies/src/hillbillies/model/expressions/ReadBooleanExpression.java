package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class ReadBooleanExpression extends ReadVariable implements BooleanExpression {

	public ReadBooleanExpression(String variableName, SourceLocation sourceLocation) {
		super(variableName, sourceLocation);
	}

	@Override
	public boolean evaluate() {
		return ((BooleanExpression) this.getExpression()).evaluate();
	}

}
