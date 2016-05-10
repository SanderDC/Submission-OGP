package hillbillies.part3.programs;
import hillbillies.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import hillbillies.model.Boulder;
import hillbillies.model.Log;
import hillbillies.model.Task;
import hillbillies.model.statements.*;
import hillbillies.model.expressions.*;

public class TaskFactory implements ITaskFactory<Expression, Statement, Task> {

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		
		 List<Task> tasklist= new ArrayList<>();
		for (int i[] : selectedCubes) {
			tasklist.add(new Task(name, priority, activity));
		}
		return tasklist;
			
		
	}

	@Override
	public Statement createAssignment(String variableName, Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new CreateWhile(condition, body);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return  new CreateIf(condition, ifBody, elseBody);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new CreatePrintStatement(value);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new SequenceStatement(statements);
	}

	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new CreateMove( position);
	}

	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new CreateWork(position);
	}

	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {
		return new attackStatement(unit);
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		return new attackStatement(unit);
		
	}

	@Override
	public Expression createReadVariable(String variableName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ReadVariable( variableName);
	}

	@Override
	public Expression createIsSolid(Expression position, SourceLocation sourceLocation) {
		return new IsSolidBoolean((PositionExpression) position);
	}

	@Override
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation) {
		return new IsPassableBoolean((PositionExpression) position);
	}

	@Override
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {
		return new IsFriendBoolean((UnitExpression) unit);
	}

	@Override
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {
		return new IsEnemyBoolean((UnitExpression) unit);
	}

	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {
		return new IsAliveBoolean((UnitExpression) unit);
	}

	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		return new CarriesItemBoolean((UnitExpression) unit);
	}

	@Override
	public Expression createNot(Expression expression, SourceLocation sourceLocation) {
		return new NotBooleanExpression((BooleanExpression) expression);
	}

	@Override
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		return new AndBooleanExpression((BooleanExpression) left, (BooleanExpression) right);
	}

	@Override
	public Expression createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		return new OrBooleanExpression((BooleanExpression) left, (BooleanExpression) right);
	}

	@Override
	public Expression createHerePosition(SourceLocation sourceLocation) {
		return new HerePositionExpression();
	}

	@Override
	public Expression createLogPosition(SourceLocation sourceLocation) {
		return new GameObjectPositionExpression<Log>();
	}

	@Override
	public Expression createBoulderPosition(SourceLocation sourceLocation) {
		return new GameObjectPositionExpression<Boulder>();
	}

	@Override
	public Expression createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopPositionExpression();
	}

	@Override
	public Expression createSelectedPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createNextToPosition(Expression position, SourceLocation sourceLocation) {
		return new NextToPositionExpression((PositionExpression) position);
	}

	@Override
	public Expression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new LiteralPositionExpression(x, y, z);
	}

	@Override
	public Expression createThis(SourceLocation sourceLocation) {
		return new ThisUnitExpression();
	}

	@Override
	public Expression createFriend(SourceLocation sourceLocation) {
		return new FriendUnitExpression();
	}

	@Override
	public Expression createEnemy(SourceLocation sourceLocation) {
		return new EnemyUnitExpression();
	}

	@Override
	public Expression createAny(SourceLocation sourceLocation) {
		return new AnyUnitExpression();
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		return new TrueBooleanExpression();
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new FalseBooleanExpression();
	}

}
