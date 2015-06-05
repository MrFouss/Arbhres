package arbhres.view.viewContent.sprite.general;

import java.awt.geom.Rectangle2D;

import arbhres.view.viewContent.sprite.Sprite;
import arbhres.view.viewContent.sprite.SpriteType;

public enum GeneralType implements SpriteType{
	BACKGROUND			(GraphicObjectType.PICTURE, new Rectangle2D.Double(0, 0, 4650, 3975), "background"),
	SCORE 				(GraphicObjectType.TEXT, new Rectangle2D.Double(1490, 980, 1950, 227), "0123456789"),
	HINT				(GraphicObjectType.TEXT, new Rectangle2D.Double(1489.939, 1060.832, 1857.129, 226.904), "HINT"),
	EXTENDED_NEXT_MENU	(GraphicObjectType.PICTURE,new Rectangle2D.Double( 200, 3975- 200-1870, 550, 1870), "extendedNextMenu");
	
	private String content;
	private Rectangle2D location;
	private GraphicObjectType type;
	
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
				BACKGROUND,
				SCORE,
				EXTENDED_NEXT_MENU
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
