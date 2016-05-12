package hillbillies.model.expressions;

import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.GameObject;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.part3.programs.SourceLocation;

public class GameObjectPositionExpression<T extends GameObject> extends Expression 
																implements PositionExpression {

	public GameObjectPositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector evaluate() {//TODO: dit kan misschien eleganter
		Unit executingUnit = this.getUnit();
		Set<GameObject> gameObjects = executingUnit.getWorld().getGameObjects();
		T result = null;
		double distance = 0;
		for (GameObject other:gameObjects){
			if (result == null){
				try {
					result = (T) other;
					distance = this.getUnit().getPosition().getDistanceTo(other.getPosition());
				} catch (ClassCastException e) {}
			} else if (this.getUnit().getPosition().getDistanceTo(other.getPosition()) < distance &&
					(other.getClass() == result.getClass())){
				result = (T) other;
				distance = this.getUnit().getPosition().getDistanceTo(other.getPosition());
			}
		}
		if (result == null){
			throw new NoSuchElementException();
		} else
			return result.getPosition();
	}
	
	@Override
	public GameObjectPositionExpression<T> clone(){
		return new GameObjectPositionExpression<>(getSourceLocation());
	}

}
