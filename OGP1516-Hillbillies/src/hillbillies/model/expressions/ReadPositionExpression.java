package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Vector;
import hillbillies.part3.programs.SourceLocation;

public class ReadPositionExpression extends ReadVariable implements IPositionExpression {

	public ReadPositionExpression(String variableName, SourceLocation sourceLocation) {
		super(variableName, sourceLocation);
	}

	@Override
	public Vector evaluate() throws NoSuchElementException {
		return ((IPositionExpression) this.getExpression()).evaluate();
	}
	
	@Override
	public ReadPositionExpression clone(){
		return new ReadPositionExpression(getVariableName(), getSourceLocation());
	}

}
