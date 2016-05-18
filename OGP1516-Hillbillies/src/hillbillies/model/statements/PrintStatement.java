package hillbillies.model.statements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.IExpression;

public class PrintStatement extends Statement implements IExecutableStatement {

	public  PrintStatement(IExpression value) {
		this.expression=value;
	}

	private IExpression expression;

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
				return PrintStatement.this;
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
