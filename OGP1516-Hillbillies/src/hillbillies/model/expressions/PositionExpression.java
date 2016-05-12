package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.Vector;

public interface PositionExpression {
	
	public Vector evaluate() throws NoSuchElementException;
	
	public void addToTask(Task task);
	
	public PositionExpression clone();
}
