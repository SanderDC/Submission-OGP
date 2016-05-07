package hillbillies.model.expressions;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import hillbillies.model.Unit;

public class AnyUnitExpression extends Expression implements UnitExpression {

	@Override
	public Unit evaluate() throws NoSuchElementException {
		int index = new Random().nextInt(this.getUnit().getWorld().getUnits().size());
		Iterator<Unit> iter = this.getUnit().getWorld().getUnits().iterator();
		for (int i = 0; i < index; i++) {
		    iter.next();
		}
		return iter.next();
	}

}
