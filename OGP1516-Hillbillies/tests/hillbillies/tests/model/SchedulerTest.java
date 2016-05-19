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
import hillbillies.model.World;
import hillbillies.model.expressions.HerePositionExpression;
import hillbillies.model.statements.MoveToStatement;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class SchedulerTest {
	
	private static Scheduler scheduler1;
	private static Task task,task1,task2,task3;
	private static Unit unit;
	private static World world;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		
		world= new World(coordinates, new DefaultTerrainChangeListener());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		scheduler1 = new Scheduler();
		task = new Task("test", 0, new MoveToStatement(new HerePositionExpression(null)));
		task1 = new Task("test", 0, new MoveToStatement(new HerePositionExpression(null)));
		task2 = new Task("test", 0, new MoveToStatement(new HerePositionExpression(null)));
		task3 = new Task("test", 0, new MoveToStatement(new HerePositionExpression(null)));
		unit= new Unit(world, false);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addOneTask() {
		scheduler1.addTasks(task);
		assertTrue(scheduler1.hasAsTask(task));
		assertTrue(task.hasAsScheduler(scheduler1));
	}
	
	@Test
	public void addMultipleTasks(){
		scheduler1.addTasks(task1,task2,task3);
		assertTrue(scheduler1.hasAsTask(task1));
		assertTrue(task1.hasAsScheduler(scheduler1));
		assertTrue(scheduler1.hasAsTask(task2));
		assertTrue(task2.hasAsScheduler(scheduler1));
		assertTrue(scheduler1.hasAsTask(task3));
		assertTrue(task3.hasAsScheduler(scheduler1));
	}
	
	@Test
	public void addTask_Illegal(){
		task.terminate();
		scheduler1.addTasks(task);
		assertFalse(scheduler1.hasAsTask(task));
		assertFalse(task.hasAsScheduler(scheduler1));
	}
	
	@Test(expected = IllegalStateException.class)
	public void assignTask_Illegal(){
		task1.terminate();
		task1.assignToUnit(unit);
		assertFalse(unit.getTask()==task1);
	}
	@Test
	public void assignTask(){
		task1.assignToUnit(unit);
		assertTrue(unit.getTask()==task1);
		
	}
	
}
