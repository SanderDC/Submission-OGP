package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Vector;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class BoulderPositionExpression extends Expression implements PositionExpression {

	public BoulderPositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Vector evaluate() throws NoSuchElementException {
		if (this.getUnit().getWorld().GetAllBoulders().size() == 0)
			throw new NoSuchElementException();
		return World.getNearestObject(this.getUnit().getWorld().GetAllBoulders(), getUnit()).getPosition();
	}
	

	@Override
	public BoulderPositionExpression clone() {
		return new BoulderPositionExpression(getSourceLocation());
	}

}
