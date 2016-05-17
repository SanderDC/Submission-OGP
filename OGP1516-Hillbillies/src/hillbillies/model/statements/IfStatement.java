package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.IBooleanExpression;

public class IfStatement extends Statement {

	public  IfStatement(IBooleanExpression expression, Statement lStatement, Statement rStatement) {
		this.expression=expression;
		this.trueStatement=lStatement;
		this.trueStatement.setParentStatement(this);
		if (rStatement != null) {
			this.falseStatement = rStatement;
			this.falseStatement.setParentStatement(this);
		}
	}


	private IBooleanExpression expression;

	private Statement trueStatement;
	
	boolean hasElseStatement(){
		return this.falseStatement != null;
	}

	private Statement falseStatement;

	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.expression.addToTask(task);
		this.trueStatement.addToTask(task);
		if (this.hasElseStatement())
			this.falseStatement.addToTask(task);
	}

	public IfStatement clone() {
		if (falseStatement!=null) {
			return new IfStatement(expression.clone(), trueStatement.clone(), falseStatement.clone());

		}
		else {
			return new IfStatement(expression.clone(), trueStatement.clone(), null);

		}
		
	}


	@Override
	public Iterator<IExecutableStatement> iterator() {
		return new Iterator<IExecutableStatement>(){

			private boolean firstCall = true;

			private Iterator<IExecutableStatement> subIterator;

			@Override
			public boolean hasNext() {
				if (firstCall)
					if (hasElseStatement())
						return true;
					else
						return expression.evaluate();
				else
					return subIterator.hasNext();
			}

			@Override
			public IExecutableStatement next() throws NoSuchElementException {
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
