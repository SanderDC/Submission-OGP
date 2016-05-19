package hillbillies.model.statements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.Task;
import hillbillies.model.expressions.IBooleanExpression;
import hillbillies.part3.programs.SourceLocation;

public class IfStatement extends Statement {

	public  IfStatement(IBooleanExpression expression, Statement lStatement, Statement rStatement, SourceLocation sourceLocation)
			throws IllegalArgumentException {
		super(sourceLocation);
		if (expression == null || lStatement == null)
			throw new IllegalArgumentException();
		this.expression=expression;
		this.trueStatement=lStatement;
		this.trueStatement.setParentStatement(this);
		if (rStatement != null) {
			this.falseStatement = rStatement;
			this.falseStatement.setParentStatement(this);
		} else
			this.falseStatement = null;
	}


	private final IBooleanExpression expression;

	private final Statement trueStatement;
	
	boolean hasElseStatement(){
		return this.falseStatement != null;
	}

	private final Statement falseStatement;

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
			return new IfStatement(expression.clone(), trueStatement.clone(), falseStatement.clone(), getSourceLocation());

		}
		else {
			return new IfStatement(expression.clone(), trueStatement.clone(), null, getSourceLocation());

		}
		
	}
	
	
	public List<Statement>  getStatements(){
		List<Statement>list=new ArrayList<Statement>();
		list.add(trueStatement);
		if (hasElseStatement()) {
			list.add(falseStatement);
		}
		return list;
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

	@Override
	public boolean isWellFormed(Set<String> variables) {
		if (this.hasElseStatement())
			return (this.expression.isWellFormed(variables) && this.trueStatement.isWellFormed(variables)
					&& this.falseStatement.isWellFormed(variables));
		else
			return (this.expression.isWellFormed(variables) && this.trueStatement.isWellFormed(variables));
	}

	@Override
	public void reset() {
		this.trueStatement.reset();
		if (this.hasElseStatement())
			this.falseStatement.reset();
	}

}
