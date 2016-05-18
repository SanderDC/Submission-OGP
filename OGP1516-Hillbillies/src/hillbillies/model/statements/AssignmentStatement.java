package hillbillies.model.statements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.Expression;

public class AssignmentStatement extends Statement implements IExecutableStatement {
	
	public AssignmentStatement(Expression expression, String variableName) {
		this.expression = expression;
		this.variableName = variableName;
	}
	
	private String variableName;
	
	private Expression expression;

	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.expression.addToTask(task);
	}

	@Override
	public void execute() {
		this.getTask().storeVariableExpression(variableName, expression);
	}

	@Override
	public Statement clone() {
		return new AssignmentStatement(expression.clone(), variableName);
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
				return AssignmentStatement.this;
			}
			
		};
	}
	
	@Override
	public List<Statement> getStatements() {
		List<Statement>list=new ArrayList<Statement>();
		list.add(this);
		return list;
	}
}
