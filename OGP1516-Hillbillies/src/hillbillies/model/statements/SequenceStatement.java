package hillbillies.model.statements;

import java.util.*;

import hillbillies.model.Task;

public class SequenceStatement extends Statement {
	
	public SequenceStatement(List<Statement> sequence) {
		this.statements = sequence;
	}

	@Override
	public void addToTask(Task task) {
		for (Statement statement:statements){
			statement.addToTask(task);
		}
	}
	
	public List<Statement> getSequence(){
		return this.statements;
	}
	
	private List<Statement> statements;

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
