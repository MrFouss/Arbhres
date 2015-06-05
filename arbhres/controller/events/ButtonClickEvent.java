package arbhres.controller.events;

/**
 * @author	Maxime Brodat <maxime.brodat@fouss.fr>
 * @version 1.0
 * @since	05/30/2015
 */

public class ButtonClickEvent {
	public enum Button {
		NEW_GAME, BONUS_RANDOM, BONUS_ERASE, BONUS_PAUSE, BONUS_SWAP, BONUS_SEE, BONUS_UNDO, BONUS_TURNLEFT, BONUS_TURNRIGHT;
	}
	
	private Button button;

	/**
	 * Constructor for ButtonClickEvent
	 * 
	 * @param button the button that has been clicked
	 */
	public ButtonClickEvent(Button button) {
		this.button = button;
	}

	/**
	 * Getter for the clicked button
	 * 
	 * @return the button that has been clicked
	 */
	public Button getButton() {
		return this.button;
	}
}
