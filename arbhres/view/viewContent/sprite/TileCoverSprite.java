package arbhres.view.viewContent.sprite;

import java.awt.Graphics;

import arbhres.view.graphicObject.GraphicObject;
import arbhres.view.graphicObject.Picture;
import arbhres.view.viewContent.Sprite;
import arbhres.view.viewContent.sprite.TileSprite.TileLocation;

public class TileCoverSprite implements Sprite
{
	private final Picture sprite;
	
	public TileCoverSprite(TileCoverSpriteType type, TileSprite ts)
	{
		if (ts == null) {
			this.sprite = new Picture(TileLocation.STORE.getBoxOfTile(0, 0),
					type.filePath);
		} else {
			this.sprite = new Picture(ts.getBox(), type.filePath);
		}
	    this.sprite.setOpacity(type.opacity);
	}
	
	public Picture getSprite() {
		return sprite;
	}
	
	public void paint(Graphics g)
	{
		sprite.paint(g);
	}
	
	public static enum TileCoverSpriteType {
	    HIGHLIGHT ("tileFilter", 0.8f),
	    BLIND ("blindTile", 1f),
	    TARGET ("target", 0.5f);
	    
	    public final String filePath;
	    public final float opacity;
	    
	    private TileCoverSpriteType(String s, float opacity) {
		filePath = path + s + extension; 
		this.opacity = opacity;
	    }
	}
}
