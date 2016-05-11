package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.BooleanExpression;
import hillbillies.model.expressions.Expression;

public class CreateIf extends Statement {
	public  CreateIf(BooleanExpression expression, Statement lStatement, Statement rStatement) {
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
			task.addStatement(this);
			this.setTask(task);
			this.expression.addToTask(task);
			this.trueStatement.setTask(task);
			this.falseStatement.setTask(task);
			
		}
}
