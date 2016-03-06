package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.Util;

/**
 * A class of vectors with three components
 * 
 * @invar  	The x-coordinate of each Vector must be a valid x-coordinate for any
 *         	Vector.
 *       	| isValidX(getX())
 * @invar  	The y-coordinate of each Vector must be a valid y-coordinate for any
 *         	Vector.
 *       	| isValidY(getY())
 * @invar  	The z-coordinate of each Vector must be a valid z-coordinate for any
 *         	Vector.
 *       	| isValidZ(getZ())
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 */
@Value
public class Vector {

	/**
	 * Initialize this new Vector with given x-coordinate, y-coordinate and z-coordinate.
	 * 
	 * @param  	x
	 *         	The x-coordinate for this new Vector.
	 * @param  	y
	 *         	The y-coordinate for this new Vector.
	 * @param  	z
	 *         	The z-coordinate for this new Vector.
	 * @post   	If the given x-coordinate is a valid x-coordinate for any Vector,
	 *         	the x-coordinate of this new Vector is equal to the given
	 *         	x-coordinate. Otherwise, the x-coordinate of this new Vector is equal
	 *         	to 0.
	 *       	| if (isValidX(x))
	 *       	|   then new.getX() == x
	 *       	|   else new.getX() == 0
	 * @post   	If the given y-coordinate is a valid y-coordinate for any Vector,
	 *         	the y-coordinate of this new Vector is equal to the given
	 *         	y-coordinate. Otherwise, the y-coordinate of this new Vector is equal
	 *         	to 0.
	 *       	| if (isValidY(y))
	 *       	|   then new.getY() == y
	 *       	|   else new.getY() == 0
	 * @post   	If the given z-coordinate is a valid z-coordinate for any Vector,
	 *         	the z-coordinate of this new Vector is equal to the given
	 *         	z-coordinate. Otherwise, the z-coordinate of this new Vector is equal
	 *         	to 0.
	 *       	| if (isValidZ(z))
	 *       	|   then new.getZ() == z
	 *       	|   else new.getZ() == 0
	 * @throws	IllegalArgumentException
	 * 			| one of the coordinates is invalid
	 * 			| (! isValidX(x)) || (! isValidY(y)) || (! isValidZ(z))
	 */
	public Vector(double x, double y, double z) throws IllegalArgumentException{
		if (! isValidX(x))
			throw new IllegalArgumentException("This is an impossible x-coordinate");
		if (! isValidY(y))
			throw new IllegalArgumentException("This is an impossible y-coordinate");		
		if (! isValidZ(z))
			throw new IllegalArgumentException("This is an impossible z-coordinate");
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Return the x-coordinate of this Vector.
	 */
	@Basic @Raw
	public double getX() {
		return this.x;
	}

	/**
	 * Check whether the given x-coordinate is a valid x-coordinate for
	 * any Vector.
	 *  
	 * @param  	x
	 *         	The x-coordinate to check.
	 * @return 	true if the given x-coordinate does not equal positive infinity or negative infinity
	 *       	| result == (x != Double.POSITIVE_INFINITY) && (x != Double.NEGATIVE_INFINITY)
	 */
	public static boolean isValidX(double x) {
		return (x != Double.POSITIVE_INFINITY) && (x != Double.NEGATIVE_INFINITY);
	}

	/**
	 * Variable registering the x-coordinate of this Vector.
	 */
	private final double x;

	/**
	 * Return the y-coordinate of this Vector.
	 */
	@Basic @Raw
	public double getY() {
		return this.y;
	}

	/**
	 * Check whether the given y-coordinate is a valid y-coordinate for
	 * any Vector.
	 *  
	 * @param  	y
	 *         	The y-coordinate to check.
	 * @return 	true if the given y-coordinate does not equal positive infinity or negative infinity
	 *       	| result == (y != Double.POSITIVE_INFINITY) && (y != Double.NEGATIVE_INFINITY)
	 */
	public static boolean isValidY(double y) {
		return (y != Double.POSITIVE_INFINITY) && (y != Double.NEGATIVE_INFINITY);
	}

	/**
	 * Variable registering the y-coordinate of this Vector.
	 */
	private final double y;

	/**
	 * Return the z-coordinate of this Vector.
	 */
	@Basic @Raw
	public double getZ() {
		return this.z;
	}

	/**
	 * Check whether the given z-coordinate is a valid z-coordinate for
	 * any Vector.
	 *  
	 * @param  	z
	 *         	The z-coordinate to check.
	 * @return 	true if the given z-coordinate does not equal positive infinity or negative infinity
	 *       	| result == (z != Double.POSITIVE_INFINITY) && (z != Double.NEGATIVE_INFINITY)
	 */
	public static boolean isValidZ(double z) {
		return (z != Double.POSITIVE_INFINITY) && (z != Double.NEGATIVE_INFINITY);
	}

	/**
	 * Variable registering the z-coordinate of this Vector.
	 */
	private final double z;
	
	/**
	 * Return an array of doubles containing the x-, y- and z-coordinate
	 * of this Vector in that order.
	 */
	public double[] toArray(){
		return new double[]{this.getX(), this.getY(), this.getZ()};
	}
	
	/**
	 * Check whether the given integer numbers can be added.
	 *
	 * @param  	a
	 *         	The first double-precision floating point number to be checked.
	 * @param  	b
	 *         	The other double-precision floating point number to be checked.
	 * @return 	If the given doubles are both positive,
	 *         	true if and only if a does not exceed the largest
	 *         	possible value in the type double diminished with b.
	 *       	| if ( (a > 0) && (b > 0) )
	 *       	|   then result == (a <= Double.MAX_VALUE - b)
	 * @return 	If the given double are both negative,
	 *         	true if and only if a is not below the smallest
	 *         	possible value in the type double diminished with b.
	 *       	| if ( (a < 0) && (b < 0) )
	 *       	|   then result == ( -a >= -Double.MAX_VALUE + b)
	 * @return 	If the given integer numbers have different signs
	 *         	always true.
	 *       	| if ( ( (a <= 0) && (b >= 0) )
	 *       	|   || ( (a >= 0) && (b <= 0) ) )
	 *       	|   then result == true
	 * @note	Because of the way floating point numbers work, this method
	 * 			will return true in cases where small numbers are added to Double.MAX_VALUE.
	 * 			When 1 is subtracted from Double.MAX_VALUE, it will be converted to a number with
	 * 			the same exponent as Double.MAX_VALUE. This number will be so small it will
	 * 			be rounded down to zero and the result will still be Double.MAX_VALUE
	 */
	public static boolean areAddable(double a, double b){
		if ( ((a > 0) && (b > 0)) || ((a < 0) && (b < 0)) ){
			return (Math.abs(a) <= Double.MAX_VALUE - Math.abs(b));
		}
		return true;
	}
	
	/**
	 * Return a new vector whose components are the sum of this vector and another vector.
	 * @param 	other
	 * 			The vector that needs to be added to this vector
	 * @return	A new vector whose components are the sum of this vector and the given vector
	 * 			| result == new Vector(this.getX() + other.getX(), this.getY() + other.getY(), 
	 *			| 						this.getZ() + other.getZ())
	 * @throws IllegalArgumentException
	 * 			At least one pair of coordinates cannot be added
	 * 			| (! areAddable(this.getX(), other.getX())) || 
	 * 			| (! areAddable(this.getY(), other.getY())) ||
	 *			| (! areAddable(this.getZ(), other.getZ()))
	 */
	public Vector add(Vector other) throws IllegalArgumentException{
		if ( (! areAddable(this.getX(), other.getX())) || (! areAddable(this.getY(), other.getY()))
				|| (! areAddable(this.getZ(), other.getZ())))
			throw new IllegalArgumentException("Adding these vectors causes overflow");
		return new Vector(this.getX() + other.getX(), this.getY() + other.getY(), 
				this.getZ() + other.getZ());
	}
	
	/**
	 * Checks whether two doubles can be multiplied without causing overflow
	 * @param a
	 * 			The first double to be checked.
	 * @param b
	 * 			The second double to be checked
	 * @return	true if the two given numbers can be multiplied without causing overflow
	 * 			result == (Math.abs(a) <= Double.MAX_VALUE / Math.abs(b))
	 */
	public boolean areMultipliable(double a, double b){
		return (Math.abs(a) <= Double.MAX_VALUE / Math.abs(b));
	}
	
	/**
	 * Return a new vector whose components equal the components of this vector
	 * multiplied by the given factor.
	 * 
	 * @param factor
	 * 			The factor with which to multiply the components of this vector.
	 * @return	A new vector whose components equal the components of this vector
	 * 			multiplied by the given factor
	 * 			| result == new Vector(factor*this.getX(), factor*this.getY(), factor*this.getZ())
	 * @throws IllegalArgumentException
	 * 			At least one of the multiplications results in overflow.
	 * 			| (! areMultipliable(this.getX(), factor)) ||
	 * 			| (! areMultipliable(this.getY(), factor)) ||
	 * 			| (! areMultipliable(this.getZ(), factor)) ||
	 */
	public Vector scalarMultiply(double factor) throws IllegalArgumentException{
		if ( (! areMultipliable(this.getX(), factor)) || (! areMultipliable(this.getY(), factor))
				|| (! areMultipliable(this.getZ(), factor)))
			throw new IllegalArgumentException();
		return new Vector(factor*this.getX(), factor*this.getY(), factor*this.getZ());
	}
	
	/**
	 * Checks whether this Vector is equal to a given Vector.
	 * @param other
	 * 			The Vector to check for equality with this Vector
	 * @return	true if and only if all components of this Vector equal
	 * 			the respective components of the given Vector
	 * 			| result == (Util.fuzzyEquals(this.getX(), other.getX())) &&
				|			(Util.fuzzyEquals(this.getY(), other.getY())) &&
				|			(Util.fuzzyEquals(this.getZ(), other.getZ()))
	 */
	public boolean equals(Vector other){
		return (Util.fuzzyEquals(this.getX(), other.getX()))
				&& (Util.fuzzyEquals(this.getY(), other.getY()))
				&& (Util.fuzzyEquals(this.getZ(), other.getZ()));
	}
	
	/**
	 * Checks whether this Vector lies in a cube with the two given Vectors as diagonally opposite corners.
	 * @param one
	 * 			The first of the two Vectors defining the cube
	 * @param two
	 * 			The second of the two Vectors defining the cube
	 * @return 	true if and only if all coordinates of this Vector lie between or equal
	 * 			the respective components of the given Vectors
	 * 			result == 	((one.getX() <= this.getX() && this.getX() <= two.getX()) || 
	 * 							(one.getX() >= this.getX() && this.getX() >= two.getX())) &&
	 * 						((one.getY() <= this.getY() && this.getY() <= two.getY()) || 
	 * 							(one.getY() >= this.getY() && this.getY() >= two.getY())) &&
	 * 						((one.getZ() <= this.getZ() && this.getZ() <= two.getZ()) || 
	 * 							(one.getZ() >= this.getZ() && this.getZ() >= two.getZ())) &&
	 */
	public boolean liesBetween(Vector one, Vector two){
		if (! ( ( one.getX() <= this.getX() ) && ( this.getX() <= two.getX() ) ) &&
				! ( ( one.getX() >= this.getX() ) && ( this.getX() >= two.getX() ) ) )
			return false;
		if (! ( ( one.getY() <= this.getY() ) && ( this.getY() <= two.getY() ) ) &&
				! ( ( one.getY() >= this.getY() ) && ( this.getY() >= two.getY() ) ) )
			return false;
		if (! ( ( one.getZ() <= this.getZ() ) && ( this.getZ() <= two.getZ() ) ) &&
				! ( ( one.getZ() >= this.getZ() ) && ( this.getZ() >= two.getZ() ) ) )
			return false;
		return true;
	}
	
	/**
	 * Return the 2-norm of this Vector
	 * @return	The 2-norm of the given Vector
	 * 			| result == Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2))
	 */
	public double norm(){
		return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));
	}
	
	/**
	 * Return a new Vector whose components equal the respective components of the given Vector
	 * divided by the norm of the given Vector
	 * @return	A Vector whose components equal those of this Vector divided by its norm,
	 * 			if this Vector is not the null vector, else return the null Vector.
	 * 			| if(!this.equals(new Vector(0,0,0)))
	 * 			| then result == new Vector(this.getX()/this.norm(), this.getY()/this.norm(), this.getZ()/this.norm())
	 * 			| else result == new Vector(0,0,0)
	 */
	public Vector normalize(){
		if (this.equals(new Vector(0,0,0)))
			return new Vector(0,0,0);
		return new Vector(this.getX()/this.norm(), this.getY()/this.norm(), this.getZ()/this.norm());
	}

}
