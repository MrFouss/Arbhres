package arbhres.view.viewContent.sprite.button;

import java.awt.geom.Rectangle2D;

import arbhres.view.viewContent.sprite.Scaller;

public enum ButtonLocation {
	   	NEW_GAME	(new Rectangle2D.Double(200, 1385, 550, 400)),
	   	STORE 		(new Rectangle2D.Double(3575, 1105, 400, 400));    
	   	
	   	private final Rectangle2D location;
	   	
	   	private static final double xStep = 50;
	   	private static final double yStep = 125;
	   	
	   	private ButtonLocation(Rectangle2D loc) {
	   	    this.location = loc;
	   	}
	   	
	   	public Rectangle2D getBox(int X, int Y) {
	   	    int x;
	   	    int y;
	   	    
	   	    if (this == STORE) {
	   		x = Math.min(X, 1);
	   		y = Math.min(Y, 3);
		    } else {
			x = 0;
	   		y = 0;
		    }
	   	    
	   	    return Scaller.applyFactor(
	   		    new Rectangle2D.Double(location.getX() + x * xStep + x * location.getWidth(),
	   		    location.getY() + y * yStep + y * location.getHeight(),
	   		    location.getWidth(),
	   		    location.getHeight())
	   		    );
	   	}
    }