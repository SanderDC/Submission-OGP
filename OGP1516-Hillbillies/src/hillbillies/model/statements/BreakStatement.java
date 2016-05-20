package hillbillies.model.statements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

public class BreakStatement  extends Statement implements IExecutableStatement {

	public BreakStatement(SourceLocation sourceLocation) throws IllegalArgumentException {
		super(sourceLocation);
	}

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
		return new BreakStatement(getSourceLocation());
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
	
	@Override
	public List<Statement> getStatements() {
		List<Statement>list=new ArrayList<Statement>();
		list.add(this);
		return list;
	}

	@Override
	public boolean isWellFormed(Set<String> variables) {
		return this.checkIfInWhile();
	}
}
