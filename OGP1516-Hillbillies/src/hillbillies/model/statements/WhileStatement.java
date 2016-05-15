package hillbillies.model.statements;

import java.util.Iterator;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.BooleanExpression;
import hillbillies.model.expressions.Expression;

public class WhileStatement extends Statement {
	
	public WhileStatement(BooleanExpression condition, Statement body){
		this.expression=condition;
		this.body=body;
		this.body.setParentStatement(this);
		this.broken = false;
	}
	
	private BooleanExpression expression;
	
	private Statement body;

	@Override
	public void execute() {
		if(expression.evaluate()){
			body.execute();
		}

	}

	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		body.addToTask(task);
		this.expression.addToTask(task);
	}
	
	void breakWhile(){
		this.broken = true;
	}
	
	boolean isBroken(){
		return this.broken;
	}
	
	private boolean broken;

	@Override
	public WhileStatement clone() {
		return new WhileStatement(expression.clone(), body.clone());
	}

	@Override
	public Iterator<Statement> iterator() {
		return new Iterator<Statement>() {
			
			Iterator<Statement> subIterator = body.iterator();
			
			@Override
			public Statement next() throws NoSuchElementException {
				if (subIterator.hasNext())
					return subIterator.next();
				else if (expression.evaluate()){
					subIterator = body.iterator();
					return subIterator.next();
				} else
					throw new NoSuchElementException();
			}
			
			@Override
			public boolean hasNext() {
				if (isBroken())
					return false;
				if (subIterator.hasNext())
					return true;
				else if (expression.evaluate())
					return true;
				return false;
			}
		};
	}

@Override
public void   setExecuted(boolean e) {
	this.executed=e;
	Iterator<Statement> itr= this.iterator();
	body.setExecuted(e);
		
	
	
}

@Override
public boolean check() {
	if (expression.evaluate()) {
		return false;
	}
	this.setExecuted(true);
	return true;
}
}
