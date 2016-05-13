package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.Expression;

public class AssignmentStatement extends Statement {
	
	public AssignmentStatement(Expression expression, String variableName) {
		this.expression = expression;
		this.variableName = variableName;
	}
	
	private String variableName;
	
	private Expression expression;

	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.expression.addToTask(task);
	}

	@Override
	public void execute() {
		this.getTask().storeVariableExpression(variableName, expression);
	}

	@Override
	public Statement clone() {
		return new AssignmentStatement(expression.clone(), variableName);
	}

}
