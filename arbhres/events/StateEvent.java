package arbhres.events;

/**
 * @author	Maxime Brodat "maxime.brodat@fouss.fr"
 * @version	1.0
 * @since	05/30/2015
 */

public class StateEvent {

	private boolean state;

	/**
	 * Constructor for StateEvent
	 * 
	 * @param the state of the mode
	 */
	public StateEvent(boolean state) {
		this.state = state;
	}

	/**
	 * Getter for the state
	 * 
	 * @return the value of the state
	 */
	public boolean getState() {
		return this.state;
	}
}
