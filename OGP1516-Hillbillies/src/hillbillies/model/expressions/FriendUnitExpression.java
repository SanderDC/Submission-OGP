package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.*;

public class FriendUnitExpression extends Expression implements UnitExpression {

	@Override
	public Unit evaluate() throws NoSuchElementException {
		Faction faction = this.getUnit().getFaction();
		Unit result = null;
		double distance = 0;
		for (Unit other:faction.getUnits()){
			if (result == null){
				result = other;
				distance = this.getUnit().getPosition().getDistanceTo(other.getPosition());
			} else if (this.getUnit().getPosition().getDistanceTo(other.getPosition())
					< distance){
				result = other;
				distance = this.getUnit().getPosition().getDistanceTo(other.getPosition());
			}
		}
		if (result == null)
			throw new NoSuchElementException();
		else
			return result;
	}

}
