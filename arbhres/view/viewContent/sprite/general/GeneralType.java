package arbhres.view.viewContent.sprite.general;

import java.awt.Rectangle;
import arbhres.view.viewContent.sprite.Sprite;
import arbhres.view.viewContent.sprite.SpriteType;

public enum GeneralType implements SpriteType{
	BACKGROUND			(GraphicObjectType.PICTURE, new Rectangle(0, 0, 4650, 4325), "background"),
	SCORE 				(GraphicObjectType.TEXT, new Rectangle(1490, 980, 1950, 227), "0"),
	HINT				(GraphicObjectType.TEXT, new Rectangle(100, 3975, 4450, 200), "HINT : Will be displayed here !"),
	EXTENDED_NEXT_MENU	(GraphicObjectType.PICTURE,new Rectangle( 200, 1905, 550, 1870), "extendedNextMenu");
	
	private String content;
	private Rectangle location;
	private GraphicObjectType type;
	
	private GeneralType(GraphicObjectType type, Rectangle position, String name)
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

	public String getContent() {
		return content;
	}

	public Rectangle getLocation() {
		return location;
	}

	public GraphicObjectType getType() {
		return type;
	}

	public static GeneralType[] getGeneralSprites()
	{
		GeneralType[] g = {
				BACKGROUND,
				HINT,
				SCORE,	
				EXTENDED_NEXT_MENU
		};
		
		return g;
	}
}
