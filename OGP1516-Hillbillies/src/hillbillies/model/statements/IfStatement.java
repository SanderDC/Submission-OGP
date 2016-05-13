package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.BooleanExpression;
import hillbillies.model.expressions.Expression;

public class IfStatement extends Statement {

	public  IfStatement(BooleanExpression expression, Statement lStatement, Statement rStatement) {
		this.expression=expression;
		this.trueStatement=lStatement;
		this.falseStatement=rStatement;
	}


	private BooleanExpression expression;

	private Statement trueStatement;

	private Statement falseStatement;

	@Override
	public void execute(){
		if (expression.evaluate()) {
			trueStatement.execute();
		}
		else {
			falseStatement.execute();
		}
	}


	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.expression.addToTask(task);
		this.trueStatement.addToTask(task);
		this.falseStatement.addToTask(task);

	}


	@Override
	public IfStatement clone() {
		return new IfStatement(expression.clone(), trueStatement.clone(), falseStatement.clone());
	}

}
