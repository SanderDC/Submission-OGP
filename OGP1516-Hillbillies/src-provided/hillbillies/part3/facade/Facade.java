package hillbillies.part3.facade;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.PathfindingException;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.World;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.part3.programs.ITaskFactory;
import ogp.framework.util.ModelException;

public class Facade implements IFacade {

	@Override
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		return new World(terrainTypes, modelListener);
	}

	@Override
	public int getNbCubesX(World world) throws ModelException {
		return world.nbCoordinateX();
	}

	@Override
	public int getNbCubesY(World world) throws ModelException {
		return world.nbCoordinateY();
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		return world.nbCoordinateZ();
	}

	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		
		world.advanceTime(dt);
	}

	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		return world.getCubeType(x, y, z);
	}

	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		world.setCubeType(x, y, z, value);
	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		return world.isSolidConnectedToBorder(new Vector(x, y, z));
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		return new Unit(world, enableDefaultBehavior);
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		world.addUnit(unit);
	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		return world.getUnits();
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		return unit.isCarryingLog();
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		return unit.isCarryingBoulder();
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		return !(unit.isTerminated());
	}


	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		return unit.getExp();
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		try {
			unit.WorkAt(x, y, z);
		} catch (IllegalStateException | IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		return unit.getFaction();
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
				return faction.getUnits();
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		return world.getActiveFactions();
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		return boulder.getPosition().toArray();
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		return world.GetAllBoulders();
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {
		return log.getPosition().toArray();
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		return world.GetAllLogs();
	
	}

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		try {
			return new Unit(new Vector(initialPosition[0] + Unit.CUBELENGTH/2,
					initialPosition[1] + Unit.CUBELENGTH/2,
					initialPosition[2] + Unit.CUBELENGTH/2), agility,strength,weight,name,toughness, enableDefaultBehavior);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		return unit.getPosition().toArray();
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		return new int[]{(int) unit.getPosition().getCubeX(),
				(int) unit.getPosition().getCubeY(), 
				(int) unit.getPosition().getCubeZ()};
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		return unit.getName();
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		try {
			unit.setName(newName);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		return unit.getTotalWeight();
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		unit.setWeight(newValue);
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		return unit.getStrength();
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		unit.setStrength(newValue);
	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		return unit.getAgility();
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		unit.setAgility(newValue);
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		return unit.getToughness();
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		unit.setToughness(newValue);
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		return unit.getmaxHitpoints();
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		return unit.getHitpoints();
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		return unit.getmaxStamina();
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		return unit.getStamina();
	}

	

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		try {
			unit.advanceTime(dt);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		try {
			unit.setDefaultBehaviorBoolean(false);
			unit.moveToAdjacent(dx, dy, dz);
		} catch (IllegalArgumentException|IllegalStateException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		return unit.getSpeed().norm();
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		return unit.isMoving();
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		try {
			unit.setSprinting(true);
		} catch (IllegalStateException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		try {
			unit.setSprinting(false);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		return unit.getSprinting();
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		return unit.getOrientation();
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		try {
			unit.setDefaultBehaviorBoolean(false);
			unit.moveTo(cube[0], cube[1], cube[2]);
		} catch (IllegalArgumentException|IllegalStateException|PathfindingException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		return unit.isWorking();
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		try {
			attacker.setDefaultBehaviorBoolean(false);
			defender.setDefaultBehaviorBoolean(false);
			attacker.startAttack(defender);
		} catch (IllegalArgumentException e) {
			
			throw new ModelException(e);
		}
	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		return unit.isAttacking();
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		try {
			unit.setDefaultBehaviorBoolean(false);
			unit.resting();
		} catch (IllegalStateException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		return unit.isResting();
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		try {
			unit.setDefaultBehaviorBoolean(value);
		} catch (IllegalStateException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		return unit.getdefaultbehaviorboolean();
	}

	@Override
	public ITaskFactory<?, ?, Task> createTaskFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWellFormed(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Scheduler getScheduler(Faction faction) throws ModelException {
		return faction.getScheduler();
	}

	@Override
	public void schedule(Scheduler scheduler, Task task) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replace(Scheduler scheduler, Task original, Task replacement) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean areTasksPartOf(Scheduler scheduler, Collection<Task> tasks) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Task> getAllTasksIterator(Scheduler scheduler) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Scheduler> getSchedulersForTask(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unit getAssignedUnit(Task task) throws ModelException {
		return task.getUnit();
	}

	@Override
	public Task getAssignedTask(Unit unit) throws ModelException {
		return unit.getTask();
	}

	@Override
	public String getName(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPriority(Task task) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

}
