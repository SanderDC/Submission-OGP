package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Vector;
import hillbillies.part3.programs.SourceLocation;

public class ReadPositionExpression extends ReadVariable implements IPositionExpression {

	public ReadPositionExpression(String variableName, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(variableName, sourceLocation);
	}

	@Override
	public Vector evaluate() throws NoSuchElementException {
		assert this.getVariableValue() instanceof Vector;
		return (Vector) this.getVariableValue();
	}
	
	@Override
	public ReadPositionExpression clone(){
		return new ReadPositionExpression(getVariableName(), getSourceLocation());
	}

}
