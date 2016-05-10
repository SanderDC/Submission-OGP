package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.Expression;

public class CreatePrintStatement extends Statement {
	
	public  CreatePrintStatement(Expression value) {
		this.expression=value;
	}
	private Expression expression;
	@Override
	public void addToTask(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		System.out.println(expression.evaluate); 
		
	}
	
		
}
