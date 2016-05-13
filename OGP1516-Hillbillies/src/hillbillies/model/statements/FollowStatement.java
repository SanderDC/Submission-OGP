package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
		boolean hasPathed=false;
		while (!expression.evaluate().isTerminated()||this.getUnit().isAdjacentPosition(expression.evaluate().getPosition())) {
			if (!hasPathed) {
				this.getUnit().moveTo(getfollowPositition().getCubeX(),getfollowPositition().getCubeY(),getfollowPositition().getCubeZ());

			}
			else {
				if (expression.evaluate().isMoving()) {
					this.getUnit().moveTo(getfollowPositition().getCubeX(),getfollowPositition().getCubeY(),getfollowPositition().getCubeZ());

				}

			}

		}

	}
	
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.expression.addToTask(task);

	}

	@Override
	public FollowStatement clone() {
		return new FollowStatement(expression.clone());
	}

	@Override
	public Iterator<Statement> iterator() {
		return new Iterator<Statement>(){
			
			private boolean statementHandled = false;

			@Override
			public boolean hasNext() {
				if (!statementHandled)
					return true;
				if (getUnit().isAdjacentPosition(expression.evaluate().getPosition()))
					return false;
				return true;
			}

			@Override
			public Statement next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				return FollowStatement.this;
			}
			
		};
	}
	
}
