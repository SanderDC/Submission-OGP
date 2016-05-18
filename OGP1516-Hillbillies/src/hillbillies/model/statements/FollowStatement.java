package hillbillies.model.statements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.Task;
import hillbillies.model.Vector;
import hillbillies.model.expressions.IUnitExpression;

public class FollowStatement extends Statement implements IExecutableStatement {

	@Override
	public boolean isWellFormed(Set<String> variables) {
		return this.expression.isWellFormed(variables);
	}

	public FollowStatement( IUnitExpression expression){
		this.expression=expression;
	}

	private IUnitExpression expression;

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
	@Override
	public List<Statement> getStatements() {
		List<Statement>list=new ArrayList<Statement>();
		list.add(this);
		return list;
	}

}
