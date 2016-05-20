package hillbillies.model.expressions;

import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class AnyUnitExpression extends Expression implements IUnitExpression {

	public AnyUnitExpression(SourceLocation sourceLocation) throws IllegalArgumentException {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate() throws NoSuchElementException {
		Set<Unit> units = this.getUnit().getWorld().getUnits();
		units.remove(getUnit());
		return World.getNearestObject(units, getUnit());
	}
	
	@Override
	public AnyUnitExpression clone(){
		return new AnyUnitExpression(getSourceLocation());
	}

}
