package hillbillies.model.statements;
import java.util.NoSuchElementException;


import hillbillies.model.Task;
import hillbillies.model.expressions.EnemyUnitExpression;
import hillbillies.model.expressions.Expression;

public class AttackStatement extends Statement {

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
	
}



