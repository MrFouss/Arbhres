package arbhres.view.viewContent.sprite;

import java.awt.Graphics;
import arbhres.view.graphicObject.Picture;
import arbhres.view.viewContent.Sprite;

public class TileCoverSprite implements Sprite
{
	private final Picture sprite;
	
	public TileCoverSprite(TileCoverSpriteType type, TileSprite ts)
	{
	    this.sprite = new Picture(ts.getBox(), type.filePath);
	    this.sprite.setOpacity(type.opacity);
	}
	
	public void paint(Graphics g)
	{
		sprite.paint(g);
	}
	
	public static enum TileCoverSpriteType {
	    HIGHLIGHT ("tileFilter", 0.5f),
	    BLIND ("blindTile", 1f),
	    TARGET ("tileTarget", 0.5f);
	    
	    public final String filePath;
	    public final float opacity;
	    
	    private static final String extention = ".png";
	    
	    private TileCoverSpriteType(String s, float opacity) {
		filePath = path + s + extention; 
		this.opacity = opacity;
	    }
	}
}
