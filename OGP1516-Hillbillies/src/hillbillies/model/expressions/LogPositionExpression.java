package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Vector;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class LogPositionExpression extends Expression implements IPositionExpression {

	public LogPositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Vector evaluate() throws NoSuchElementException {
		if (this.getUnit().getWorld().GetAllLogs().size() == 0)
			throw new NoSuchElementException();
		return World.getNearestObject(this.getUnit().getWorld().GetAllLogs(), getUnit()).getPosition();
	}

	@Override
	public LogPositionExpression clone() {
		return new LogPositionExpression(getSourceLocation());
	}

}
