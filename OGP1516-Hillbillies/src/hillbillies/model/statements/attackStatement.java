package hillbillies.model.statements;
import java.util.NoSuchElementException;


import hillbillies.model.Task;
import hillbillies.model.expressions.EnemyUnitExpression;
import hillbillies.model.expressions.Expression;

public class attackStatement extends Statement {

	
	public  attackStatement(EnemyUnitExpression expression) {
		this.expression=expression;
	}
		private EnemyUnitExpression expression;
	@Override
	public void execute(){
			
			this.getUnit().startAttack(expression.evaluate());
			
			
	}
	@Override
	public void addToTask(Task task) {
		task.addStatement(this);
		this.setTask(task);
		this.expression.addToTask(task);
		
	}

	
	
		
	}
	


