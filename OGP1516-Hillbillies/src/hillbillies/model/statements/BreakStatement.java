package hillbillies.model.statements;

import hillbillies.model.Task;

public class BreakStatement  extends Statement{

	@Override
	public void addToTask(Task task) {
		this.setTask(task);
	}

	@Override
	public void execute() {
		
	}

	@Override
	public BreakStatement clone() {
		return new BreakStatement();
	}

}
