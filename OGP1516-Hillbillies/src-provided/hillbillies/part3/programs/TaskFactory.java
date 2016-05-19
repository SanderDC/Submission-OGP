package hillbillies.part3.programs;
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
import hillbillies.model.statements.AssignmentStatement;
import hillbillies.model.statements.AttackStatement;
import hillbillies.model.statements.BreakStatement;
import hillbillies.model.statements.FollowStatement;
import hillbillies.model.statements.IfStatement;
import hillbillies.model.statements.MoveToStatement;
import hillbillies.model.statements.PrintStatement;
import hillbillies.model.statements.SequenceStatement;
import hillbillies.model.statements.Statement;
import hillbillies.model.statements.WhileStatement;
import hillbillies.model.statements.WorkStatement;

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
		return new AssignmentStatement(value, variableName, sourceLocation);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new WhileStatement((IBooleanExpression) condition, body, sourceLocation);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return  new IfStatement((IBooleanExpression) condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new BreakStatement(sourceLocation);
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new PrintStatement(value, sourceLocation);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new SequenceStatement(statements, sourceLocation);
	}

	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {
		return new MoveToStatement( (IPositionExpression) position, sourceLocation);
	}

	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {
		return new WorkStatement((IPositionExpression) position, sourceLocation);
	}

	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {
		return new FollowStatement((IUnitExpression) unit, sourceLocation);
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		return new AttackStatement((EnemyUnitExpression) unit, sourceLocation);
		
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
		return new IsSolidBoolean((IPositionExpression) position, sourceLocation);
	}

	@Override
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation) {
		return new IsPassableBoolean((IPositionExpression) position, sourceLocation);
	}

	@Override
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {
		return new IsFriendBoolean((IUnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {
		return new IsEnemyBoolean((IUnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {
		return new IsAliveBoolean((IUnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		return new CarriesItemBoolean((IUnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createNot(Expression expression, SourceLocation sourceLocation) {
		return new NotBooleanExpression((IBooleanExpression) expression, sourceLocation);
	}

	@Override
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		return new AndBooleanExpression((IBooleanExpression) left, (IBooleanExpression) right, sourceLocation);
	}

	@Override
	public Expression createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		return new OrBooleanExpression((IBooleanExpression) left, (IBooleanExpression) right, sourceLocation);
	}

	@Override
	public Expression createHerePosition(SourceLocation sourceLocation) {
		return new HerePositionExpression(sourceLocation);
	}

	@Override
	public Expression createLogPosition(SourceLocation sourceLocation) {
		return new LogPositionExpression(sourceLocation);
	}

	@Override
	public Expression createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderPositionExpression(sourceLocation);
	}

	@Override
	public Expression createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopPositionExpression(sourceLocation);
	}

	@Override
	public Expression createSelectedPosition(SourceLocation sourceLocation) {
		return new SelectedPositionExpression(sourceLocation);
	}

	@Override
	public Expression createNextToPosition(Expression position, SourceLocation sourceLocation) {
		return new NextToPositionExpression((IPositionExpression) position, sourceLocation);
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
		return new PositionOfExpression((IUnitExpression) unit, sourceLocation);
	}

}
