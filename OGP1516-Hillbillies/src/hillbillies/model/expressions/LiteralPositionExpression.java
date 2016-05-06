package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Vector;

public class LiteralPositionExpression extends PositionExpression {
	
	public LiteralPositionExpression(int x, int y, int z) {
		this.position = new Vector(x, y, z);
	}

	@Override
	public Vector evaluate() throws NoSuchElementException {
		if (!this.getUnit().getWorld().isInsideWorld(position))
			throw new NoSuchElementException();
		else
			return this.position;
	}
	
	private final Vector position;
}
