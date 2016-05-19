package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class ThisUnitExpression extends Expression implements IUnitExpression {

	public ThisUnitExpression(SourceLocation sourceLocation) throws IllegalArgumentException {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate() throws NoSuchElementException {
		return this.getUnit();
	}
	
	@Override
	public ThisUnitExpression clone(){
		return new ThisUnitExpression(getSourceLocation());
	}
	
	
}
