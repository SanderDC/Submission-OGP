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

public class WorldTest {

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int [][][] coordinates=new int[3][3][3];
		coordinates[1][1][1]=1;
		World world3= new World(coordinates);
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void isConnectedtoBorderWorld3(){
		world3
	}

}
