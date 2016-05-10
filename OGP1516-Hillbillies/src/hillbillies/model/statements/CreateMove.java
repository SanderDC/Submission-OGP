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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void execute(){
		this.getUnit().moveTo(expression.evaluate().getCubeX(),expression.evaluate().getCubeY(),expression.evaluate().getCubeZ());
	}
	
	
}
