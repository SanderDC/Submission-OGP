package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class ReadUnitExpression extends ReadVariable implements IUnitExpression {

	public ReadUnitExpression(String variableName, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(variableName, sourceLocation);
	}

	@Override
	public Unit evaluate() throws NoSuchElementException {
		assert this.getVariableValue() instanceof Unit;
		return (Unit) this.getVariableValue();
	}
	
	@Override
	public ReadUnitExpression clone(){
		return new ReadUnitExpression(getVariableName(), getSourceLocation());
	}

}
