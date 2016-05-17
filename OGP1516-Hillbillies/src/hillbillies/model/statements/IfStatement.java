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
		if (rStatement != null) {
			this.falseStatement = rStatement;
			this.falseStatement.setParentStatement(this);
		}
	}


	private BooleanExpression expression;

	private Statement trueStatement;
	
	boolean hasElseStatement(){
		return this.falseStatement != null;
	}

	private Statement falseStatement;

	private int trueorfalse=0;
	
//	public void execute(){
//		
//			if (expression.evaluate()) {
//				trueorfalse=1;
//				trueStatement.execute();
//			}
//			else {
//				trueorfalse=2;
//				if (!hasElseStatement()) {
//					return;
//				}
//				falseStatement.execute();
//			}
//		
//		
//	}


	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.expression.addToTask(task);
		this.trueStatement.addToTask(task);
		if (this.hasElseStatement())
			this.falseStatement.addToTask(task);
	}


	@Override
	public boolean check() {
		if (this.trueorfalse==1&&trueStatement.check()) {
			this.setExecuted(true);
			return true;
		}
		if (!this.hasElseStatement()&&this.trueorfalse==2){
			this.setExecuted(true);
			return true;
		}
		if (this.trueorfalse==2&&falseStatement.check()) {
			this.setExecuted(true);
			
			return true;
		}
		return false;
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
	public Iterator<ExecutableStatement> iterator() {
		return new Iterator<ExecutableStatement>(){

			private boolean firstCall = true;

			private Iterator<ExecutableStatement> subIterator;

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
			public ExecutableStatement next() throws NoSuchElementException {
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
	public void   setExecuted(boolean e) {
		this.executed=e;
		this.trueorfalse=0;
		this.trueStatement.setExecuted(e);
		if (hasElseStatement()) {
			this.falseStatement.setExecuted(e);
		}
		
	}

}
