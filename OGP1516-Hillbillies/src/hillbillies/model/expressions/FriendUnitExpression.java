package hillbillies.model.expressions;

import hillbillies.model.*;

public class FriendUnitExpression extends UnitExpression {

	@Override
	public Unit evaluate() {
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
		return result;
	}

}
