package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.BooleanExpression;
import hillbillies.model.expressions.Expression;

public class CreateWhile extends Statement {
 public CreateWhile(BooleanExpression condition, Statement body){
	 this.expression=condition;
	 this.body=body;
 }
private BooleanExpression expression;
private Statement body;
 
 @Override
 public void execute() {
	 while(expression.evaluate()){
		 body.execute();
	 }
		 	
 }
 

@Override
public void addToTask(Task task) {
	task.addStatement(this);
	this.setTask(task);
	
}
}
