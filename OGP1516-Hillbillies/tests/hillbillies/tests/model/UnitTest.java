package hillbillies.tests.model;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.World;

public class UnitTest {
	
	private static Unit movingDistantUnit, movingAdjacentUnit,distantUnit, idleUnit, adjacentUnit;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		distantUnit = new Unit(new Vector(10.5,10.5,10.5), 50, 50, 50, "James", 50, false);
		idleUnit = new Unit(new Vector(5.5,5.5,5.5), 50, 50, 50, "Idle", 50, false);
		adjacentUnit = new Unit(new Vector(6.5,5.5,5.5), 50, 50, 50, "Adjacent to Idle", 50, false);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Constructor_IllegalPosition(){
		new Unit(new Vector(-1.5,0.5,0.5), 50, 50, 50,"Illegal", 50, false);
	}
	
	@Test
	public void Constructor_attributesTooSmall(){
		Unit test = new Unit(new Vector(1.5,1.5,1.5), 20, -2, 0,"Johnny", 10, false);
		assertTrue(test.getAgility() == 25);
		assertTrue(test.getStrength() == 25);
		assertTrue(test.getToughness() == 25);
		assertTrue(test.getWeight() == 25);
		assertTrue(test.getmaxHitpoints() == 13);
		assertTrue(test.getmaxStamina() == 13);
		assertTrue(test.getHitpoints() == 13);
		assertTrue(test.getStamina() == 13);
		assertTrue(test.getSpeed().equals(new Vector(0,0,0)));
		assertTrue(test.getNearTarget() == null);
		assertTrue(test.getDistantTarget() == null);
		assertTrue(test.getPosition().equals(new Vector(1.5,1.5,1.5)));
		assertTrue(test.getName().equals("Johnny"));
		assertFalse(test.getdefaultbehaviorboolean());
	}
	
	@Test
	public void Constructor_attributesTooLarge(){
		Unit test = new Unit(new Vector(1.5,1.5,1.5), 150, Integer.MAX_VALUE, 50,"Johnny", 900, false);
		assertTrue(test.getAgility() == 100);
		assertTrue(test.getStrength() == 100);
		assertTrue(test.getToughness() == 100);
		assertTrue(test.getWeight() == 100);
		assertTrue(test.getmaxHitpoints() == 200);
		assertTrue(test.getmaxStamina() == 200);
		assertTrue(test.getHitpoints() == 200);
		assertTrue(test.getStamina() == 200);
		assertTrue(test.getSpeed().equals(new Vector(0,0,0)));
		assertTrue(test.getNearTarget() == null);
		assertTrue(test.getDistantTarget() == null);
		assertTrue(test.getPosition().equals(new Vector(1.5,1.5,1.5)));
		assertTrue(test.getName().equals("Johnny"));
		assertFalse(test.getdefaultbehaviorboolean());
	}
	
	@Test
	public void setAgility_legalCase(){
		idleUnit.setAgility(100);
		assertEquals(idleUnit.getAgility(), 100);
		assertEquals(idleUnit.getWeight(), 75);
		assertEquals(idleUnit.getmaxHitpoints(),75);
		assertEquals(idleUnit.getmaxStamina(),75);
	}
	
	@Test
	public void setAgility_tooLarge(){
		idleUnit.setAgility(500);
		assertEquals(idleUnit.getAgility(),200);
		assertEquals(idleUnit.getWeight(), 125);
		assertEquals(idleUnit.getmaxHitpoints(),125);
		assertEquals(idleUnit.getmaxStamina(),125);
	}
	
	@Test
	public void setAgility_tooSmall(){
		idleUnit.setAgility(-200);
		assertEquals(idleUnit.getAgility(), 1);
		assertEquals(idleUnit.getWeight(), 50);
		assertEquals(idleUnit.getmaxHitpoints(),50);
		assertEquals(idleUnit.getmaxStamina(),50);
	}
	
	@Test
	public void setStrength_legalCase(){
		idleUnit.setStrength(100);
		assertEquals(idleUnit.getStrength(), 100);
		assertEquals(idleUnit.getWeight(), 75);
		assertEquals(idleUnit.getmaxHitpoints(),75);
		assertEquals(idleUnit.getmaxStamina(),75);
	}
	
	@Test
	public void setStrength_tooLarge(){
		idleUnit.setStrength(500);
		assertEquals(idleUnit.getStrength(),200);
		assertEquals(idleUnit.getWeight(), 125);
		assertEquals(idleUnit.getmaxHitpoints(),125);
		assertEquals(idleUnit.getmaxStamina(),125);
	}
	
	@Test
	public void setStrength_tooSmall(){
		idleUnit.setStrength(-200);
		assertEquals(idleUnit.getStrength(), 1);
		assertEquals(idleUnit.getWeight(), 50);
		assertEquals(idleUnit.getmaxHitpoints(),50);
		assertEquals(idleUnit.getmaxStamina(),50);
	}
	
	@Test
	public void setToughness_legalCase(){
		idleUnit.setToughness(100);
		assertEquals(idleUnit.getToughness(), 100);
		assertEquals(idleUnit.getmaxHitpoints(),100);
		assertEquals(idleUnit.getmaxStamina(), 100);
	}
	
	@Test
	public void setToughness_tooLarge(){
		idleUnit.setToughness(1000);
		assertEquals(idleUnit.getToughness(), 200);
		assertEquals(idleUnit.getmaxHitpoints(), 200);
		assertEquals(idleUnit.getmaxStamina(), 200);
	}
	
	@Test
	public void setToughness_tooSmall(){
		idleUnit.setToughness(-200);
		assertEquals(idleUnit.getToughness(), 1);
		assertEquals(idleUnit.getmaxHitpoints(), 1);
		assertEquals(idleUnit.getHitpoints(), 1);
		assertEquals(idleUnit.getmaxStamina(), 1);
		assertEquals(idleUnit.getStamina(),1);
	}
	
	@Test
	public void setWeight_legalCase(){
		idleUnit.setWeight(100);
		assertEquals(idleUnit.getWeight(), 100);
		assertEquals(idleUnit.getmaxHitpoints(),100);
		assertEquals(idleUnit.getmaxStamina(), 100);
	}
	
	@Test
	public void setWeight_tooLarge(){
		idleUnit.setWeight(1000);
		assertEquals(idleUnit.getWeight(), 200);
		assertEquals(idleUnit.getmaxHitpoints(), 200);
		assertEquals(idleUnit.getmaxStamina(), 200);
	}
	
	@Test
	public void setWeight_tooSmall(){
		idleUnit.setWeight(-200);
		assertEquals(idleUnit.getWeight(), 50);
		assertEquals(idleUnit.getmaxHitpoints(), 50);
		assertEquals(idleUnit.getmaxStamina(), 50);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setName_tooShort(){
		idleUnit.setName("A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setName_noCapital(){
		idleUnit.setName("johnny");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setName_illegalToken(){
		idleUnit.setName("J4m3s");
	}
	
	@Test
	public void setName_legalCase(){
		idleUnit.setName("James O'Hara");
		assertEquals(idleUnit.getName(),"James O'Hara");
	}
	
	@Test
	public void isValidStamina_legalCase(){
		assertTrue(Unit.isValidStamina(0, idleUnit.getmaxStamina()));
	}
	
	@Test
	public void isValidStamina_negative(){
		assertFalse(Unit.isValidStamina(-1, idleUnit.getmaxStamina()));
	}
	
	@Test
	public void isValidStamina_tooLarge(){
		assertFalse(Unit.isValidStamina(idleUnit.getmaxStamina() + 1, idleUnit.getmaxStamina()));
	}
	
	@Test
	public void isValidHitpoints_legalCase(){
		assertTrue(Unit.isValidHitpoints(0, idleUnit.getmaxHitpoints()));
	}
	
	@Test
	public void isValidHitpoints_negative(){
		assertFalse(Unit.isValidHitpoints(-1, idleUnit.getmaxHitpoints()));
	}
	
	@Test
	public void isValidHitpoints_tooLarge(){
		assertFalse(Unit.isValidHitpoints(idleUnit.getmaxHitpoints() + 1, idleUnit.getmaxHitpoints()));
	}
	
	@Test(expected = IllegalStateException.class)
	public void setToWork_whileMovingDistant(){
		movingDistantUnit.setToWork();
	}
	@Test(expected = IllegalStateException.class)
	public void setToWork_whileFighting(){
		idleUnit.startAttack(adjacentUnit);
		idleUnit.setToWork();
	}
	
	@Test(expected = IllegalStateException.class)
	public void setToWork_whileMovingAdjacent(){
		movingDistantUnit.setToWork();
	}
	
	@Test(expected = IllegalStateException.class)
	public void resting_whileMovingAdjacent(){
		movingAdjacentUnit.resting();
	}
	
	@Test(expected = IllegalStateException.class)
	public void startAttack_whileMovingAdjacent(){
		movingAdjacentUnit.startAttack(movingDistantUnit);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void startAttack_targetTooFar(){
		idleUnit.startAttack(distantUnit);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void startAttack_attackItself(){
		idleUnit.startAttack(idleUnit);
	}
	
	@Test
	public void startAttack_legalCase(){
		idleUnit.startAttack(adjacentUnit);
		assertTrue(idleUnit.getEnemy() == adjacentUnit);
		assertTrue(idleUnit.getSpeed().equals(new Vector(0,0,0)));
		assertTrue(adjacentUnit.getSpeed().equals(new Vector(0,0,0)));
		assertFalse(idleUnit.getSprinting());
		assertFalse(adjacentUnit.getSprinting());
		assertTrue(idleUnit.isAttacking());
		
	}
	
	
	
	@Test
	public void SettoWork_legalcase(){
		idleUnit.setToWork();
		assertTrue(idleUnit.isWorking());
	}
	@Test
	public void moveAdjacent_legalcase(){
		idleUnit.moveToAdjacent(0, 0, 1);
		assertTrue(idleUnit.isMoving());
	}
	@Test
	public void moveTo_legalcase() {
		idleUnit.moveTo(30, 30, 30);
		assertTrue(idleUnit.isMoving());
	}
	@Test(expected = IllegalStateException.class)
	public void movingAdjacent_whileFighting(){
		idleUnit.startAttack(adjacentUnit);
		idleUnit.moveToAdjacent(0, 0, 1);
	}
	@Test(expected = IllegalStateException.class)
	public void movingAdjacent_whilenotrestingenough(){
		idleUnit.resting();
		idleUnit.moveToAdjacent(0, 0, 1);
	}
	@Test(expected = IllegalArgumentException.class)
	public void movingAdjacent_whilenotvalidargument(){
		idleUnit.moveToAdjacent(0, 0, 5);
	}
	@Test(expected = IllegalStateException.class)
	public void moveto_whileFighting(){
		idleUnit.startAttack(adjacentUnit);
		idleUnit.moveTo(30, 30, 30);
	}
	@Test(expected = IllegalStateException.class)
	public void moveTo_whilenotrestingenough(){
		idleUnit.resting();
		idleUnit.moveTo(30, 30, 30);
		}
	@Test(expected = IllegalArgumentException.class)
	public void moveTo_whilenotvalidargument(){
		idleUnit.moveTo(55, 30, 30);
		}
	@Test(expected = IllegalStateException.class)
	public void resting_whileAttacking(){
		idleUnit.startAttack(adjacentUnit);
		idleUnit.resting();
	}
	@Test(expected = IllegalStateException.class)
	public void resting_whileDefending(){
		adjacentUnit.startAttack(idleUnit);
		idleUnit.resting();}
	@Test
	public void resting_legalcase(){
		idleUnit.resting();
		assertTrue(idleUnit.isResting());
	}
	@Test(expected = IllegalStateException.class)
	public void sprint_whileIdle(){
		idleUnit.setSprinting(true);}
	@Test
	public void sprinting_legalcase(){
		idleUnit.moveTo(30, 30, 30);
		idleUnit.setSprinting(true);
		assertTrue(idleUnit.getSprinting());
	}
	@Test
	public void defaultbehaviorsettings_legalcase(){
		idleUnit.setDefaultBehaviorBoolean(true);
		assertTrue(idleUnit.getdefaultbehaviorboolean());
	}
	@Test(expected = IllegalStateException.class)
	public void behavior_whilenotIdle(){
		idleUnit.moveTo(30, 30, 30);
		idleUnit.setDefaultBehaviorBoolean(true);
		}
	@Test
	public void defaultbehavior_works(){
		idleUnit.setDefaultBehaviorBoolean(true);
		idleUnit.advanceTime(0.1);
		assertFalse(idleUnit.isIdle());
	}
}
