package hillbillies.tests.expressions;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.omg.CORBA.Any;

import hillbillies.model.Boulder;
import hillbillies.model.Log;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.model.expressions.*;
import hillbillies.model.statements.SequenceStatement;
import hillbillies.model.statements.Statement;
import hillbillies.part3.programs.SourceLocation;

public class ExpressionsTest {
		
	static SourceLocation sourcelocation;
	static Statement statement;
	static Task task;
	static World world;
	static Unit unit1;
	static Unit unit2;
	static Unit unit3;
	static Boulder boulder1,boulder2;
	static Log log1, log2;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SourceLocation sourceLocation= new SourceLocation(0, 0);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int [][][] coordinates= new int [5][1][1];
		coordinates[4][0][0]=2;
		
		world= new World(coordinates, new DefaultTerrainChangeListener());
		task=new Task("name", 0, new SequenceStatement(new ArrayList<>()) );
		unit1=new Unit(world, false);
		unit2=new Unit(world, false);
		unit3=new Unit(world, false);
		log1= new Log(new Vector(0.5, 0.5, 0.5), world);
		log2= new Log(new Vector(2.5, 0.5, 0.5), world);
		boulder1= new Boulder(new Vector(1.5, 0.5, 0.5), world);
		boulder2= new Boulder(new Vector(3.5, 0.5, 0.5), world);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void anyUnit() {
		AnyUnitExpression anyUnitExpression =new AnyUnitExpression(sourcelocation);
		anyUnitExpression.addToTask(task);
		unit1.moveTo(0, 0, 0);
		unit2.moveTo(0, 0, 0);
		unit3.moveTo(2, 0, 0);
		for (int i = 0; i < 50; i++) {
			world.advanceTime(0.19);

		}
		task.assignToUnit(unit1);
		assertTrue(anyUnitExpression.evaluate()==unit2);
		
	}
	@Test
	public void isSolid() {
		IsSolidBoolean isSolidBoolean =new IsSolidBoolean(new LiteralPositionExpression(4, 0, 0,sourcelocation), sourcelocation);
		isSolidBoolean.addToTask(task);
		task.assignToUnit(unit1);

		assertTrue(isSolidBoolean.evaluate());

		
		
			}
	@Test
	public void isThis() {
		ThisUnitExpression thisUnitExpression =new ThisUnitExpression(sourcelocation);
		thisUnitExpression.addToTask(task);
		task.assignToUnit(unit1);

		assertTrue(thisUnitExpression.evaluate()==unit1);
	}
	@Test
	public void trueexpression() {
		TrueBooleanExpression trueBooleanExpression =new TrueBooleanExpression(sourcelocation);
		trueBooleanExpression.addToTask(task);
		task.assignToUnit(unit1);

		assertTrue(trueBooleanExpression.evaluate());
	}
	@Test
	public void falseExpression() {
		FalseBooleanExpression falseBooleanExpression =new FalseBooleanExpression(sourcelocation);
		falseBooleanExpression.addToTask(task);
		task.assignToUnit(unit1);

		assertTrue(!falseBooleanExpression.evaluate());
	}
	@Test
	public void nextTo() {
		NextToPositionExpression nextToPositionExpression =new NextToPositionExpression(new LiteralPositionExpression(0, 0, 0, sourcelocation),sourcelocation);
		nextToPositionExpression.addToTask(task);
		
		task.assignToUnit(unit1);
		assertTrue(nextToPositionExpression.evaluate().equals(new Vector(1, 0, 0)));
		
	}
	@Test
	public void Isalive() {
		IsAliveBoolean isAliveBoolean =new IsAliveBoolean(new ThisUnitExpression(sourcelocation),sourcelocation);
		isAliveBoolean.addToTask(task);
		task.assignToUnit(unit1);
		assertTrue(isAliveBoolean.evaluate());
	}
	@Test
	public void ReadUnit() {
		ThisUnitExpression thisUnitExpression1=new ThisUnitExpression(sourcelocation);
		thisUnitExpression1.addToTask(task);
		task.storeVariableExpression("test", thisUnitExpression1);
		ReadUnitExpression readUnitExpression =new ReadUnitExpression("test",sourcelocation);
		readUnitExpression.addToTask(task);
		
		task.assignToUnit(unit1);
		assertTrue(readUnitExpression.evaluate()==unit1);
	}
	
	@Test
	public void anyLog() {
		LogPositionExpression logPositionExpression =new LogPositionExpression(sourcelocation);
		logPositionExpression.addToTask(task);
		unit1.moveTo(0, 0, 0);
		for (int i = 0; i < 100; i++) {
			world.advanceTime(0.19);

		}
		task.assignToUnit(unit1);
		assertTrue(logPositionExpression.evaluate().equals(log1.getPosition()));
		
	}
	@Test
	public void anyBoulder() {
		BoulderPositionExpression boulderPositionExpression =new BoulderPositionExpression(sourcelocation);
		boulderPositionExpression.addToTask(task);
		unit1.moveTo(0, 0, 0);
		for (int i = 0; i < 100; i++) {
			world.advanceTime(0.19);

		}
		task.assignToUnit(unit1);
		assertTrue(boulderPositionExpression.evaluate().equals(boulder1.getPosition()));
		
	}
	@Test
	public void enemyUnit() {
		EnemyUnitExpression enemyUnitExpression =new EnemyUnitExpression(sourcelocation);
		enemyUnitExpression.addToTask(task);
		unit1.moveTo(0, 0, 0);
		unit2.moveTo(0, 0, 0);
		unit3.moveTo(2, 0, 0);
		for (int i = 0; i < 50; i++) {
			world.advanceTime(0.19);

		}
		task.assignToUnit(unit1);
		assertTrue(enemyUnitExpression.evaluate()==unit2);
		
	}
	
}
