package hillbillies.tests.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.GameObject;
import hillbillies.model.Log;
import hillbillies.model.PathfindingException;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class UnitTest2 {

	
		private static Unit Idleunit;
		private static World world7;
		private static World world6;
		private static World world5;
		private static World world4;
		private static World world3;
		private static World world2;
		private static World world1;
		private static World world;
		private static Unit attackingunit;
		private static Unit defendingunit;
		private static Unit movingunit;
		private static Unit workunit;
		private static GameObject log;
		private static GameObject log1;
		private static GameObject boulder;
		private static GameObject boulder1;
		private static GameObject fallingbuilder;
		private static  Unit upgradeUnit;
		private static  Unit pickupUnit;
		private static  Unit pickupUnit1;
		
		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
			
			int [][][] coordinates3=new int[5][5][5];
			coordinates3[1][1][1]=1;
			coordinates3[1][1][2]=1;
			coordinates3[0][0][0]=1;
			coordinates3[1][2][2]=1;
			coordinates3[1][3][2]=1;
			coordinates3[1][1][3]=1;
			coordinates3[3][1][2]=1;
			coordinates3[2][1][2]=1;
			coordinates3[0][1][0]=1;
			
			world3= new World(coordinates3, new DefaultTerrainChangeListener());
			int [][][] coordinates1=new int[1][1][3];
			coordinates1[0][0][1]=1;
			world2= new World(coordinates1,new DefaultTerrainChangeListener());
			int [][][] coordinates2=new int[2][1][1];
			world1=new World(coordinates2,new DefaultTerrainChangeListener());
			int [][][] coordinates=new int[1][1][2];
			coordinates[0][0][0]=1;
			world= new World(coordinates, new DefaultTerrainChangeListener());
			int [][][] coordinates4=new int[1][1][1];
			coordinates4[0][0][0]=3;
			world4=new World(coordinates4, new DefaultTerrainChangeListener());
			int [][][] coordinates5=new int[1][1][1];
			world5=new World(coordinates5, new DefaultTerrainChangeListener());
			int [][][] coordinates6=new int[1][1][1];
			world6=new World(coordinates6, new DefaultTerrainChangeListener());
			world7=new World(coordinates6, new DefaultTerrainChangeListener());
			
		}

		@AfterClass
		public static void tearDownAfterClass() throws Exception {
		}

		@Before
		public void setUp() throws Exception {
			Idleunit= new Unit(world3, false);
			attackingunit=new Unit(world1, false);
			defendingunit=new Unit(world1, false);
			movingunit=new Unit(world2, false);
			workunit=new Unit(world, false);
			boulder= new Boulder(new Vector(0.5, 0.5, 0.5), world4);
			log= new Log(new Vector(0.5, 0.5, 0.5), world4);
			upgradeUnit= new Unit(world4, false);
			pickupUnit=new Unit(world5, false);
			pickupUnit1=new Unit(world6, false);
			boulder1= new Boulder(new Vector(0.5, 0.5, 0.5), world5);
			log1= new Log(new Vector(0.5, 0.5, 0.5), world6);
			fallingbuilder=new Boulder(new Vector(0.5, 0.5, 1.5), world);
		}

		@After
		public void tearDown() throws Exception {
		}
		
		@Test
		public void spawnedUnitHasLegalAttributes(){
			assertTrue(Idleunit.getAgility() >= 25||Idleunit.getAgility()<=100);
			assertTrue(Idleunit.getStrength() >= 25||Idleunit.getStrength()<=100);
			assertTrue(Idleunit.getToughness() >= 25||Idleunit.getToughness()<=100);
			assertTrue((Idleunit.getWeight() >= 25&&Idleunit.getWeight()<=100)&&Idleunit.getWeight()>=Idleunit.getMinWeight());
			assertTrue(Idleunit.getmaxHitpoints() ==Math.ceil((double) Idleunit.getToughness() * (double) Idleunit.getWeight() / 50.0));
			assertTrue(Idleunit.getmaxStamina() ==Math.ceil((double) Idleunit.getToughness() * (double) Idleunit.getWeight() / 50.0));
			assertTrue(Idleunit.getHitpoints() ==Math.ceil((double) Idleunit.getToughness() * (double) Idleunit.getWeight() / 50.0));
			assertTrue(Idleunit.getStamina() ==Math.ceil((double) Idleunit.getToughness() * (double) Idleunit.getWeight() / 50.0));
			assertTrue(Idleunit.getSpeed().equals(new Vector(0,0,0)));
			assertTrue(Idleunit.getNearTarget() == null);
			assertTrue(Idleunit.getEnemy()==null);
			assertTrue(Idleunit.getDistantTarget() == null);
			assertTrue(Idleunit.getName().equals("John"));
			assertFalse(Idleunit.getdefaultbehaviorboolean());
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void UnitIllegalMove(){
			Idleunit.moveTo(1,1,1);
		}
		@Test(expected = PathfindingException.class)
		public void UnitIllegalmove(){
			movingunit.moveTo(0, 0, 0);
			movingunit.moveTo(0, 0, 2);
		}
		@Test
		public void UnitLegalAttack(){
			attackingunit.startAttack(defendingunit);
			assertTrue(attackingunit.isAttacking());
			int totalstats= attackingunit.getAgility()+attackingunit.getStrength()+attackingunit.getToughness();
			int totalstats1= defendingunit.getAgility()+defendingunit.getStrength()+defendingunit.getToughness();

			for (int i = 0; i < 6; i++) {
				world1.advanceTime(0.19);
			}
			int totalstatsafter= attackingunit.getAgility()+attackingunit.getStrength()+attackingunit.getToughness();
			int totalstats1after= defendingunit.getAgility()+defendingunit.getStrength()+defendingunit.getToughness();


			assertTrue(totalstats<totalstatsafter||totalstats1<totalstats1after);
			
		}
		@Test
		public void caveInworkandfalls(){
			workunit.WorkAt(0, 0, 0);
			for (int i = 0; i < 101; i++) {
				world.advanceTime(0.2);
			}
			assertTrue(world.getCubeType(0, 0, 0)==0);
//			assertTrue(workunit.getHitpoints()<workunit.getmaxHitpoints());
			assertTrue(fallingbuilder.getPosition().getCubeZ()==0);

		}
		@Test
		public void upgrade(){
			upgradeUnit.WorkAt(0, 0, 0);
			int Toughnessbefore=upgradeUnit.getToughness();
			int weightbefore=upgradeUnit.getWeight();
			for (int i = 0; i < 101; i++) {
				world4.advanceTime(0.2);
			}			assertTrue(upgradeUnit.getToughness()>Toughnessbefore||upgradeUnit.getWeight()>weightbefore);
			
		}
		
		@Test
		public void pickupBoulder(){
			pickupUnit.WorkAt(0, 0, 0);
			for (int i = 0; i < 101; i++) {
				world5.advanceTime(0.2);
			}	
			assertTrue(pickupUnit.isCarryingBoulder());
		}
		@Test
		public void pickupLog(){
			pickupUnit1.WorkAt(0, 0, 0);
			for (int i = 0; i < 101; i++) {
				world6.advanceTime(0.2);
			}	
			assertTrue(pickupUnit1.isCarryingLog());
				
		}
		
	}


