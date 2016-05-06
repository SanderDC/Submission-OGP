package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Vector;

public interface PositionExpression {
	
	public abstract Vector evaluate() throws NoSuchElementException;
	
}
