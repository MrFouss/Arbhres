package arbhres.view.viewContent.sprite;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import arbhres.view.viewContent.RelativeSize;
import arbhres.view.viewContent.Sprite;
import arbhres.view.graphicObject.*;

public enum GeneralSprite implements Sprite
{	
	BACKGROUND		(GraphicObjectType.PICTURE , new Rectangle2D.Double(0, 0, 4650, 3975), "background", null),
	SCORE 			(GraphicObjectType.TEXT, new Rectangle2D.Double(1490, 980, 1950, 227), "0123456789", null),
	//HINTS			(GraphicObjectType.TEXT, new Rectangle2D.Double(1489.939, 1060.832, 1857.129, 226.904), "", null),
	EXTENDED_NEXT_MENU	(GraphicObjectType.PICTURE,new Rectangle2D.Double( 200, 3975- 200-1870, 550, 1870), "extendedNextMenu", null);
	
	private GraphicObject sprite;
	
	public static GeneralSprite[] getGeneralSprites()
	{
		GeneralSprite[] g = {
				BACKGROUND,
				SCORE,
				EXTENDED_NEXT_MENU
		};
		
		return g;
	}
	
	private GeneralSprite(GraphicObjectType type, Rectangle2D position, String name, Font font)
	{
	    this.sprite = type.getGraphicObject(position, name, font);
	}

	public void paint(Graphics g)
	{
	    sprite.paint(g);
	}
	
	private enum GraphicObjectType
	{
		PICTURE,
		TEXT;
		
		public GraphicObject getGraphicObject(Rectangle2D position, String name, Font font){
		    GraphicObject go;
		    switch (this) {
		    case PICTURE:
			go = new Picture(RelativeSize.applyFactor(position), path + name + extension);
			break;
		    case TEXT:
			go = new Text(RelativeSize.applyFactor(position), name, font);
			break;
		    default:
			go = null;
			break;
		    }
		    
		    return go;
		}
	}
}
