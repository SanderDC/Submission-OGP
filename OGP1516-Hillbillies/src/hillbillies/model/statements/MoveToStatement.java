package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.LiteralPositionExpression;
import hillbillies.model.expressions.PositionExpression;
import javafx.geometry.Pos;

public class MoveToStatement extends Statement {
	
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
	public boolean check() {
		if (expression.evaluate().equals(getUnit().getPosition())) {
			this.setExecuted(true);
			return true;
		}
		return false;
	}
	
	

	@Override
	public MoveToStatement clone() {
		return new MoveToStatement(expression.clone());
	}

	@Override
	public Iterator<Statement> iterator() {
		return new Iterator<Statement>(){

			private boolean statementHandled = false;

			@Override
			public boolean hasNext() {
				return !statementHandled;
			}

			@Override
			public Statement next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				statementHandled = true;
				return MoveToStatement.this;
			}
		};
	}
}
