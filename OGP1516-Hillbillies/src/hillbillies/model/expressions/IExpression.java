package hillbillies.model.expressions;

import java.util.Set;

import hillbillies.model.Task;

public interface IExpression {
	
	public void addToTask(Task task);
	
	public Object evaluate();
	
	public IExpression clone();
	
	public default boolean isWellFormed(Set<String> variables) {
		return true;
	}
}
