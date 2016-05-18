package hillbillies.model.expressions;

import java.util.Set;

import hillbillies.part3.programs.SourceLocation;

public abstract class ReadVariable extends Expression {
	
	public  ReadVariable(String variableName, SourceLocation sourceLocation){
		super(sourceLocation);
		this.variableName=variableName;
	}
	
	protected IExpression getExpression(){
		return this.getTask().getVariableExpression(this.variableName);
	}
	
	protected String getVariableName(){
		return this.variableName;
	}

	private String variableName;

	@Override
	public boolean isWellFormed(Set<String> variables) {
		return (variables.contains(this.getVariableName()));
	}

}
