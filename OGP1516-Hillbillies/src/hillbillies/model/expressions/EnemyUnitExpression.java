package hillbillies.model.expressions;

import java.util.*;

import hillbillies.model.Faction;
import hillbillies.model.Unit;

public class EnemyUnitExpression extends UnitExpression {

	@Override
	public Unit evaluate() {
		Unit result = null;
		double distance = 0;
		Set<Unit> allOptions = new HashSet<>();
		for (Faction faction:this.getUnit().getWorld().getActiveFactions()){
			if (faction != this.getUnit().getFaction())
				allOptions.addAll(faction.getUnits());
		}
		for (Unit other:allOptions){
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
