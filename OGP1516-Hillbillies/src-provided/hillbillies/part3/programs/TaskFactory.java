package hillbillies.part3.programs;
import hillbillies.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


import hillbillies.model.Boulder;
import hillbillies.model.Log;
import hillbillies.model.Task;
import hillbillies.model.statements.*;
import hillbillies.model.expressions.*;

public class TaskFactory implements ITaskFactory<Expression, Statement, Task> {
	
	private HashMap<String,Class<? extends Expression>> types = new HashMap<>();

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		
		 List<Task> tasklist= new ArrayList<>();
		if (selectedCubes.size()!=0) {
			for (int i[] : selectedCubes) {
				tasklist.add(new Task(name, priority, activity));
			}
		}
		else {
			tasklist.add(new Task(name, priority, activity));
		}
		
		return tasklist;
			
		
	}

	@Override
	public Statement createAssignment(String variableName, Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		this.types.put(variableName, value.getClass());
		return null;
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new WhileStatement((BooleanExpression) condition, body);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return  new IfStatement((BooleanExpression) condition, ifBody, elseBody);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new PrintStatement((ReadVariable) value);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new SequenceStatement(statements);
	}

	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new MoveToStatement( (PositionExpression) position);
	}

	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new WorkStatement((PositionExpression) position);
	}

	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {
		return new FollowStatement((UnitExpression) unit);
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		return new AttackStatement((EnemyUnitExpression) unit);
		
	}

	@Override
	public Expression createReadVariable(String variableName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		if (!types.containsKey(variableName))
			return null;
		else if (PositionExpression.class.isAssignableFrom(types.get(variableName)))
			return new ReadPositionExpression(variableName, sourceLocation);
		else if (BooleanExpression.class.isAssignableFrom(types.get(variableName)))
			return new ReadBooleanExpression(variableName, sourceLocation);
		else if (UnitExpression.class.isAssignableFrom(types.get(variableName)))
			return new ReadUnitExpression(variableName, sourceLocation);
		else
			return null;
	}

	@Override
	public Expression createIsSolid(Expression position, SourceLocation sourceLocation) {
		return new IsSolidBoolean((PositionExpression) position, sourceLocation);
	}

	@Override
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation) {
		return new IsPassableBoolean((PositionExpression) position, sourceLocation);
	}

	@Override
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {
		return new IsFriendBoolean((UnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {
		return new IsEnemyBoolean((UnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {
		return new IsAliveBoolean((UnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		return new CarriesItemBoolean((UnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createNot(Expression expression, SourceLocation sourceLocation) {
		return new NotBooleanExpression((BooleanExpression) expression, sourceLocation);
	}

	@Override
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		return new AndBooleanExpression((BooleanExpression) left, (BooleanExpression) right, sourceLocation);
	}

	@Override
	public Expression createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		return new OrBooleanExpression((BooleanExpression) left, (BooleanExpression) right, sourceLocation);
	}

	@Override
	public Expression createHerePosition(SourceLocation sourceLocation) {
		return new HerePositionExpression(sourceLocation);
	}

	@Override
	public Expression createLogPosition(SourceLocation sourceLocation) {
		return new GameObjectPositionExpression<Log>(sourceLocation);
	}

	@Override
	public Expression createBoulderPosition(SourceLocation sourceLocation) {
		return new GameObjectPositionExpression<Boulder>(sourceLocation);
	}

	@Override
	public Expression createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopPositionExpression(sourceLocation);
	}

	@Override
	public Expression createSelectedPosition(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createNextToPosition(Expression position, SourceLocation sourceLocation) {
		return new NextToPositionExpression((PositionExpression) position, sourceLocation);
	}

	@Override
	public Expression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new LiteralPositionExpression(x, y, z, sourceLocation);
	}

	@Override
	public Expression createThis(SourceLocation sourceLocation) {
		return new ThisUnitExpression(sourceLocation);
	}

	@Override
	public Expression createFriend(SourceLocation sourceLocation) {
		return new FriendUnitExpression(sourceLocation);
	}

	@Override
	public Expression createEnemy(SourceLocation sourceLocation) {
		return new EnemyUnitExpression(sourceLocation);
	}

	@Override
	public Expression createAny(SourceLocation sourceLocation) {
		return new AnyUnitExpression(sourceLocation);
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		return new TrueBooleanExpression(sourceLocation);
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new FalseBooleanExpression(sourceLocation);
	}

	@Override
	public Expression createPositionOf(Expression unit, SourceLocation sourceLocation) {
		return new PositionOfExpression((UnitExpression) unit, sourceLocation);
	}

}
