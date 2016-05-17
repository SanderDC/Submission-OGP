package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;

public class BreakStatement  extends Statement implements IExecutableStatement {

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
				return BreakStatement.this;
			}
		};
	}
}
