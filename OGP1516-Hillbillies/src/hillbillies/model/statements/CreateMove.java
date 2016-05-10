package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.LiteralPositionExpression;

public class CreateMove extends Statement {
	public CreateMove(LiteralPositionExpression expression) {
		this.expression=expression;
	}
	private LiteralPositionExpression expression;
	@Override
	public void addToTask(Task task) {
		task.addStatement(this);
		this.setTask(task);
		
	}
	@Override
	public void execute(){
		this.getUnit().moveTo(expression.evaluate().getCubeX(),expression.evaluate().getCubeY(),expression.evaluate().getCubeZ());
	}
	
	
}
