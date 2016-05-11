package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Vector;
import hillbillies.part3.programs.SourceLocation;

public class ReadPositionExpression extends ReadVariable implements PositionExpression {

	public ReadPositionExpression(String variableName, SourceLocation sourceLocation) {
		super(variableName, sourceLocation);
	}

	@Override
	public Vector evaluate() throws NoSuchElementException {
		return ((PositionExpression) this.getExpression()).evaluate();
	}

}
