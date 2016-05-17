package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.Unit;

public interface IUnitExpression {

	public Unit evaluate() throws NoSuchElementException;
	
	public void addToTask(Task task);
	
	public IUnitExpression clone();
}
