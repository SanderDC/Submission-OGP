package hillbillies.model.statements;

import hillbillies.model.Task;
import hillbillies.model.expressions.Expression;

public class CreateWhile extends Statement {
 public CreateWhile(Expression condition, Statement body){
	 this.expression=expression;
	 this.body=body;
 }
private Expression expression;
private Statement body;
 
 @Override
 public void execute() {
	 while(expression.evaluate){
		 body.execute();
	 }
		 	
 }
 
@Override
public void addToTask(Task task) {
	// TODO Auto-generated method stub
	
}
}
