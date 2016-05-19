package hillbillies.model.expressions;

import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.Vector;
import hillbillies.part3.programs.SourceLocation;

public class SelectedPositionExpression extends Expression implements IPositionExpression {

	public SelectedPositionExpression(SourceLocation sourceLocation) throws IllegalArgumentException {
		super(sourceLocation);
	}

	@Override
	public Vector evaluate() throws NoSuchElementException {
		if (this.getTask().getSelectedPosition() == null)
			throw new NoSuchElementException();
		else 
			return this.getTask().getSelectedPosition();
	}

	@Override
	public SelectedPositionExpression clone() {
		return new SelectedPositionExpression(getSourceLocation());
	}

	@Override
	public boolean isWellFormed(Set<String> variables) {
		return (this.getTask().getSelectedPosition() != null);
	}

}
