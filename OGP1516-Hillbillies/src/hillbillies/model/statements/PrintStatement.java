package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.Expression;
import hillbillies.model.expressions.ReadVariable;

public class PrintStatement extends Statement {

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
				return PrintStatement.this;
			}
		};
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}
}
