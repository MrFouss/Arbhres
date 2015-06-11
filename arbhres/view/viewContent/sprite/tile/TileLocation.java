package arbhres.view.viewContent.sprite.tile;

import java.awt.Point;
import java.awt.Rectangle;

import arbhres.view.viewContent.sprite.Scaller;

public enum TileLocation {
	GRID(new Point(1075, 1455), 4, 4),
	NEXT(new Point(225, 3975-445-1600), 1, 3),
	STORE(new Point(3750 , 3975-225-500),1, 1);
	
	private final Rectangle position;
   	private final int xSize;
   	private final int ySize;
   	
	private static final int step = 50;
	private static final int width = 500;
	
	private TileLocation(Point pos, int x, int y) {
	    this.position = (new Rectangle(pos.x, pos.y, width, width));
	    this.xSize = x;
	    this.ySize = y;
	}
	
	public Point getCoordinateOfTile(int X, int Y) {
	    int x;
	    int y;

   		x = Math.min(Math.max(X, 0), xSize-1);
   		y = Math.min(Math.max(Y, 0), ySize-1);
   	    
	    return Scaller.applyFactor(new Point(
	    		(int)(position.x+x*step+x*position.width),
				(int)(position.y+y*step+y*position.width)
			));
	}
	
	public Rectangle getBoxOfTile(int X, int Y) {
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
	    
	    return Scaller.applyFactor(new Rectangle(
	    		(int)(position.x+x*step+x*position.width),
				(int)(position.y+y*step+y*position.height),
				(int)(position.width),
				(int)(position.height)
			));
	}
}