package hillbillies.model.expressions;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class IsEnemyBoolean extends UnitBoolean {

	public IsEnemyBoolean(UnitExpression unit, SourceLocation sourceLocation) {
		super(unit, sourceLocation);
	}

	@Override
	public Boolean evaluate() {
		Unit target = this.getTarget().evaluate();
		if (target.getWorld() != this.getUnit().getWorld())
			return false;
		else
			return !this.getUnit().getFaction().hasAsUnit(target);
	}
	
	@Override
	public IsEnemyBoolean clone(){
		return new IsEnemyBoolean(getTarget().clone(), getSourceLocation());
	}
}
