package hillbillies.model.statements;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.expressions.EnemyUnitExpression;

public class AttackStatement extends Statement implements IExecutableStatement {

	public  AttackStatement(EnemyUnitExpression expression) {
		this.expression=expression;
	}

	private EnemyUnitExpression expression;

	@Override
	public void execute(){
		this.getUnit().startAttack(expression.evaluate());
	}

	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.expression.addToTask(task);
	}

	@Override
	public AttackStatement clone() {
		return new AttackStatement(expression.clone());
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
				return AttackStatement.this;
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