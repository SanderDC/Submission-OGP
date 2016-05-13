package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.LiteralPositionExpression;
import hillbillies.model.expressions.PositionExpression;
import javafx.geometry.Pos;

public class MoveToStatement extends Statement {
	public MoveToStatement(PositionExpression expression) {
		this.expression=expression;
	}
	private PositionExpression expression;
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.expression.addToTask(task);
		
	}
	@Override
	public void execute(){
		this.getUnit().moveTo(expression.evaluate().getCubeX(),expression.evaluate().getCubeY(),expression.evaluate().getCubeZ());
	}
	@Override
	public boolean check() {
		if (expression.evaluate().equals(getUnit().getPosition())) {
			this.setExecuted(true);
			return true;
		}
		return false;
	}
	
	
}
