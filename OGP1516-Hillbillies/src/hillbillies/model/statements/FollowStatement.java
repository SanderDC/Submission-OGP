package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.Vector;
import hillbillies.model.expressions.AnyUnitExpression;
import hillbillies.model.expressions.UnitExpression;

public class FollowStatement extends Statement {
	
	public FollowStatement( UnitExpression expression){
		this.expression=expression;
	}
	private UnitExpression expression;

	private Vector getfollowPositition(){
	return	expression.evaluate().getPosition();
	}
	public void execute(){
				this.getUnit().moveTo(getfollowPositition().getCubeX(),getfollowPositition().getCubeY(),getfollowPositition().getCubeZ());
			}
			
			
			
		
		
	
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.expression.addToTask(task);
		
	}
	@Override
	public boolean check() {
		
		if( this.getUnit().isAdjacentPosition(expression.evaluate().getPosition())){
			this.setExecuted(true);
			return true;}
		else{
			if (expression.evaluate().isMoving()||expression.evaluate().isFalling()) {
				this.getUnit().moveTo(getfollowPositition().getCubeX(),getfollowPositition().getCubeY(),getfollowPositition().getCubeZ());
				
			}
			return false;
			
		}
	}
}
