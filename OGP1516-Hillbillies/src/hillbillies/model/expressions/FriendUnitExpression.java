package hillbillies.model.expressions;

import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class FriendUnitExpression extends Expression implements IUnitExpression {

	public FriendUnitExpression(SourceLocation sourceLocation) throws IllegalArgumentException {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate() throws NoSuchElementException {
		Set<Unit> units = this.getUnit().getWorld().getUnits();
		units.remove(getUnit());
		units.removeIf(u -> u.getFaction() != this.getUnit().getFaction());
		if (units.size() == 0)
			throw new NoSuchElementException();
		return World.getNearestObject(units, getUnit());
	}
	
	@Override
	public FriendUnitExpression clone(){
		return new FriendUnitExpression(getSourceLocation());
	}

}
