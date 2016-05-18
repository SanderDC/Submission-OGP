package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.Vector;

public interface IPositionExpression extends IExpression {
	
	public Vector evaluate() throws NoSuchElementException;
		
	public IPositionExpression clone();
}
