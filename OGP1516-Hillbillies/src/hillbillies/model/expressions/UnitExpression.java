package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Task;
import hillbillies.model.Unit;

public interface UnitExpression {

	public abstract Unit evaluate() throws NoSuchElementException;
	
}
