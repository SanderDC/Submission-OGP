package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.Vector;
import hillbillies.model.expressions.UnitExpression;

public class FollowStatement extends Statement implements IExecutableStatement {

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
	public FollowStatement clone() {
		return new FollowStatement(expression.clone());
	}

	@Override
	public Iterator<IExecutableStatement> iterator() {
		return new Iterator<IExecutableStatement>(){

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
			public IExecutableStatement next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				statementHandled = true;
				return FollowStatement.this;
			}

		};
	}

}
