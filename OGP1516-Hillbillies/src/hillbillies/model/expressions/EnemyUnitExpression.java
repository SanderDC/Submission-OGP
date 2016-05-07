package hillbillies.model.expressions;

import java.util.*;

import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class EnemyUnitExpression extends Expression implements UnitExpression {

	public EnemyUnitExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate() throws NoSuchElementException {
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
		if (result == null)
			throw new NoSuchElementException();
		else
			return result;
	}

}
