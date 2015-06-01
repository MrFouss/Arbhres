package arbhres.controller.events;

/**
 * @author Maxime Brodat <maxime.brodat@fouss.fr>
 * @version 1.0
 * @since 05/30/2015
 */

public class ActionEvent {
    public enum Action {
	MOVE_UP, MOVE_LEFT, MOVE_RIGHT, MOVE_DOWN,
	TILE_0, TILE_1, TILE_2, TILE_3,
	TILE_4, TILE_5, TILE_6, TILE_7,
	TILE_8, TILE_9, TILE_10, TILE_11,
	TILE_12, TILE_13, TILE_14, TILE_15,
	TILE_INVENTORY, BONUS_RANDOM, BONUS_ERASE, BONUS_PAUSE,
	BONUS_SWAP, BONUS_SEE, BONUS_UNDO, BONUS_TURNLEFT, BONUS_TURNRIGHT,
	MALUS_TARGET, MALUS_BLIND,
	NEWGAME;
    }

    private Action action;

    public ActionEvent(Action action) {
	this.action = action;
    }

    public Action getAction() {
	return this.action;
    }
}
