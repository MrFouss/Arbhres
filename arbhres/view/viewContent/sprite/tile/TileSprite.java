package arbhres.view.viewContent.sprite.tile;

import java.awt.Graphics;
import arbhres.view.graphicObject.Picture;
import arbhres.view.viewContent.sprite.Sprite;

public class TileSprite implements Sprite {
	
	private int value;
    private final Picture sprite;
    
    public TileSprite(TileLocation loc, TileType type, int value, int x, int y) {
    	if (type == TileType.TILE) {
			sprite = new Picture(loc.getBoxOfTile(x, y),
					path + type.getname() + value + extension);
		} else {
			sprite = new Picture(loc.getBoxOfTile(x, y),
					path + type.getname() + extension);
			sprite.setOpacity(type.getOpacity());
		}
    	this.value = value;
    }

    public void paint(Graphics g) {
	sprite.paint(g);
    }
	

}