package hillbillies.model.expressions;

import hillbillies.model.Task;

public interface IBooleanExpression{
	
	public Boolean evaluate();
	
	public void addToTask(Task task);
	
	public IBooleanExpression clone();

}
