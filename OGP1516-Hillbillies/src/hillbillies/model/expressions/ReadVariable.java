package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public abstract class ReadVariable extends Expression {
	public  ReadVariable(String variableName, SourceLocation sourceLocation){
		super(sourceLocation);
		this.variableName=variableName;
	}
	
	protected Expression getExpression(){
		return this.getTask().getVariableExpression(this.variableName);
	}

	private String variableName;

}
