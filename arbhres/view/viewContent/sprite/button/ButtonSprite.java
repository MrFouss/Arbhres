package arbhres.view.viewContent.sprite.button;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import arbhres.view.graphicObject.*;
import arbhres.view.viewContent.sprite.Sprite;

/** 
 * @author Esia Belbachir
 * @version 1.0
 * @since 06/10/15
 * Contains all the needed functionnality to safely manage a button.
 */
public class ButtonSprite implements Sprite {   
    private Button sprite;
    private String hint;
    /**
     * Create a button from a buttonType object.
     * @param bType button type
     */
    public ButtonSprite(ButtonType bType) {
    	sprite = new Button(bType.getLocation(), bType.getSpriteUP(), bType.getSpriteP());
    	hint = bType.getHint();
    }

    /**
     * Return if a given point is in the button box.
     * @param point the point to test
     * @return TRUE if it does, FALSE otherwise
     */
    public boolean contains(Point point) {
    	return sprite.contains(point);
    }
    
    /**
     * Sets the button status
     * @param pressed the new status
     */
    public void setPressed(boolean pressed) {
    	if (pressed) {
			sprite.setStatus(ButtonStatus.PRESSED);
		} else {
			sprite.setStatus(ButtonStatus.UNPRESSED);
		}
    }
    
    public String getHint() {
    	return hint;
    }
	
    @Override
    public void paint(Graphics g) {
    	sprite.paint(g);
    }
}
