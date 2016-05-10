package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.Vector;
import hillbillies.model.expressions.AnyUnitExpression;

public class FollowStatement extends Statement {
	
	public FollowStatement( AnyUnitExpression expression){
		this.expression=expression;
	}
	private AnyUnitExpression expression;

	private Vector getfollowPositition(){
	return	expression.evaluate().getPosition();
	}
	public void execute(){
		this.getUnit().moveTo(getfollowPositition().getCubeX(),getfollowPositition().getCubeY(),getfollowPositition().getCubeZ());
	}
	@Override
	public void addToTask(Task task) {
		task.addStatement(this);
		this.setTask(task);
		
	}
}
