package hillbillies.tests.model;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Vector;
import ogp.framework.util.Util;

public class VectorTest {
	
	private static Vector firstquadrantpositive1,firstquadrantpositive2,firstquadrantpositive3,
							secondquadrantpositive1,secondquadrantpositive2,secondquadrantpositive3,
							thirdquadrantpositive1,thirdquadrantpositive2,thirdquadrantpositive3,
							fourthquadrantpositive1,fourthquadrantpositive2,fourthquadrantpositive3,
							firstquadrantnegative1,firstquadrantnegative2,firstquadrantnegative3,
							secondquadrantnegative1,secondquadrantnegative2,secondquadrantnegative3,
							thirdquadrantnegative1,thirdquadrantnegative2,thirdquadrantnegative3,
							fourthquadrantnegative1,fourthquadrantnegative2,fourthquadrantnegative3,
							nullvector;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nullvector = new Vector(0,0,0);
		
		firstquadrantpositive1 = new Vector(1.0,1.0,1.0);
		firstquadrantpositive2 = new Vector(2.0, 2.0, 2.0);
		firstquadrantpositive3 = new Vector(3.0,3.0,3.0);
		secondquadrantpositive2 = new Vector(-2.0, 2.0, 2.0);
		secondquadrantpositive1 = new Vector(-1.0,1.0,1.0);
		secondquadrantpositive3 = new Vector(-3.0,3.0,3.0);
		thirdquadrantpositive2 = new Vector(-2.0, -2.0, 2.0);
		thirdquadrantpositive1 = new Vector(-1.0, -1.0, 1.0);
		thirdquadrantpositive3 = new Vector(-3.0, -3.0, 3.0);
		fourthquadrantpositive2 = new Vector(2.0, -2.0, 2.0);
		fourthquadrantpositive1 = new Vector(1.0, -1.0, 1.0);
		fourthquadrantpositive3 = new Vector(3.0, -3.0, 3.0);
		
		firstquadrantnegative1 = new Vector(1.0,1.0,-1.0);
		firstquadrantnegative2 = new Vector(2.0, 2.0, -2.0);
		firstquadrantnegative3 = new Vector(3.0,3.0,-3.0);
		secondquadrantnegative2 = new Vector(-2.0, 2.0, -2.0);
		secondquadrantnegative1 = new Vector(-1.0,1.0,-1.0);
		secondquadrantnegative3 = new Vector(-3.0,3.0,-3.0);
		thirdquadrantnegative2 = new Vector(-2.0, -2.0, -2.0);
		thirdquadrantnegative1 = new Vector(-1.0, -1.0, -1.0);
		thirdquadrantnegative3 = new Vector(-3.0, -3.0, -3.0);
		fourthquadrantnegative2 = new Vector(2.0, -2.0, -2.0);
		fourthquadrantnegative1 = new Vector(1.0, -1.0, -1.0);
		fourthquadrantnegative3 = new Vector(3.0, -3.0, -3.0);
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
	public void liesBetween_smaller_larger() {
		assertTrue(firstquadrantpositive2.liesBetween(firstquadrantpositive1, firstquadrantpositive3));
	}
	
	@Test
	public void liesBetween_larger_smaller(){
		assertTrue(firstquadrantpositive2.liesBetween(firstquadrantpositive3, firstquadrantpositive1));
	}
	
	@Test
	public void liesBetween_firstQuadrantNegative(){
		assertTrue(firstquadrantnegative2.liesBetween(firstquadrantnegative1, firstquadrantnegative3));
		assertTrue(firstquadrantnegative2.liesBetween(firstquadrantnegative3, firstquadrantnegative1));
		assertFalse(firstquadrantnegative1.liesBetween(firstquadrantnegative2, firstquadrantnegative3));
		assertFalse(firstquadrantnegative3.liesBetween(firstquadrantnegative1, firstquadrantnegative2));
	}
	
	@Test
	public void liesBetween_false(){
		assertFalse(firstquadrantpositive1.liesBetween(firstquadrantpositive2, firstquadrantpositive3));
		assertFalse(firstquadrantpositive3.liesBetween(firstquadrantpositive1, firstquadrantpositive2));
	}
	
	@Test
	public void liesBetween_secondQuadrantPositive(){
		assertTrue(secondquadrantpositive2.liesBetween(secondquadrantpositive1, secondquadrantpositive3));
		assertTrue(secondquadrantpositive2.liesBetween(secondquadrantpositive3, secondquadrantpositive1));
		assertFalse(secondquadrantpositive1.liesBetween(secondquadrantpositive2, secondquadrantpositive3));
		assertFalse(secondquadrantpositive3.liesBetween(secondquadrantpositive1, secondquadrantpositive2));
	}
	
	@Test
	public void liesBetween_secondQuadrantNegative(){
		assertTrue(secondquadrantnegative2.liesBetween(secondquadrantnegative1, secondquadrantnegative3));
		assertTrue(secondquadrantnegative2.liesBetween(secondquadrantnegative3, secondquadrantnegative1));
		assertFalse(secondquadrantnegative1.liesBetween(secondquadrantnegative2, secondquadrantnegative3));
		assertFalse(secondquadrantnegative3.liesBetween(secondquadrantnegative1, secondquadrantnegative2));
	}
	
	@Test
	public void liesBetween_thirdQuadrantPositive(){
		assertTrue(thirdquadrantpositive2.liesBetween(thirdquadrantpositive1, thirdquadrantpositive3));
		assertTrue(thirdquadrantpositive2.liesBetween(thirdquadrantpositive3, thirdquadrantpositive1));
		assertFalse(thirdquadrantpositive1.liesBetween(thirdquadrantpositive2, thirdquadrantpositive3));
		assertFalse(thirdquadrantpositive3.liesBetween(thirdquadrantpositive1, thirdquadrantpositive2));
	}
	
	@Test
	public void liesBetween_thirdQuadrantNegative(){
		assertTrue(thirdquadrantnegative2.liesBetween(thirdquadrantnegative1, thirdquadrantnegative3));
		assertTrue(thirdquadrantnegative2.liesBetween(thirdquadrantnegative3, thirdquadrantnegative1));
		assertFalse(thirdquadrantnegative1.liesBetween(thirdquadrantnegative2, thirdquadrantnegative3));
		assertFalse(thirdquadrantnegative3.liesBetween(thirdquadrantnegative1, thirdquadrantnegative2));
	}
	
	@Test
	public void liesBetween_fourthQuadrantPositive(){
		assertTrue(fourthquadrantpositive2.liesBetween(fourthquadrantpositive1, fourthquadrantpositive3));
		assertTrue(fourthquadrantpositive2.liesBetween(fourthquadrantpositive3, fourthquadrantpositive1));
		assertFalse(fourthquadrantpositive1.liesBetween(fourthquadrantpositive2, fourthquadrantpositive3));
		assertFalse(fourthquadrantpositive3.liesBetween(fourthquadrantpositive1, fourthquadrantpositive2));
	}
	
	@Test
	public void liesBetween_fourthQuadrantNegative(){
		assertTrue(fourthquadrantnegative2.liesBetween(fourthquadrantnegative1, fourthquadrantnegative3));
		assertTrue(fourthquadrantnegative2.liesBetween(fourthquadrantnegative3, fourthquadrantnegative1));
		assertFalse(fourthquadrantnegative1.liesBetween(fourthquadrantnegative2, fourthquadrantnegative3));
		assertFalse(fourthquadrantnegative3.liesBetween(fourthquadrantnegative1, fourthquadrantnegative2));
	}
	
	@Test
	public void liesBetween_nullVector(){
		assertTrue(nullvector.liesBetween(thirdquadrantnegative1, firstquadrantpositive1));
		assertTrue(nullvector.liesBetween(thirdquadrantpositive1, firstquadrantnegative1));
		assertTrue(nullvector.liesBetween(secondquadrantnegative1, fourthquadrantpositive1));
		assertTrue(nullvector.liesBetween(secondquadrantpositive1, fourthquadrantnegative1));
		assertFalse(nullvector.liesBetween(firstquadrantnegative1, secondquadrantpositive1));
		assertFalse(nullvector.liesBetween(secondquadrantnegative1, thirdquadrantpositive1));
		assertFalse(nullvector.liesBetween(thirdquadrantnegative1, fourthquadrantpositive1));
		assertFalse(nullvector.liesBetween(fourthquadrantnegative1, firstquadrantpositive1));
	}
	
	@Test
	public void liesBetween_differentQuadrants(){
		assertTrue(firstquadrantpositive1.liesBetween(thirdquadrantnegative1, firstquadrantpositive2));
		assertTrue(firstquadrantnegative1.liesBetween(thirdquadrantpositive1, firstquadrantnegative2));
		assertTrue(secondquadrantpositive1.liesBetween(fourthquadrantnegative1, secondquadrantpositive2));
		assertTrue(secondquadrantnegative1.liesBetween(fourthquadrantpositive1, secondquadrantnegative2));
	}
	
	@Test
	public void Constructor_legalCase(){
		Vector test;
		test = new Vector(0,0,0);
		assertTrue(Util.fuzzyEquals(test.getX(), 0));
		assertTrue(Util.fuzzyEquals(test.getY(), 0));
		assertTrue(Util.fuzzyEquals(test.getZ(), 0));
		test = new Vector (-51023, 5480, Double.MAX_VALUE);
		assertTrue(Util.fuzzyEquals(test.getX(), -51023));
		assertTrue(Util.fuzzyEquals(test.getY(), 5480));
		assertTrue(Util.fuzzyEquals(test.getZ(), Double.MAX_VALUE));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Constructor_illegalCase() throws Exception{
		new Vector(0,0,Double.POSITIVE_INFINITY);
	}
	
	@Test
	public void toArray(){
		double[] test = firstquadrantpositive3.toArray();
		assertTrue(Util.fuzzyEquals(firstquadrantpositive3.getX(), test[0]));
		assertTrue(Util.fuzzyEquals(firstquadrantpositive3.getY(), test[1]));
		assertTrue(Util.fuzzyEquals(firstquadrantpositive3.getZ(), test[2]));
	}
	
	@Test
	public void equals(){
		Vector firstquadrantpositive1clone = new Vector(firstquadrantpositive1.getX(), firstquadrantpositive1.getY(), 
														firstquadrantpositive1.getZ());
		assertTrue(firstquadrantpositive1clone.equals(firstquadrantpositive1));
		assertTrue(firstquadrantpositive1clone.hashCode() == firstquadrantpositive1.hashCode());
		Vector nullvectorclone = new Vector(0,0,0);
		assertTrue(nullvectorclone.equals(nullvector));
	}
	
	@Test
	public void add_legalCase(){
		Vector test;
		test = firstquadrantpositive1.add(firstquadrantpositive2);
		assertTrue(test.equals(firstquadrantpositive3));
		test = thirdquadrantnegative1.add(thirdquadrantnegative2);
		assertTrue(test.equals(thirdquadrantnegative3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void add_illegalCase(){
		Vector test1 = new Vector(0.5*Double.MAX_VALUE,0,0);
		Vector test2 = new Vector(0.6*Double.MAX_VALUE,0,0);
		test1.add(test2);
	}
	
	@Test
	public void scalarMultiply_legalCase(){
		Vector test = firstquadrantpositive1.scalarMultiply(20);
		assertTrue(Util.fuzzyEquals(test.getX(), 20));
		assertTrue(Util.fuzzyEquals(test.getY(), 20));
		assertTrue(Util.fuzzyEquals(test.getZ(), 20));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void scalarMultiply_illegalCasePositive(){
		firstquadrantpositive3.scalarMultiply(Double.MAX_VALUE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void scalarMultiply_illegalCaseOppositeSign(){
		firstquadrantnegative3.scalarMultiply(Double.MAX_VALUE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void scalarMultiply_illegalCaseNegative(){
		firstquadrantnegative3.scalarMultiply(-Double.MAX_VALUE);
	}
	
	@Test
	public void norm(){
		assertTrue(Util.fuzzyEquals(firstquadrantnegative1.norm(), secondquadrantpositive1.norm()));
		assertTrue(Util.fuzzyEquals(firstquadrantpositive1.norm(), Math.sqrt(3)));
		assertTrue(Util.fuzzyEquals(firstquadrantnegative3.norm(), 3*Math.sqrt(3)));
		assertTrue(Util.fuzzyEquals(new Vector(1,0,0).norm(), 1));
		assertTrue(Util.fuzzyEquals(new Vector(0,-1,0).norm(), 1));
		assertTrue(Util.fuzzyEquals(nullvector.norm(), 0));
	}
	
	@Test
	public void normalize(){
		assertTrue(nullvector.normalize().equals(nullvector));
		assertTrue(firstquadrantpositive3.normalize().equals(firstquadrantpositive3.scalarMultiply(1/firstquadrantpositive3.norm())));
		assertTrue(Util.fuzzyEquals(thirdquadrantnegative2.normalize().norm(), 1));
	}
	
	
}
