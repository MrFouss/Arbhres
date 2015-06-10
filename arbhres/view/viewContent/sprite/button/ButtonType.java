package arbhres.view.viewContent.sprite.button;

import java.awt.geom.Rectangle2D;

import arbhres.view.viewContent.sprite.Sprite;
import arbhres.view.viewContent.sprite.SpriteType;
import arbhres.view.viewContent.sprite.general.HintPhrase;

/** 
 * @author Esia Belbachir
 * @version 1.0
 * @since 06/10/15
 * Describes the caracterictics of a Button.
 */
public enum ButtonType implements SpriteType {
    NEW_GAME	(ButtonLocation.NEW_GAME, 0, 0, "newGame", null),
    RANDOM		(ButtonLocation.STORE, 0, 0, "random", null),
    ERASE		(ButtonLocation.STORE, 1, 0, "erase", HintPhrase.HINT_ERASE),
    PAUSE		(ButtonLocation.STORE, 0, 1, "pause", HintPhrase.HINT_PAUSE),
    SWAP		(ButtonLocation.STORE, 1, 1, "swap", HintPhrase.HINT_SWAP),
    SEE			(ButtonLocation.STORE, 0, 2, "see", HintPhrase.HINT_SEE),
    UNDO		(ButtonLocation.STORE, 1, 2, "undo", HintPhrase.HINT_UNDO),
    TURN_LEFT	(ButtonLocation.STORE, 0, 3, "turnLeft", HintPhrase.HINT_TURN_LEFT),
    TURN_RIGHT	(ButtonLocation.STORE, 1, 3, "turnRight", HintPhrase.HINT_TURN_RIGHT);
    
    /**
     * sprite UNpressed
     */
    private String spriteUP;
    /**
     * sprite Pressed
     */
	private String spriteP;
    private Rectangle2D location;
    private String hint;
    
    private static final String unpressedSuffix = "UP";
    private static final String pressedSuffix = "P";
    
    /**
     * Create a ButtonType that includes the files path and the location
     * @param loc type of location
     * @param x emplacement on x-axis
     * @param y emplacement on y-axis
     * @param name file name without the path, suffix, and extension
     */
    private ButtonType(ButtonLocation loc, int x, int y, String name, HintPhrase h) {
		spriteUP = Sprite.path + name + unpressedSuffix + Sprite.extension;
		spriteP = Sprite.path + name + pressedSuffix + Sprite.extension;
		location = loc.getBox(x, y);
		if (h != null) {
			hint = h.getPhrase();	
		} else {
			hint = "";
		}
    }
    
    /**
     * 
     * @return the file path to the unpressed sprite
     */
    public String getSpriteUP() {
		return spriteUP;
	}

    /**
     * 
     * @return return the path to the pressed sprite
     */
	public String getSpriteP() {
		return spriteP;
	}

	/**
	 * 
	 * @return returns the location of the sprite
	 */
	public Rectangle2D getLocation() {
		return location;
	}
    
	public String getHint() {
		return hint;
	}
	
	/**
	 * Lists the existing buttons.
	 * @return An array of ButtonType objects.
	 */
    public static ButtonType[] getButtonTypes() {
	ButtonType[] s = {
		NEW_GAME,
		RANDOM,
		ERASE,
		PAUSE,
		SWAP,
		SEE,
		UNDO,
		TURN_LEFT,
		TURN_RIGHT
		};
	return s;
    }

}
