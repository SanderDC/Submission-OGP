package hillbillies.part1.facade;


import hillbillies.model.Unit;
import hillbillies.model.Vector;
import ogp.framework.util.ModelException;

public class Facade implements IFacade{

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
		return new int[]{(int) unit.getCubeX(), (int) unit.getCubeY(), (int) unit.getCubeZ()};
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
		return unit.getWeight();
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
		// TODO Auto-generated method stub
		return unit.getmaxHitpoints();
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getHitpoints();
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getmaxStamina();
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
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
		return unit.ismoving();
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
		} catch (IllegalArgumentException|IllegalStateException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void work(Unit unit) throws ModelException {
		try {
			unit.setDefaultBehaviorBoolean(false);
			unit.setToWork();
		} catch (IllegalStateException e) {
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
		return unit.isresting();
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
	
}