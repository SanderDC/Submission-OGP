package hillbillies.model;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of vectors with three components
 * 
 * @invar  The x-coordinate of each Vector must be a valid x-coordinate for any
 *         Vector.
 *       | isValidX(getX())
 * @invar  The y-coordinate of each Vector must be a valid y-coordinate for any
 *         Vector.
 *       | isValidY(getY())
 * @invar  The z-coordinate of each Vector must be a valid z-coordinate for any
 *         Vector.
 *       | isValidZ(getZ())
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 */
public class Vector {

	/**
	 * Initialize this new Vector with given x-coordinate, y-coordinate and z-coordinate.
	 * 
	 * @param  x
	 *         The x-coordinate for this new Vector.
	 * @param  y
	 *         The y-coordinate for this new Vector.
	 * @param  z
	 *         The z-coordinate for this new Vector.
	 * @post   If the given x-coordinate is a valid x-coordinate for any Vector,
	 *         the x-coordinate of this new Vector is equal to the given
	 *         x-coordinate. Otherwise, the x-coordinate of this new Vector is equal
	 *         to 0.
	 *       | if (isValidX(x))
	 *       |   then new.getX() == x
	 *       |   else new.getX() == 0
	 * @post   If the given y-coordinate is a valid y-coordinate for any Vector,
	 *         the y-coordinate of this new Vector is equal to the given
	 *         y-coordinate. Otherwise, the y-coordinate of this new Vector is equal
	 *         to 0.
	 *       | if (isValidY(y))
	 *       |   then new.getY() == y
	 *       |   else new.getY() == 0
	 * @post   If the given z-coordinate is a valid z-coordinate for any Vector,
	 *         the z-coordinate of this new Vector is equal to the given
	 *         z-coordinate. Otherwise, the z-coordinate of this new Vector is equal
	 *         to 0.
	 *       | if (isValidZ(z))
	 *       |   then new.getZ() == z
	 *       |   else new.getZ() == 0
	 */
	public Vector(double x, double y, double z) {
		if (! isValidX(x))
			x = 0;
		setX(x);
		if (! isValidY(y))
			y = 0;
		setY(y);
		if (! isValidZ(z))
			z = 0;
		setZ(z);
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
	 * @param  x
	 *         The x-coordinate to check.
	 * @return 
	 *       | result == true
	 */
	public static boolean isValidX(double x) {
		return true;
	}

	/**
	 * Set the x-coordinate of this Vector to the given x-coordinate.
	 * 
	 * @param  x
	 *         The new x-coordinate for this Vector.
	 * @post   If the given x-coordinate is a valid x-coordinate for any Vector,
	 *         the x-coordinate of this new Vector is equal to the given
	 *         x-coordinate.
	 *       | if (isValidX(x))
	 *       |   then new.getX() == x
	 */
	@Raw
	public void setX(double x) {
		if (isValidX(x))
			this.x = x;
	}

	/**
	 * Variable registering the x-coordinate of this Vector.
	 */
	private double x;

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
	 * @param  y
	 *         The y-coordinate to check.
	 * @return 
	 *       | result == true;
	 */
	public static boolean isValidY(double y) {
		return true;
	}

	/**
	 * Set the y-coordinate of this Vector to the given y-coordinate.
	 * 
	 * @param  y
	 *         The new y-coordinate for this Vector.
	 * @post   If the given y-coordinate is a valid y-coordinate for any Vector,
	 *         the y-coordinate of this new Vector is equal to the given
	 *         y-coordinate.
	 *       | if (isValidY(y))
	 *       |   then new.getY() == y
	 */
	@Raw
	public void setY(double y) {
		if (isValidY(y))
			this.y = y;
	}

	/**
	 * Variable registering the y-coordinate of this Vector.
	 */
	private double y;

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
	 * @param  z
	 *         The z-coordinate to check.
	 * @return 
	 *       | result == true
	 */
	public static boolean isValidZ(double z) {
		return true;
	}

	/**
	 * Set the z-coordinate of this Vector to the given z-coordinate.
	 * 
	 * @param  z
	 *         The new z-coordinate for this Vector.
	 * @post   If the given z-coordinate is a valid z-coordinate for any Vector,
	 *         the z-coordinate of this new Vector is equal to the given
	 *         z-coordinate.
	 *       | if (isValidZ(z))
	 *       |   then new.getZ() == z
	 */
	@Raw
	public void setZ(double z) {
		if (isValidZ(z))
			this.z = z;
	}

	/**
	 * Variable registering the z-coordinate of this Vector.
	 */
	private double z;
	
	public double[] toArray(){
		return new double[]{this.getX(), this.getY(), this.getZ()};
	}
	
	/**
	 * Check whether the given integer numbers can be added.
	 *
	 * @param  a
	 *         The first double-precision floating point number to be checked.
	 * @param  b
	 *         The other double-precision floating point number to be checked.
	 * @return If the given doubles are both positive,
	 *         true if and only if a does not exceed the largest
	 *         possible value in the type double diminished with b.
	 *       | if ( (a > 0) && (b > 0) )
	 *       |   then result == (a <= Double.MAX_VALUE - b)
	 * @return If the given double are both negative,
	 *         true if and only if a is not below the smallest
	 *         possible value in the type double diminished with b.
	 *       | if ( (a < 0) && (b < 0) )
	 *       |   then result == ( -a >= -Double.MAX_VALUE + b)
	 * @return If the given integer numbers have different signs
	 *         always true.
	 *       | if ( ( (a <= 0) && (b >= 0) )
	 *       |   || ( (a >= 0) && (b <= 0) ) )
	 *       |   then result == true
	 */
	public static boolean areAddable(double a, double b){
		if ( ((a > 0) && (b > 0)) || ((a < 0) && (b < 0)) ){
			return (Math.abs(a) <= Double.MAX_VALUE - Math.abs(b));
		}
		return true;
	}
	
	/**
	 * Return a new vector whose components are the sum of this vector and another vector.
	 * @param other
	 * 		The vector that needs to be added to this vector
	 * @post	The new x-coordinate equals the sum of the x-coordinate of this vector and
	 * 			of the other vector.
	 * 			| (new this).getX() == this.getX() + other.getX()
	 * @post	The new y-coordinate equals the sum of the y-coordinate of this vector and
	 * 			of the other vector.
	 * 			| (new this).getY() == this.getY() + other.getY()
	 * @post	The new z-coordinate equals the sum of the z-coordinate of this vector and
	 * 			of the other vector.
	 * 			| (new this).getZ() == this.getZ() + other.getZ()
	 * @throws IllegalArgumentException
	 * 			At least one pair of coordinates cannot be added
	 * 			| (! areAddable(this.getX(), other.getX())) || 
	 * 			| (! areAddable(this.getY(), other.getY())) ||
				| (! areAddable(this.getZ(), other.getZ()))
	 */
	public Vector add(Vector other) throws IllegalArgumentException{
		if ( (! areAddable(this.getX(), other.getX())) || (! areAddable(this.getY(), other.getY()))
				|| (! areAddable(this.getZ(), other.getZ())))
			throw new IllegalArgumentException();
		return new Vector(this.getX() + other.getX(), this.getY() + other.getY(), 
				this.getZ() + other.getZ());
	}
	
	/**
	 * Checks whether two doubles can be multiplied without causing overflow
	 * @param a
	 * 			The first double to be checked.
	 * @param b
	 * 			The second double to be checked
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
	 */
	public boolean equals(Vector other){
		return (Util.fuzzyEquals(this.getX(), other.getX()))
				&& (Util.fuzzyEquals(this.getY(), other.getY()))
				&& (Util.fuzzyEquals(this.getZ(), other.getZ()));
	}
	
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

}
