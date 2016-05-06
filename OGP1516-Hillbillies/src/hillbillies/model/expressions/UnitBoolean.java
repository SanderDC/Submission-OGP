package hillbillies.model.expressions;

public abstract class UnitBoolean extends Expression implements BooleanExpression {
	
	public UnitBoolean(UnitExpression unit) {
		this.target = unit;
	}
	
	protected UnitExpression getTarget(){
		return this.target;
	}
	
	private final UnitExpression target;
}
