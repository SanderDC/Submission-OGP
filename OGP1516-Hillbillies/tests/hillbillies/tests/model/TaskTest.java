package hillbillies.tests.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.World;
import hillbillies.model.expressions.HerePositionExpression;
import hillbillies.model.statements.MoveToStatement;
import hillbillies.model.statements.Statement;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.programs.SourceLocation;

public class TaskTest {
	
	private static World world;
	private static Task task, task1;
	private static Unit unit, unit2;
	private static Scheduler scheduler;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		world = new World(new int[3][3][3], new DefaultTerrainChangeListener());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		unit = new Unit(world, false);
		unit2 = new Unit(world, false);
		SourceLocation sourceLocation = new SourceLocation(1, 1);
		Statement stmt = new MoveToStatement(new HerePositionExpression(sourceLocation), sourceLocation);
		task = new Task("test",0,stmt.clone());
		task1 = new Task("test",0,stmt.clone());
		scheduler = unit.getFaction().getScheduler();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void assignToUnit_legalCase() {
		task.assignToUnit(unit);
		assertTrue(task.isBeingExecuted());
		assertTrue(task.getUnit() == unit);
		assertTrue(unit.getTask() == task);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void assignToUnit_null(){
		task.assignToUnit(null);
	}
	
	@Test(expected = IllegalStateException.class)
	public void assignToUnit_alreadyHasTask(){
		task.assignToUnit(unit);
		task1.assignToUnit(unit);
	}
	
	@Test(expected = IllegalStateException.class)
	public void assignToUnit_alreadyInExecution(){
		task.assignToUnit(unit);
		task.assignToUnit(unit2);
	}
	
	@Test(expected = IllegalStateException.class)
	public void assignToUnit_terminated(){
		task.terminate();
		task.assignToUnit(unit);
	}
	
	@Test
	public void terminate(){
		scheduler.addTasks(task);
		task.assignToUnit(unit);
		for (int i = 0; i < 10; i++){
			world.advanceTime(0.2);
		}
		assertTrue(task.isTerminated());
		assertFalse(task.isBeingExecuted());
		assertFalse(unit.hasTask());
		assertTrue(task.getUnit() == null);
		assertFalse(scheduler.hasAsTask(task));
		assertFalse(task.hasAsScheduler(scheduler));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setName_null(){
		task.setName(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setName_empty(){
		task.setName("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void storeVariable_IllegalArgument(){
		task.storeVariable("test", new Object());
	}
	
	@Test
	public void storeVariable_Vector(){
		Vector vector = new Vector(0,0,0);
		task.storeVariable("test", vector);
		assertTrue(task.getVariable("test") == vector);
	}
	
	@Test
	public void storeVariable_Unit(){
		task.storeVariable("test", unit);
		assertTrue(task.getVariable("test") == unit);
	}
	
	@Test
	public void storeVariable_Boolean(){
		Boolean test = Boolean.valueOf(true);
		task.storeVariable("test", test);
		assertTrue(task.getVariable("test") == test);
	}
}
