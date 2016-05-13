package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.BooleanExpression;
import hillbillies.model.expressions.Expression;

public class IfStatement extends Statement {

	public  IfStatement(BooleanExpression expression, Statement lStatement, Statement rStatement) {
		this.expression=expression;
		this.trueStatement=lStatement;
		this.trueStatement.setParentStatement(this);
		this.falseStatement=rStatement;
		this.falseStatement.setParentStatement(this);
	}


	private BooleanExpression expression;

	private Statement trueStatement;

	private Statement falseStatement;

	@Override
	public void execute(){
		if (expression.evaluate()) {
			trueStatement.execute();
		}
		else {
			falseStatement.execute();
		}
	}


	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.expression.addToTask(task);
		this.trueStatement.addToTask(task);
		this.falseStatement.addToTask(task);

	}


	@Override
	public IfStatement clone() {
		return new IfStatement(expression.clone(), trueStatement.clone(), falseStatement.clone());
	}


	@Override
	public Iterator<Statement> iterator() {
		return new Iterator<Statement>(){

			private boolean firstCall = true;

			private Iterator<Statement> subIterator;

			@Override
			public boolean hasNext() {
				if (firstCall)
					return true;
				else
					return subIterator.hasNext();
			}

			@Override
			public Statement next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				if (firstCall){
					firstCall = false;
					if (expression.evaluate())
						subIterator = trueStatement.iterator();
					else
						subIterator = falseStatement.iterator();
				}
				return subIterator.next();
			}

		};
	}

}
