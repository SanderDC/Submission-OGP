package hillbillies.tests.model;

import static org.junit.Assert.*;

import hillbillies.model.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SchedulerTest {
	
	private static Scheduler scheduler1;
	private static Task task,task1,task2,task3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		scheduler1 = new Scheduler();
		task = new Task("test", 0, null);
		task1 = new Task("test", 0, null);
		task2 = new Task("test", 0, null);
		task3 = new Task("test", 0, null);
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

}
