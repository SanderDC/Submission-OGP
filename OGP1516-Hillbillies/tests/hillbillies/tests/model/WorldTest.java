package hillbillies.tests.model;

import static org.junit.Assert.*;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.kuleuven.cs.som.annotate.Model;
import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part2.listener.TerrainChangeListener;

public class WorldTest {
	private static World world3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int [][][] coordinates=new int[5][5][5];
		coordinates[1][1][1]=1;
		coordinates[1][1][2]=1;
		coordinates[0][0][0]=1;
		coordinates[1][2][2]=1;
		coordinates[1][3][2]=1;
		coordinates[1][1][3]=1;
		coordinates[3][1][2]=1;
		coordinates[2][1][2]=1;
		coordinates[0][1][0]=1;
		
		world3= new World(coordinates, new DefaultTerrainChangeListener());
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void isConnectedtoBorderWorld3(){
		assertFalse(world3.isSolidConnectedToBorder(new Vector(3, 1, 2)));
	}
	
	@Test
	public void tooManyUnits(){
		for (int i = 1; i <= 100; i++){
			new Unit(world3, false);
		}
		Unit test = new Unit(new Vector(0.5,0.5,0.5), 100,100,100,"John",100,false);
		world3.addGameObject(test);
		assertFalse(world3.hasAsUnit(test));
	}
	
	@Test
	public void tooManyFactions(){
		for (int i = 1; i <= 5; i++){
			new Unit(world3, false);
		}
		Faction test = new Faction(world3);
		assertFalse(world3.hasAsFaction(test));
	}

}
