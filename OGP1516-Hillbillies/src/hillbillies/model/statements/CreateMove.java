package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.LiteralPositionExpression;
import hillbillies.model.expressions.PositionExpression;
import javafx.geometry.Pos;

public class CreateMove extends Statement {
	public CreateMove(PositionExpression expression) {
		this.expression=expression;
	}
	private PositionExpression expression;
	@Override
	public void addToTask(Task task) {
		task.addStatement(this);
		this.setTask(task);
		this.expression.addToTask(task);
		
	}
	@Override
	public void execute(){
		this.getUnit().moveTo(expression.evaluate().getCubeX(),expression.evaluate().getCubeY(),expression.evaluate().getCubeZ());
	}
	
	
}
