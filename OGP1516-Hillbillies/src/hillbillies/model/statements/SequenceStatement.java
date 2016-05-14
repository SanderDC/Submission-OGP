package hillbillies.model.statements;

import java.util.*;

import hillbillies.model.Task;

public class SequenceStatement extends Statement {
	
	public SequenceStatement(List<Statement> sequence) {
		this.statements = sequence;
		for (Statement statement:this.statements){
			statement.setParentStatement(this);
		}
	}

	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		for (Statement statement : statements) {
			statement.addToTask(task);
		}
	}
	
	public List<Statement> getSequence(){
		return this.statements;
	}
	
	private List<Statement> statements = new ArrayList<>();

	@Override
	public void execute() {
		Iterator<Statement> itr= this.iterator();
		boolean foundstatement = false;
		Statement statementtoexecute = null;
		for (Statement statement : statements) {
			if (!foundstatement&&!statement.executed) {
				 statementtoexecute=statement;
			}
		}
		statementtoexecute.execute();
		}
		
	

	@Override
	public boolean check() {
		for (Statement statement : statements) {
			if (!statement.executed) {
				return false;
			}
		}
		return true;
	}
	public SequenceStatement clone() {
		List<Statement> cloned = new ArrayList<>();
		for (Statement statement:this.statements){
			cloned.add(statement.clone());
		}
		return new SequenceStatement(cloned);
	}

	@Override
	public Iterator<Statement> iterator() {
		return new Iterator<Statement>(){
			
			private Iterator<Statement> nextStatements = statements.iterator();
			
			private Iterator<Statement> currentIterator;

			@Override
			public boolean hasNext() {
				return nextStatements.hasNext() || currentIterator.hasNext();
			}

			@Override
			public Statement next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				if (currentIterator == null)
					currentIterator = nextStatements.next().iterator();
				else if (!currentIterator.hasNext())
					currentIterator = nextStatements.next().iterator();
				return currentIterator.next();
			}
			
		};
	}
	@Override
	public void   setExecuted(boolean e) {
		this.executed=e;
		Iterator<Statement> itr= this.iterator();
		while (itr.hasNext()) {
			Statement statement=itr.next();
			statement.setExecuted(e);
		}
		
	}
}
