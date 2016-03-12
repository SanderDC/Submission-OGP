package hillbillies.model;

public class World {
	public World(int[][][] Coordinates){
		this.Coordinates=Coordinates;
	}
	private int [][][] Coordinates;
	
	private int[][][]getCoordinates () {
		return this.Coordinates;
	}
	public int maxCoordinateX() {
		return getCoordinates().length;
	}
	public int maxCoordinateY(){
		return getCoordinates()[0].length;

	}
	public int maxCoordinateZ() {
		return getCoordinates()[0][1].length;

	}
	public void advanceTime(double time)throws IllegalArgumentException {
		if (time<0||time>0.2)
			throw new IllegalArgumentException();
	}
	public void getCubeType(int [][][] coordinates) {
		
	}
	public void setCubeType(int [][][] coordinates) {
		
	}
	
}
