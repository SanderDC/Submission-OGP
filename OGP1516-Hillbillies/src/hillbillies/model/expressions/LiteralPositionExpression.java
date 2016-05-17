package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Vector;
import hillbillies.part3.programs.SourceLocation;

public class LiteralPositionExpression extends Expression implements IPositionExpression {
	
	public LiteralPositionExpression(int x, int y, int z, SourceLocation sourceLocation) {
		super(sourceLocation);
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
	
	@Override
	public LiteralPositionExpression clone(){
		return new LiteralPositionExpression(this.position.getCubeX(), this.position.getCubeY(), this.position.getCubeZ(), getSourceLocation());
	}
}
