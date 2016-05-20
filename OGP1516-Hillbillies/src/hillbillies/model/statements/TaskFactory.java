package hillbillies.model.statements;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hillbillies.model.Task;
import hillbillies.model.Vector;
import hillbillies.model.expressions.AndBooleanExpression;
import hillbillies.model.expressions.AnyUnitExpression;
import hillbillies.model.expressions.BoulderPositionExpression;
import hillbillies.model.expressions.CarriesItemBoolean;
import hillbillies.model.expressions.EnemyUnitExpression;
import hillbillies.model.expressions.Expression;
import hillbillies.model.expressions.FalseBooleanExpression;
import hillbillies.model.expressions.FriendUnitExpression;
import hillbillies.model.expressions.HerePositionExpression;
import hillbillies.model.expressions.IBooleanExpression;
import hillbillies.model.expressions.IPositionExpression;
import hillbillies.model.expressions.IUnitExpression;
import hillbillies.model.expressions.IsAliveBoolean;
import hillbillies.model.expressions.IsEnemyBoolean;
import hillbillies.model.expressions.IsFriendBoolean;
import hillbillies.model.expressions.IsPassableBoolean;
import hillbillies.model.expressions.IsSolidBoolean;
import hillbillies.model.expressions.LiteralPositionExpression;
import hillbillies.model.expressions.LogPositionExpression;
import hillbillies.model.expressions.NextToPositionExpression;
import hillbillies.model.expressions.NotBooleanExpression;
import hillbillies.model.expressions.OrBooleanExpression;
import hillbillies.model.expressions.PositionOfExpression;
import hillbillies.model.expressions.ReadBooleanExpression;
import hillbillies.model.expressions.ReadPositionExpression;
import hillbillies.model.expressions.ReadUnitExpression;
import hillbillies.model.expressions.SelectedPositionExpression;
import hillbillies.model.expressions.ThisUnitExpression;
import hillbillies.model.expressions.TrueBooleanExpression;
import hillbillies.model.expressions.WorkshopPositionExpression;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;

public class TaskFactory implements ITaskFactory<Expression, Statement, Task> {
	
	private HashMap<String,Class<? extends Expression>> types = new HashMap<>();

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		
		 List<Task> tasklist= new ArrayList<>();
		if (selectedCubes.size()!=0) {
			for (int[] i : selectedCubes) {
				tasklist.add(new Task(name, priority, activity.clone(), new Vector(i[0],i[1],i[2])));
			}
		}
		else {
			tasklist.add(new Task(name, priority, activity.clone()));
		}
		
		return tasklist;
			
		
	}

	@Override
	public Statement createAssignment(String variableName, Expression value, SourceLocation sourceLocation) {
		if (this.types.containsKey(variableName))
			if (value.getClass().getInterfaces()[0] != types.get(variableName).getInterfaces()[0])
				return null;
		this.types.put(variableName, value.getClass());
		try {
			return new AssignmentStatement(value, variableName, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		try {
			return new WhileStatement((IBooleanExpression) condition, body, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		try {
			return  new IfStatement((IBooleanExpression) condition, ifBody, elseBody, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		try {
			return new BreakStatement(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		try {
			return new PrintStatement(value, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		try {
			return new SequenceStatement(statements, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {
		try {
			return new MoveToStatement( (IPositionExpression) position, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {
		try {
			return new WorkStatement((IPositionExpression) position, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {
		try {
			return new FollowStatement((IUnitExpression) unit, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		try {
			return new AttackStatement((IUnitExpression) unit, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createReadVariable(String variableName, SourceLocation sourceLocation) {
		if (!types.containsKey(variableName))
			return null;
		else if (IPositionExpression.class.isAssignableFrom(types.get(variableName)))
			return new ReadPositionExpression(variableName, sourceLocation);
		else if (IBooleanExpression.class.isAssignableFrom(types.get(variableName)))
			return new ReadBooleanExpression(variableName, sourceLocation);
		else if (IUnitExpression.class.isAssignableFrom(types.get(variableName)))
			return new ReadUnitExpression(variableName, sourceLocation);
		else
			return null;
	}

	@Override
	public Expression createIsSolid(Expression position, SourceLocation sourceLocation) {
		try {
			return new IsSolidBoolean((IPositionExpression) position, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation) {
		try {
			return new IsPassableBoolean((IPositionExpression) position, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {
		try {
			return new IsFriendBoolean((IUnitExpression) unit, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {
		try {
			return new IsEnemyBoolean((IUnitExpression) unit, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {
		try {
			return new IsAliveBoolean((IUnitExpression) unit, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		try {
			return new CarriesItemBoolean((IUnitExpression) unit, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createNot(Expression expression, SourceLocation sourceLocation) {
		try {
			return new NotBooleanExpression((IBooleanExpression) expression, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		try {
			return new AndBooleanExpression((IBooleanExpression) left, (IBooleanExpression) right, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		try {
			return new OrBooleanExpression((IBooleanExpression) left, (IBooleanExpression) right, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createHerePosition(SourceLocation sourceLocation) {
		try {
			return new HerePositionExpression(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createLogPosition(SourceLocation sourceLocation) {
		try {
			return new LogPositionExpression(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createBoulderPosition(SourceLocation sourceLocation) {
		try {
			return new BoulderPositionExpression(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createWorkshopPosition(SourceLocation sourceLocation) {
		try {
			return new WorkshopPositionExpression(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createSelectedPosition(SourceLocation sourceLocation) {
		try {
			return new SelectedPositionExpression(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createNextToPosition(Expression position, SourceLocation sourceLocation) {
		try {
			return new NextToPositionExpression((IPositionExpression) position, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		try {
			return new LiteralPositionExpression(x, y, z, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createThis(SourceLocation sourceLocation) {
		try {
			return new ThisUnitExpression(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createFriend(SourceLocation sourceLocation) {
		try {
			return new FriendUnitExpression(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createEnemy(SourceLocation sourceLocation) {
		try {
			return new EnemyUnitExpression(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createAny(SourceLocation sourceLocation) {
		try {
			return new AnyUnitExpression(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		try {
			return new TrueBooleanExpression(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		try {
			return new FalseBooleanExpression(sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Expression createPositionOf(Expression unit, SourceLocation sourceLocation) {
		try {
			return new PositionOfExpression((IUnitExpression) unit, sourceLocation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}
