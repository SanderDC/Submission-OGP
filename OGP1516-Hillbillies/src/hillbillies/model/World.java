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
	public  int getCubeType(int x,int y, int z) {
		return getCoordinates()[x][y][z];
		
	}
	public void setCubeType(int x,int y, int z, int value) {
		this.getCoordinates()[x][y][z]=value;
	}
	
}
