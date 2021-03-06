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
import hillbillies.model.statements.Statement;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.programs.SourceLocation;

public class SchedulerTest {
	
	private static Scheduler scheduler1;
	private static Task task,task1,task2,task3;
	private static Unit unit;
	private static World world;
	private static Statement testStatement;
	

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
//		Faction faction = new Faction(new World(new int[3][3][3], new DefaultTerrainChangeListener()));
//		scheduler1 = faction.getScheduler();
		SourceLocation testLocation = new SourceLocation(1, 1);
		testStatement = new MoveToStatement(new HerePositionExpression(testLocation), testLocation);
		task = new Task("test", -10, testStatement);
		task1 = new Task("test", 0, testStatement);
		task2 = new Task("test", 10, testStatement);
		task3 = new Task("test", 20 , testStatement);
		unit= new Unit(world, false);
		scheduler1 = unit.getFaction().getScheduler();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addOneTask_legalCase() {
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
	public void assignTask_terminated(){
		task1.terminate();
		task1.assignToUnit(unit);
		assertFalse(unit.getTask()==task1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void assignTask_wrongUnit(){
		Unit wrongUnit = new Unit(world, false);
		scheduler1.assignTaskToUnit(wrongUnit, task);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void assignTask_null(){
		scheduler1.assignTaskToUnit(unit, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void assignTask_notInThisScheduler(){
		Task task = new Task("test",100,testStatement);
		scheduler1.assignTaskToUnit(unit, task);
	}
	
	@Test(expected = IllegalStateException.class)
	public void assignTask_alreadyInExecution(){
		scheduler1.addTasks(task);
		scheduler1.assignTaskToUnit(unit, task);
		scheduler1.assignTaskToUnit(unit, task);
	}
	
	@Test
	public void assignTask_legalCase(){
		scheduler1.addTasks(task);
		scheduler1.assignTaskToUnit(unit, task);
		assertTrue(unit.getTask()==task);
		assertTrue(task.getUnit() == unit);
	}
	
	@Test
	public void getTopPriorityTask(){
		scheduler1.addTasks(task,task1,task2,task3);
		assertTrue(scheduler1.getTopPriorityTask() == task3);
	}
	
	@Test
	public void replaceTask_legalCase(){
		scheduler1.addTasks(task,task1,task2);
		scheduler1.replaceTask(task2, task3);
		assertTrue(scheduler1.hasAsTask(task3));
		assertFalse(scheduler1.hasAsTask(task2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void replaceTask_IllegalCase(){
		scheduler1.replaceTask(task1, task2);
	}
	
	@Test
	public void removeTaskFromUnit_legalCase(){
		scheduler1.addTasks(task);
		scheduler1.assignTaskToUnit(unit, task);
		assertTrue(unit.getTask()==task);
		assertTrue(task.getUnit() == unit);
		scheduler1.removeTaskFromUnit(unit);
		assertTrue(unit.getTask() == null);
		assertTrue(task.getUnit() == null);
		assertFalse(task.isBeingExecuted());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeTask_null(){
		scheduler1.removeTaskFromUnit(null);
	}
	
	@Test(expected = IllegalStateException.class)
	public void removeTask_noTask(){
		scheduler1.removeTaskFromUnit(unit);
	}
}
