package hillbillies.model.expressions;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Unit;

public abstract class UnitExpression extends Expression {

	//	/**
	//	 * Initialize this new UnitExpression with given Unit.
	//	 *
	//	 * @param  unit
	//	 *         The Unit for this new UnitExpression.
	//	 * @effect The Unit of this new UnitExpression is set to
	//	 *         the given Unit.
	//	 *       | this.setUnit(unit)
	//	 */
	//	public UnitExpression(Unit unit)
	//			throws IllegalArgumentException {
	//		this.setUnit(unit);
	//	}


	/**
	 * Return the Unit of this UnitExpression.
	 */
	@Basic @Raw
	public Unit getUnit() {
		return this.unit;
	}

	/**
	 * Check whether the given Unit is a valid Unit for
	 * any UnitExpression.
	 *  
	 * @param  Unit
	 *         The Unit to check.
	 * @return 
	 *       | result == true
	 */
	public static boolean isValidUnit(Unit unit) {
		return true;
	}

	/**
	 * Set the Unit of this UnitExpression to the given Unit.
	 * 
	 * @param  unit
	 *         The new Unit for this UnitExpression.
	 * @post   The Unit of this new UnitExpression is equal to
	 *         the given Unit.
	 *       | new.getUnit() == unit
	 * @throws IllegalArgumentException
	 *         The given Unit is not a valid Unit for any
	 *         UnitExpression.
	 *       | ! isValidUnit(getUnit())
	 */
	@Raw
	public void setUnit(Unit unit) 
			throws IllegalArgumentException {
		if (! isValidUnit(unit))
			throw new IllegalArgumentException();
		this.unit = unit;
	}

	/**
	 * Variable registering the Unit of this UnitExpression.
	 */
	private Unit unit;

	public abstract Unit evaluate();

}
