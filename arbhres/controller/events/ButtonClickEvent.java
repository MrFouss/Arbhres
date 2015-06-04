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

	public ButtonClickEvent(Button button) {
		this.button = button;
	}

	public Button getButton() {
		return this.button;
	}
}
