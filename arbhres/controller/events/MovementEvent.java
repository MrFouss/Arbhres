package arbhres.controller.events;

/**
 * @author Maxime Brodat <maxime.brodat@fouss.fr>
 * @version 1.0
 * @since 05/30/2015
 */

public class MovementEvent {
	public enum Direction {
		 UP, LEFT, RIGHT, DOWN;
	}
	
	private Direction direction;

	public MovementEvent(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return this.direction;
	}
}
