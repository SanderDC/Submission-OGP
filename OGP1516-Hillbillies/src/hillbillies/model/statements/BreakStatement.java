package hillbillies.model.statements;

import hillbillies.model.Task;

public class BreakStatement  extends Statement{

	@Override
	public void addToTask(Task task) {
		task.addStatement(this);
		this.setTask(task);
	}

	@Override
	public void execute() {
		
	}

}
