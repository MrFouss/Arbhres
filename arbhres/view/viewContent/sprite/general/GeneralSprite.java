package arbhres.view.viewContent.sprite.general;

import java.awt.Graphics;
import arbhres.view.viewContent.sprite.Sprite;
import arbhres.view.graphicObject.*;

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
		sprite.setVisibility(visible);
	}
	
	public void paint(Graphics g)
	{
	    sprite.paint(g);
	}
}
