package arbhres.view.viewContent.sprite.button;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import arbhres.view.graphicObject.*;
import arbhres.view.viewContent.sprite.Sprite;

public class ButtonSprite implements Sprite {   
    private Button sprite;
    
    public ButtonSprite(ButtonType bType) {
    	sprite = new Button(bType.getLocation(), bType.getSpriteUP(), bType.getSpriteP());
    }

    public boolean contains(Point2D point) {
    	return sprite.contains(point);
    }
    
    public void setPressed(boolean pressed) {
    	if (pressed) {
			sprite.setStatus(ButtonStatus.PRESSED);
		} else {
			sprite.setStatus(ButtonStatus.UNPRESSED);
		}
    }
	
    public void paint(Graphics g) {
    	sprite.paint(g);
    }
}
