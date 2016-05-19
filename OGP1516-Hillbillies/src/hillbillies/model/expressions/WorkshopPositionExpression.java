package hillbillies.model.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import hillbillies.model.Heap;
import hillbillies.model.Node;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;

public class WorkshopPositionExpression extends Expression implements IPositionExpression {

	public WorkshopPositionExpression(SourceLocation sourceLocation) throws IllegalArgumentException {
		super(sourceLocation);
	}

	@Override
	public Vector evaluate() throws NoSuchElementException {
		Unit unit = this.getUnit();
		World world = unit.getWorld();
		Heap<Node> open = new Heap<>();
		List<Node> closed = new ArrayList<>();
		Node start = new Node(unit.getPosition());
		open.add(start);
		while (open.size() > 0){
			Node current = open.pop();
			closed.add(current);
			if (world.getCubeType(current.getCubeCoordinates().getCubeX(), current.getCubeCoordinates().getCubeY(), current.getCubeCoordinates().getCubeZ()) == 3)
				return current.getCubeCoordinates();
			for (Node neighbour:current.getNeighbouringNodes()){
				if (!unit.getWorld().unitCanStandAt(neighbour.getCubeCoordinates()) ||
						closed.contains(neighbour))
					continue;
				neighbour.setGCost(current.getGCost()+Node.calculateDistance(current, neighbour));
				neighbour.setHCost(0);
				if (!open.contains(neighbour)){
					open.add(neighbour);
				}
				else {
					int index = open.getIndex(neighbour);
					if (neighbour.compareTo(open.get(index)) < 0){
						open.replace(index, neighbour);
					}
				}
			}
		}
		throw new NoSuchElementException();
	}
	
	@Override
	public WorkshopPositionExpression clone(){
		return new WorkshopPositionExpression(getSourceLocation());
	}

}
