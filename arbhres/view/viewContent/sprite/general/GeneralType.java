package arbhres.view.viewContent.sprite.general;

import java.awt.geom.Rectangle2D;

import arbhres.view.viewContent.sprite.Sprite;
import arbhres.view.viewContent.sprite.SpriteType;

/**
 * This class can take many different values. It gathers sprites that does no belong
 * to any other type of sprite.
 * @author esia
 *
 */
public enum GeneralType implements SpriteType{
	BACKGROUND			(GraphicObjectType.PICTURE, new Rectangle2D.Double(0, 0, 4650, 4325), "background"),
	SCORE 				(GraphicObjectType.TEXT, new Rectangle2D.Double(1490, 980, 1950, 227), "0123456789"),
	HINT				(GraphicObjectType.TEXT, new Rectangle2D.Double(100, 3975, 4650 - 200, 200), "HINT"),
	EXTENDED_NEXT_MENU	(GraphicObjectType.PICTURE,new Rectangle2D.Double( 200, 3975- 200-1870, 550, 1870), "extendedNextMenu");
	
	private String content;
	private Rectangle2D location;
	private GraphicObjectType type;
	
	/**
	 * Create a GeneralType object.
	 * @param type the type of graphic object
	 * @param position the position of the object
	 * @param name te file name without path or extension;
	 */
	private GeneralType(GraphicObjectType type, Rectangle2D position, String name)
	{
	   this.type = type;
	   this.location = position;
		   switch (type) {
		case PICTURE:
			this.content = Sprite.path + name + Sprite.extension;
			break;
		case TEXT:
			this.content = name;
			break;
		default:
			content = "";
			break;
		} 
	}
	
	public static GeneralType[] getGeneralSprites()
	{
		GeneralType[] g = {
				SCORE,
				HINT,
				EXTENDED_NEXT_MENU,
				BACKGROUND
		};
		
		return g;
	}

	public String getContent() {
		return content;
	}

	public Rectangle2D getLocation() {
		return location;
	}

	public GraphicObjectType getType() {
		return type;
	}

}
