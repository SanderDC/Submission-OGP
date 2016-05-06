package hillbillies.model.expressions;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Task;
import hillbillies.model.Unit;

public abstract class UnitExpression extends Expression {

	public abstract Unit evaluate();
	
}
