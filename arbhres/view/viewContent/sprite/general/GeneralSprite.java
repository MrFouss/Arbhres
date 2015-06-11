package arbhres.view.viewContent.sprite.general;

import java.awt.Graphics;
import arbhres.view.viewContent.sprite.Sprite;
import arbhres.view.graphicObject.*;

public class GeneralSprite implements Sprite
{	
	private GraphicObject sprite;
	
	public GeneralSprite(GeneralType gType) {
		if (gType != null) {
			sprite = gType.getType().getGraphicObject(gType.getLocation(), gType.getContent());	
		} else {
			sprite = null;
		}
	}
	
	public void setVisible(boolean visible) {
		sprite.setVisible(visible);
	}
	
	public void setValue(String val) {
		if (sprite instanceof Text) {
			((Text) sprite).setContent(val);
		}
	}
	
	public void paint(Graphics g)
	{
		if (sprite != null) {
		    sprite.paint(g);	
		}
	}
}
