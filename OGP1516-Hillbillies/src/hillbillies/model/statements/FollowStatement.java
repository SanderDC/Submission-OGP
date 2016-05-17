package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.Vector;
import hillbillies.model.expressions.UnitExpression;

public class FollowStatement extends Statement {

	public FollowStatement( UnitExpression expression){
		this.expression=expression;
	}

	private UnitExpression expression;

	private Vector getfollowPositition(){
		Vector position = expression.evaluate().getPosition();
		return	position;
	}

	public void execute(){
		Vector position = this.getfollowPositition();
		this.getUnit().moveTo(position.getCubeX(),position.getCubeY(),position.getCubeZ());
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
				statementHandled = true;
				return FollowStatement.this;
			}

		};
	}

}
