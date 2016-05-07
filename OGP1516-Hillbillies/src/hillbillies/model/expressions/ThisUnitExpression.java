package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class ThisUnitExpression extends Expression implements UnitExpression {

	public ThisUnitExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate() throws NoSuchElementException {
		return this.getUnit();
	}

}
