package hillbillies.tests.model;

import static org.junit.Assert.*;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.kuleuven.cs.som.annotate.Model;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.World;
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
		
		world3= new World(coordinates,TerrainChangeListener);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void isConnectedtoBorderWorld3(){
		assertFalse(world3.isConnectedToBorder(3, 1, 2));
	}

}
