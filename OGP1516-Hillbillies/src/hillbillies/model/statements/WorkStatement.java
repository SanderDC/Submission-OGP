package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.LiteralPositionExpression;
import hillbillies.model.expressions.PositionExpression;

public class WorkStatement extends Statement{
	public WorkStatement(PositionExpression expression){
	this.expression=expression;
}
	private PositionExpression expression;
	
	
	@Override
	public void execute(){
		this.getUnit().WorkAt(expression.evaluate().getCubeX(),expression.evaluate().getCubeY(),expression.evaluate().getCubeZ());
	}


	
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.expression.addToTask(task);
		
	}



	@Override
	public boolean check() {
		if ( !this.getUnit().isWorking()) {
			this.setExecuted(true);
			return true;
		}
		else
			return false;
			
		
	}
	}