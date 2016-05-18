package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.Unit;

public interface IUnitExpression extends IExpression {

	public Unit evaluate() throws NoSuchElementException;
		
	public IUnitExpression clone();
}
