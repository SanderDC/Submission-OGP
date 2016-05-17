package hillbillies.model.expressions;

import hillbillies.part3.programs.SourceLocation;

public class OrBooleanExpression extends BinaryBooleanExpression {

	public OrBooleanExpression(IBooleanExpression leftExpression, IBooleanExpression rightExpression, SourceLocation sourceLocation) {
		super(leftExpression, rightExpression, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		return (this.getLeftExpression().evaluate()) || (this.getRightExpression().evaluate());
	}
	
	@Override
	public OrBooleanExpression clone(){
		return new OrBooleanExpression(getLeftExpression().clone(), getRightExpression().clone(), getSourceLocation());
	}

}
