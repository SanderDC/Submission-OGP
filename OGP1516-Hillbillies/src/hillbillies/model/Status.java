package hillbillies.model;
import be.kuleuven.cs.som.annotate.Value;

/**
 * An enumeration providing different possible statuses to express
 * what a GameObject is doing at a certain moment.
 * 
 * @author Sander Declercq
 * @author Bram Belpaire
 *
 */
@Value
public enum Status {
	WORKING,
	RESTING,
	MOVINGADJACENT,
	MOVINGDISTANT,
	IDLE,
	ATTACKING,
	FALLING
}
