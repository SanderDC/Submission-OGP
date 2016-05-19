package hillbillies.model.statements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.Task;
import hillbillies.model.expressions.IExpression;
import hillbillies.part3.programs.SourceLocation;

public class AssignmentStatement extends Statement implements IExecutableStatement {
	
	public AssignmentStatement(IExpression expression, String variableName, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(sourceLocation);
		if (expression == null || variableName == null || variableName.length() == 0)
			throw new IllegalArgumentException();
		this.expression = expression;
		this.variableName = variableName;
	}
	
	public String getVariableName(){
		return this.variableName;
	}
	
	private String variableName;
	
	private IExpression expression;

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
		return new AssignmentStatement(expression.clone(), variableName, getSourceLocation());
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

	@Override
	public boolean isWellFormed(Set<String> variables) {
		variables.add(this.getVariableName());
		return true;
	}
}
