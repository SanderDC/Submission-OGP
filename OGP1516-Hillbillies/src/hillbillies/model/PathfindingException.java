package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signaling a path to a certain position could not be found.
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 */
public class PathfindingException extends RuntimeException {
	
	/**
	 * Initialize this new Pathfinding Exception with a given start and end point
	 * @param start
	 * 			The starting point from which no path could be found
	 * @param end
	 * 			The end point to which no path from the start point could be found.
	 * @post	The start point of this new PathfindingException is equal to
	 * 			the given start point
	 * 			new.getStart() == start
	 * @post	The end point of this new PathfindingException is equal to
	 * 			the given end point.
	 * 			new.getEnd() == end
	 */
	public PathfindingException(Vector start, Vector end){
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Return the start point of this PathfindingException
	 */
	public Vector getStart(){
		return this.start;
	}
	
	/**
	 * Variable registering the start point of this PathfindingException
	 */
	private final Vector start;
	
	/**
	 * Return the end point of this PathfindingException
	 */
	@Raw @Immutable
	public Vector getEnd(){
		return this.end;
	}
	
	/**
	 * Variable registering the end point of this PathfindingException
	 */
	private final Vector end;

	/**
	 * Variable registering the serialVersionUID of this Exception
	 */
	private static final long serialVersionUID = -4292986837381695822L;

}
