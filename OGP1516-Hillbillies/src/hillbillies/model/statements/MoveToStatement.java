package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.LiteralPositionExpression;
import hillbillies.model.expressions.PositionExpression;
import javafx.geometry.Pos;

public class MoveToStatement extends Statement implements ExecutableStatement {
	
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
		if (this.getUnit().getPosition().getCubeX()==expression.evaluate().getCubeX()&&this.getUnit().getPosition().getCubeY()==expression.evaluate().getCubeY()&&this.getUnit().getPosition().getCubeZ()==expression.evaluate().getCubeZ()) {
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
	public Iterator<ExecutableStatement> iterator() {
		return new Iterator<ExecutableStatement>(){

			private boolean statementHandled = false;

			@Override
			public boolean hasNext() {
				return !statementHandled || !getUnit().getPosition().getCubePosition().equals(expression.evaluate().getCubePosition());
			}

			@Override
			public ExecutableStatement next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				statementHandled = true;
				return MoveToStatement.this;
			}
		};
	}
}
