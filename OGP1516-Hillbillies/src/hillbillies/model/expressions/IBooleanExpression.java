package hillbillies.model.expressions;

public interface IBooleanExpression extends IExpression{
	
	public Boolean evaluate();
		
	public IBooleanExpression clone();

}
