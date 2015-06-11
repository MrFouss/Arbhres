package arbhres.view.viewContent.sprite.button;

import java.awt.Graphics;
import java.awt.Point;
import arbhres.view.graphicObject.*;
import arbhres.view.viewContent.sprite.Sprite;

public class ButtonSprite implements Sprite {   
    private Button sprite;
    private String hint;

    public ButtonSprite(ButtonType bType) {
    	if (bType != null) {
        	sprite = new Button(bType.getLocation(), bType.getSpriteUP(), bType.getSpriteP());
        	hint = bType.getHint();			
		} else {
			sprite = null;
			hint = null;
		}
    }
    
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
	
    public boolean contains(Point point) {
    	if (point != null) {
    		return sprite.contains(point);
		}
    	return false;
    	
    }
    
    @Override
    public void paint(Graphics g) {
    	if (sprite != null) {
		    sprite.paint(g);	
		}
    }
}
