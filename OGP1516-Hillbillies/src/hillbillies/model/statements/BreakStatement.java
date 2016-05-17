package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;

public class BreakStatement  extends Statement implements ExecutableStatement {

	@Override
	public void addToTask(Task task) {
		this.setTask(task);
	}

	@Override
	public void execute() {
		this.getWhileStatement().breakWhile();
	}

	@Override
	public BreakStatement clone() {
		return new BreakStatement();
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
				return BreakStatement.this;
			}
		};
	}

	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		return true;
	}

}
