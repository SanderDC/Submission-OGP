package hillbillies.model.expressions;

import hillbillies.model.Task;

public interface BooleanExpression{
	
	public Boolean evaluate();
	
	public void addToTask(Task task);
	
	public BooleanExpression clone();

}
