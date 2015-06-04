package view.viewContent.sprite;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import view.graphicObject.Picture;
import view.viewContent.RelativeSize;
import view.viewContent.Sprite;

public class TileSprite implements Sprite {
    private final Picture sprite;
    
    private static final String prefix = "tile";
    
    public TileSprite(TileLocation loc, int value, int x, int y) {
	this.sprite = new Picture(loc.getBoxOfTile(x, y), path + prefix + value + extension);
    }
    
    public Rectangle2D getBox() {
	return sprite.getBox();
    }

    public void paint(Graphics g) {
	sprite.paint(g);
    }

    public static enum TileLocation {
	GRID(new Point2D.Double(1075, 1455)),
	NEXT(new Point2D.Double(225, 3975-445-1600)),
	STORE(new Point2D.Double(3750 , 3975-225-500));

	private final Rectangle2D position;

	private static final double step = 50;
	private static final double width = 500;

	private TileLocation(Point2D rec) {
	    this.position = (new Rectangle2D.Double(rec.getX(), rec.getY(), width, width));
	}

	public Rectangle2D getBoxOfTile(int X, int Y) {
	    int x;
	    int y;
	    
	    switch (this) {
	    case GRID:
		x = Math.min(X, 3);
		y = Math.min(Y, 3);
		break;
	    case NEXT:
		x = 0;
		y = Math.min(Y, 2);
		break;
	    case STORE:
		x = 0;
		y = 0;
		break;
	    default:
		x = 0;
		y = 0;
		break;
	    }
	    
	    return RelativeSize.applyFactor(new Rectangle2D.Double(position.getX()+x*step+x*position.getWidth(),
			position.getY()+y*step+y*position.getHeight(), position.getWidth(), position.getHeight()));
	}
    }
}
