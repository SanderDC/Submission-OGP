package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.PositionExpression;

public class MoveToStatement extends Statement implements IExecutableStatement {
	
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
	public MoveToStatement clone() {
		return new MoveToStatement(expression.clone());
	}

	@Override
	public Iterator<IExecutableStatement> iterator() {
		return new Iterator<IExecutableStatement>(){

			private boolean statementHandled = false;

			@Override
			public boolean hasNext() {
				return !statementHandled || !getUnit().getPosition().getCubePosition().equals(expression.evaluate().getCubePosition());
			}

			@Override
			public IExecutableStatement next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				statementHandled = true;
				return MoveToStatement.this;
			}
		};
	}
}
