package arbhres.view.viewContent.sprite.general;

import java.awt.Graphics;
import arbhres.view.viewContent.sprite.Sprite;
import arbhres.view.graphicObject.*;

/** 
 * @author Esia Belbachir
 * @version 1.0
 * @since 06/10/15
 * Contains all the necessary methods to manage a GeneralSprite.
 */
public class GeneralSprite implements Sprite
{	
	private GraphicObject sprite;
	
	public GeneralSprite(GeneralType gType) {
		sprite = gType.getType().getGraphicObject(gType.getLocation(), gType.getContent());
	}
	
    public GeneralSprite getButton(GeneralSprite[] tab) {
    	int i = 0;
    	
    	while (i < tab.length && this != tab[i]) {
    		i++;
    	}
    	
    	if (i < tab.length) {
			return tab[i];
		}
    	return null;
    }
	
	public void setVisible(boolean visible) {
		sprite.setVisible(visible);
	}
	
	public void setValue(String val) {
		if (sprite instanceof Text) {
			Text t = (Text) sprite;
			t.setContent(val);
		}
	}
	
	public void paint(Graphics g)
	{
	    sprite.paint(g);
	}
}
