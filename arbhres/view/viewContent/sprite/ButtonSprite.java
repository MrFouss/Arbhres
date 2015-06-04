package arbhres.view.viewContent.sprite;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import arbhres.view.graphicObject.*;
import arbhres.view.viewContent.RelativeSize;
import arbhres.view.viewContent.Sprite;

public enum ButtonSprite implements Sprite {   
    NEW_GAME	(ButtonLocation.NEW_GAME, 0, 0, "newGame"),
    MYSTERY	(ButtonLocation.STORE, 0, 0, "random"),
    ERASE	(ButtonLocation.STORE, 1, 0, "erase"),
    PAUSE	(ButtonLocation.STORE, 0, 1, "pause"),
    SWAP	(ButtonLocation.STORE, 1, 1, "swap"),
    SEE		(ButtonLocation.STORE, 0, 2, "see"),
    UNDO	(ButtonLocation.STORE, 1, 2, "undo"),
    TURN_LEFT	(ButtonLocation.STORE, 0, 3, "turnLeft"),
    TURN_RIGHT	(ButtonLocation.STORE, 1, 3, "turnRight");
   
    private static final String unpressedSuffix = "UP";
    private static final String pressedSuffix = "P";
    
    private Button sprite;
    
    private ButtonSprite(ButtonLocation loc, int x, int y, String name) {
	this.sprite = new Button(loc.getButtonBox(x, y),
		path + name + unpressedSuffix + extension,
		path + name + pressedSuffix + extension);
    }
    
    public static ButtonSprite[] getButtonSprites() {
	ButtonSprite[] s = {
		NEW_GAME,
		MYSTERY,
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

    public Rectangle2D getBox() {
	return sprite.getBox();
    }
    
	public Button getSprite() {
		return sprite;
	}
	
    public void paint(Graphics g) {
	sprite.paint(g);
    }
    
    private static enum ButtonLocation {
   	NEW_GAME	(new Rectangle2D.Double(200, 3975 - 2190 - 400, 550, 400)),
   	STORE 		(new Rectangle2D.Double(3575, 3975 - 795 - 2075, 400, 400));    
   	
   	private final Rectangle2D position;
   	
   	private static final double xStep = 50;
   	private static final double yStep = 125;
   	
   	private ButtonLocation(Rectangle2D loc) {
   	    this.position = loc;
   	}
   	
   	public Rectangle2D getButtonBox(int X, int Y) {
   	    int x;
   	    int y;
   	    
   	    if (this == STORE) {
   		x = Math.min(X, 1);
   		y = Math.min(Y, 3);
	    } else {
		x = 0;
   		y = 0;
	    }
   	    
   	    return RelativeSize.applyFactor(
   		    new Rectangle2D.Double(position.getX() + x * xStep + x * position.getWidth(),
   		    position.getY() + y * yStep + y * position.getHeight(),
   		    position.getWidth(),
   		    position.getHeight())
   		    );
   	}
    }
}
