package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.Expression;
import hillbillies.model.expressions.ReadVariable;

public class CreatePrintStatement extends Statement {
	
	public  CreatePrintStatement(ReadVariable value) {
		this.expression=value;
	}
	private ReadVariable expression;
	@Override
	public void addToTask(Task task) {
		expression.addToTask(task);
		this.setTask(task);
		
	}
	private double getvalue(){
		return expression.evaluate();
	}

	@Override
	public void execute() {
		System.out.println(getvalue()); 
		
	}
	
		
}
