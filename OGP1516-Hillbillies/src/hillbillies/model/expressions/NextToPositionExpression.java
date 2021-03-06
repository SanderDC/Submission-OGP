package hillbillies.model.expressions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import hillbillies.model.Task;
import hillbillies.model.Vector;
import hillbillies.part3.programs.SourceLocation;

public class NextToPositionExpression extends Expression implements IPositionExpression {
	
	public NextToPositionExpression(IPositionExpression position, SourceLocation sourceLocation) 
			throws IllegalArgumentException {
		super(sourceLocation);
		if (position == null)
			throw new IllegalArgumentException();
		this.target = position;
	}

	@Override
	public Vector evaluate() throws NoSuchElementException {
		List<Integer> xPositions = new ArrayList<>();
		xPositions.add(-1);xPositions.add(0);xPositions.add(1);
		List<Integer> yPositions = new ArrayList<>();
		yPositions.addAll(xPositions);
		List<Integer> zPositions = new ArrayList<>();
		zPositions.addAll(xPositions);
		Collections.shuffle(xPositions);Collections.shuffle(yPositions);Collections.shuffle(zPositions);
		for (int x=0; x < xPositions.size();x++){
			for (int y=0; y < yPositions.size();y++){
				for (int z=0; z < zPositions.size();z++){
					if (Math.abs(xPositions.get(x)) + Math.abs(yPositions.get(y)) + Math.abs(zPositions.get(z)) == 1){
						Vector position = target.evaluate().add(new Vector(xPositions.get(x),yPositions.get(y),zPositions.get(z)));
						if (this.getUnit().getWorld().unitCanStandAt(position))
							return position;
					}
				}
			}
		}
		throw new NoSuchElementException();
	}
	
	private final IPositionExpression target;
	
	@Override
	public NextToPositionExpression clone(){
		return new NextToPositionExpression(target.clone(), getSourceLocation());
	}
	
	@Override
	public void addToTask(Task task) {
		this.setTask(task);
		this.target.addToTask(task);
	}

	@Override
	public boolean isWellFormed(Set<String> variables) {
		return this.target.isWellFormed(variables);
	}
}
