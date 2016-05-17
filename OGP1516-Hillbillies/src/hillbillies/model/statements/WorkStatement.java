package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.PositionExpression;

public class WorkStatement extends Statement implements IExecutableStatement {
	
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
	public Statement clone() {
		return new WorkStatement(expression.clone());
	}

	@Override
	public Iterator<IExecutableStatement> iterator() {
		return new Iterator<IExecutableStatement>(){

			private boolean statementHandled = false;

			@Override
			public boolean hasNext() {
				return !statementHandled;
			}

			@Override
			public IExecutableStatement next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				statementHandled = true;
				return WorkStatement.this;
			}
		};
	}
}
