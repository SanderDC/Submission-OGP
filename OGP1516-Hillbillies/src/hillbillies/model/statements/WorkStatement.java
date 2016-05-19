package hillbillies.model.statements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.Task;
import hillbillies.model.expressions.IPositionExpression;
import hillbillies.part3.programs.SourceLocation;

public class WorkStatement extends Statement implements IExecutableStatement {
	
	public WorkStatement(IPositionExpression expression, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(sourceLocation);
		if (expression == null)
			throw new IllegalArgumentException();
		this.expression=expression;
	}
	
	private IPositionExpression expression;

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
		return new WorkStatement(expression.clone(), getSourceLocation());
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

	@Override
	public List<Statement> getStatements() {
		List<Statement>list=new ArrayList<Statement>();
		list.add(this);
		return list;
	}

	@Override
	public boolean isWellFormed(Set<String> variables) {
		return this.expression.isWellFormed(variables);
	}
}
