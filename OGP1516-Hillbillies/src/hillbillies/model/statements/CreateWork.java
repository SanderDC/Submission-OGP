package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.LiteralPositionExpression;

public class CreateWork extends Statement{
	public CreateWork(LiteralPositionExpression expression){
	this.expression=expression;
}
	private LiteralPositionExpression expression;
	
	
	@Override
	public void execute(){
		this.getUnit().WorkAt(expression.evaluate().getCubeX(),expression.evaluate().getCubeY(),expression.evaluate().getCubeZ());
	}


	@Override
	public void addToTask(Task task) {
		// TODO Auto-generated method stub
		
	}
	}
