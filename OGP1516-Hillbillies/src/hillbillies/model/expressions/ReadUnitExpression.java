package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class ReadUnitExpression extends ReadVariable implements UnitExpression {

	public ReadUnitExpression(String variableName, SourceLocation sourceLocation) {
		super(variableName, sourceLocation);
	}

	@Override
	public Unit evaluate() throws NoSuchElementException {
		return ((UnitExpression) this.getExpression()).evaluate();
	}

}
