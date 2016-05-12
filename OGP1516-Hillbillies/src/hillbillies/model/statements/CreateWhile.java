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
}
