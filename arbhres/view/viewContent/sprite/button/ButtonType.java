package arbhres.view.viewContent.sprite.button;

import java.awt.geom.Rectangle2D;

import arbhres.view.viewContent.sprite.Sprite;
import arbhres.view.viewContent.sprite.SpriteType;

public enum ButtonType implements SpriteType {
    NEW_GAME	(ButtonLocation.NEW_GAME, 0, 0, "newGame"),
    RANDOM		(ButtonLocation.STORE, 0, 0, "random"),
    ERASE		(ButtonLocation.STORE, 1, 0, "erase"),
    PAUSE		(ButtonLocation.STORE, 0, 1, "pause"),
    SWAP		(ButtonLocation.STORE, 1, 1, "swap"),
    SEE			(ButtonLocation.STORE, 0, 2, "see"),
    UNDO		(ButtonLocation.STORE, 1, 2, "undo"),
    TURN_LEFT	(ButtonLocation.STORE, 0, 3, "turnLeft"),
    TURN_RIGHT	(ButtonLocation.STORE, 1, 3, "turnRight");
    
    private String spriteUP;
	private String spriteP;
    private Rectangle2D location;
    
    private static final String unpressedSuffix = "UP";
    private static final String pressedSuffix = "P";
    
    private ButtonType(ButtonLocation loc, int x, int y, String name) {
		spriteUP = Sprite.path + name + unpressedSuffix + Sprite.extension;
		spriteP = Sprite.path + name + pressedSuffix + Sprite.extension;
		location = loc.getBox(x, y);
    }
    
    public String getSpriteUP() {
		return spriteUP;
	}

	public String getSpriteP() {
		return spriteP;
	}

	public Rectangle2D getLocation() {
		return location;
	}
    
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
