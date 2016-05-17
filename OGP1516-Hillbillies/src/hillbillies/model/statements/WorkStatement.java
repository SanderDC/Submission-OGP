package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.LiteralPositionExpression;
import hillbillies.model.expressions.PositionExpression;

public class WorkStatement extends Statement implements ExecutableStatement {
	
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
	public boolean check() {
		if ( !this.getUnit().isWorking()) {
			this.setExecuted(true);
			return true;
		}
		else
			return false;
			
		
	}
	@Override
	public Statement clone() {
		return new WorkStatement(expression.clone());
	}

	@Override
	public Iterator<ExecutableStatement> iterator() {
		return new Iterator<ExecutableStatement>(){

			private boolean statementHandled = false;

			@Override
			public boolean hasNext() {
				return !statementHandled;
			}

			@Override
			public ExecutableStatement next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				statementHandled = true;
				return WorkStatement.this;
			}
		};
	}
}
