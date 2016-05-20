package hillbillies.tests.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Log;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.World;
import hillbillies.model.expressions.*;
import hillbillies.model.statements.AttackStatement;
import hillbillies.model.statements.FollowStatement;
import hillbillies.model.statements.MoveToStatement;
import hillbillies.model.statements.SequenceStatement;
import hillbillies.model.statements.Statement;
import hillbillies.model.statements.WorkStatement;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.programs.SourceLocation;

public class StatementsTests {
	static SourceLocation sourcelocation;
	static Statement statement;
	static Task task,task2;
	static World world, world1;
	static Unit unit1,unit2, unit3,unit4,unit5;
	
	static Boulder boulder1,boulder2;
	static Log log1, log2;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		sourcelocation= new SourceLocation(0, 0);
		int [][][] coordinates= new int [5][1][1];
		coordinates[4][0][0]=2;
		world= new World(coordinates, new DefaultTerrainChangeListener());
		
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
	public void testAttack(){
		task= new Task("name", 0, new AttackStatement(new EnemyUnitExpression(sourcelocation), sourcelocation));
		unit1.moveTo(0, 0, 0);
		unit2.moveTo(0, 0, 0);
		unit3.moveTo(2, 0, 0);
		for (int i = 0; i < 50; i++) {
			world.advanceTime(0.19);
		}
		task.assignToUnit(unit1);
		unit1.setDefaultBehaviorBoolean(true);
		unit1.advanceTime(0.01);
		assertTrue(unit1.getEnemy()==unit2);
		
	}
	@Test
	public void testMove(){
		LiteralPositionExpression literalPositionExpression = new LiteralPositionExpression(3, 0, 0, sourcelocation);
		literalPositionExpression.addToTask(task);
		task= new Task("name", 0, new MoveToStatement(literalPositionExpression, sourcelocation));
		unit1.moveTo(0, 0, 0);
		
		for (int i = 0; i < 100; i++) {
			unit1.advanceTime(0.19);
		}
		task.assignToUnit(unit1);
		unit1.setDefaultBehaviorBoolean(true);
		unit1.advanceTime(0.01);
		assertTrue(unit1.isMoving());
		while (unit1.isMoving()) {
			unit1.advanceTime(0.19);
		} 
			
		
		assertTrue(unit1.getPosition().getCubeX()==3);
	}
	@Test
	public void testfollow(){
		AnyUnitExpression anyUnitExpression= new AnyUnitExpression(sourcelocation);
		anyUnitExpression.addToTask(task);
		task= new Task("name", 0, new FollowStatement(anyUnitExpression,sourcelocation));
		unit1.moveTo(0, 0, 0);
		unit2.moveTo(2, 0, 0);
		unit3.moveTo(3, 0, 0);
		for (int i = 0; i < 50; i++) {
			world.advanceTime(0.19);
		}
		task.assignToUnit(unit1);
		unit1.setDefaultBehaviorBoolean(true);
		unit1.advanceTime(0.01);
		assertTrue(unit1.isMoving());
		while (unit1.isMoving()) {
			unit1.advanceTime(0.19);
		} 
			
		
		assertTrue(unit1.getPosition().getCubeX()==2);
	}
	@Test
	public void testWorkandMove(){
		BoulderPositionExpression boulderPositionExpression= new BoulderPositionExpression(sourcelocation);
		boulderPositionExpression.addToTask(task);
		task= new Task("name", 0, new MoveToStatement(boulderPositionExpression,sourcelocation));
		unit1.moveTo(0, 0, 0);
		for (int i = 0; i < 50; i++) {
			world.advanceTime(0.19);
		}
		task.assignToUnit(unit1);
		unit1.setDefaultBehaviorBoolean(true);
		unit1.advanceTime(0.01);
		assertTrue(unit1.isMoving());
		while (unit1.isMoving()) {
			unit1.advanceTime(0.19);
		} 
		assertTrue(unit1.getPosition().getCubeX()==1);

		HerePositionExpression herePositionExpression= new HerePositionExpression(sourcelocation);
		herePositionExpression.addToTask(task);
		task2= new Task("name", 0, new WorkStatement(herePositionExpression,sourcelocation));
		task2.assignToUnit(unit1);
		unit1.advanceTime(0.19);
		assertTrue(unit1.isWorking());
		while (unit1.isWorking()) {
			unit1.advanceTime(0.19);
		}
		assertTrue(unit1.isCarryingBoulder());
	}
	

}
