package hillbillies.model.expressions;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class EnemyUnitExpression extends Expression implements IUnitExpression {

	public EnemyUnitExpression(SourceLocation sourceLocation) throws IllegalArgumentException {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate() throws NoSuchElementException {
		Set<Unit> units = new HashSet<>();
		units.addAll(this.getUnit().getWorld().getUnits());
		units.removeIf(u -> u.getFaction() == this.getUnit().getFaction());
		if (units.size() == 0)
			throw new NoSuchElementException();
		return World.getNearestObject(units, getUnit());
	}
	
	@Override
	public EnemyUnitExpression clone(){
		return new EnemyUnitExpression(getSourceLocation());
	}

}
