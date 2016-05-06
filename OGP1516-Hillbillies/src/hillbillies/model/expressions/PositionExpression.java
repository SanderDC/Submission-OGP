package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Vector;

public abstract class PositionExpression extends Expression {
	
	public abstract Vector evaluate() throws NoSuchElementException;
	
}
