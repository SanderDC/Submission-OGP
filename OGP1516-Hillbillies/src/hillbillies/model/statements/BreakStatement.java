package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;

public class BreakStatement  extends Statement{

	@Override
	public void addToTask(Task task) {
		this.setTask(task);
	}

	@Override
	public void execute() {

	}

	@Override
	public BreakStatement clone() {
		return new BreakStatement();
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
				return BreakStatement.this;
			}
		};
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

}
