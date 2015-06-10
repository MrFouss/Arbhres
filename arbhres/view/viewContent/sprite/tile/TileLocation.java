package arbhres.view.viewContent.sprite.tile;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import arbhres.view.viewContent.sprite.Scaller;

public enum TileLocation {
	GRID(new Point2D.Double(1075, 1455), 4, 4),
	NEXT(new Point2D.Double(225, 3975-445-1600), 1, 3),
	STORE(new Point2D.Double(3750 , 3975-225-500),1, 1);
	
	private final Rectangle2D position;
   	private final int xSize;
   	private final int ySize;
   	
	private static final double step = 50;
	private static final double width = 500;
	
	private TileLocation(Point2D rec, int x, int y) {
	    this.position = (new Rectangle2D.Double(rec.getX(), rec.getY(), width, width));
	    this.xSize = x;
	    this.ySize = y;
	}
	
	public Point2D getCoordinateofTile(int X, int Y) {
	    int x;
	    int y;

   		x = Math.min(Math.max(X, 0), xSize-1);
   		y = Math.min(Math.max(Y, 0), ySize-1);
   	    
	    return Scaller.applyFactor(new Point2D.Double(
	    		position.getX()+x*step+x*position.getWidth(),
				position.getY()+y*step+y*position.getHeight()
			));
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
	    
	    return Scaller.applyFactor(new Rectangle2D.Double(
	    		position.getX()+x*step+x*position.getWidth(),
				position.getY()+y*step+y*position.getHeight(),
				position.getWidth(),
				position.getHeight()
			));
	}
}