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
		for (Statement statement : statements) {
			if (!statement.executed) {
				statement.execute();
				return;
			}
		}
		}
		
	

	@Override
	public boolean check() {
		for (Statement statement : statements) {
			if (!statement.executed) {
				statement.check();
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
			
			private int nbStatementsHandled = 0;

			@Override
			public boolean hasNext() {
				if (currentIterator != null && currentIterator.hasNext())
					return true;
				for (int i = nbStatementsHandled + 1; i < statements.size(); i++){
					if (statements.get(i).iterator().hasNext())
						return true;
				}
				return false;
			}

			@Override
			public Statement next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				if (currentIterator == null)
					currentIterator = nextStatements.next().iterator();
				while(!currentIterator.hasNext()){
					nbStatementsHandled += 1;
					currentIterator = nextStatements.next().iterator();
				}
				return currentIterator.next();
			}
			
		};
	}
	@Override
	public void   setExecuted(boolean e) {
		this.executed=e;
		for (Statement statement : statements) {
			statement.setExecuted(e);

		}
			 
		}
		
	
	
	
}
