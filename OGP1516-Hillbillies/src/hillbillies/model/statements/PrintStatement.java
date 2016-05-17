package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.Expression;
import hillbillies.model.expressions.ReadVariable;

public class PrintStatement extends Statement implements ExecutableStatement {

	public  PrintStatement(Expression value) {
		this.expression=value;
	}

	private Expression expression;

	@Override
	public void addToTask(Task task) {
		expression.addToTask(task);
		this.setTask(task);

	}

	private Object getvalue(){
		return expression.evaluate();
	}

	@Override
	public void execute() {
		System.out.println(getvalue()); 

	}

	@Override
	public Statement clone() {
		return new PrintStatement(expression.clone());
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
				return PrintStatement.this;
			}
		};
	}

	@Override
	public boolean check() {
		
		return true;
	}
}
