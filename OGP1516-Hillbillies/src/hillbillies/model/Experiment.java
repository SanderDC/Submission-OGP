package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.facade.Facade;
import ogp.framework.util.ModelException;

public class Experiment {
	
	public Experiment() throws ModelException {
		facade = new Facade();
		int[][][] types = new int[15][15][15];
		world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.spawnUnit(world, false);
		Log log = new Log(new Vector(0,0,0), world);
		Boulder boulder = new Boulder(new Vector(0,0,0),world);
	}

	public static void main(String[] args) throws ModelException{
		Experiment exp = new Experiment();
		Log log = new Log(new Vector(0,0,0), exp.world);
		Set<GameObject> set = new HashSet<>();
		set.add(log);
		for (GameObject obj : set){
			System.out.println((Unit) obj);
		}
	}
	
	public <T> T getObject(){
		for (GameObject object : world.getGameObjects()){
			try {
				T result = (T) object;
				return result;
			} catch (ClassCastException e){
				continue;
			}
		}
		return null;
	}
	
	public World world;
	
	private Facade facade;
}
