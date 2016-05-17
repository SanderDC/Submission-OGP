package hillbillies.model.statements;
import java.util.Iterator;
import java.util.NoSuchElementException;


import hillbillies.model.Task;
import hillbillies.model.expressions.EnemyUnitExpression;
import hillbillies.model.expressions.Expression;

public class AttackStatement extends Statement implements ExecutableStatement {

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
	public Iterator<ExecutableStatement> iterator() {
		return new Iterator<ExecutableStatement>(){

			private boolean statementHandled = false;

			@Override
			public boolean hasNext() {
				return !statementHandled;
			}

			@Override
			public ExecutableStatement next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				statementHandled = true;
				return AttackStatement.this;
			}

		};
	}

	@Override
	public boolean check() {
		if (!this.getUnit().isAttacking()) {
			
			return true;
		}
		return false;
	}

}