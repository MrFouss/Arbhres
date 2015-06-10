package arbhres.events;

/**
 * @author Maxime Brodat "maxime.brodat@fouss.fr"
 * @version 1.0
 * @since 05/30/2015
 */

public class MovementEvent {
	public enum Direction {
		 UP, LEFT, RIGHT, DOWN;
	}
	
	private Direction direction;

	/**
	 * Constructor for MovementEvent
	 * 
	 * @param direction the direction of the movement
	 */
	public MovementEvent(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Getter for the direction of the movement
	 * 
	 * @return the direction of the movement
	 */
	public Direction getDirection() {
		return this.direction;
	}
}
