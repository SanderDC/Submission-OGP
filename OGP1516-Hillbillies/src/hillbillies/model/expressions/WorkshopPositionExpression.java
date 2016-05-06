package hillbillies.model.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Vector;
import hillbillies.model.World;

public class WorkshopPositionExpression extends PositionExpression {

	@Override
	public Vector evaluate() throws NoSuchElementException {
		Vector position = this.getUnit().getPosition();
		World world = this.getUnit().getWorld();
		int[] result = null;
		double distance = 0;
		for (int x = 0; x <= world.maxCoordinates()[0]; x++){
			for (int y = 0; y <= world.maxCoordinates()[1]; y++){
				for (int z = 0; z <= world.maxCoordinates()[2]; y++){
					if (world.getCubeType(x, y, z) == 3){
						if (result == null){
							result = new int[]{x,y,z};
							distance = position.getDistanceTo(new Vector(x,y,z));
						} else if (position.getDistanceTo(new Vector(x,y,z)) < distance){
							result = new int[]{x,y,z};
							distance = position.getDistanceTo(new Vector(x,y,z));
						}
					}
				}
			}
		}
		if (result == null)
			throw new NoSuchElementException();
		else
			return new Vector(result[0],result[1],result[2]);
	}

}
