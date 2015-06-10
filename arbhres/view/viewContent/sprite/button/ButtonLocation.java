package arbhres.view.viewContent.sprite.button;

import java.awt.geom.Rectangle2D;

import arbhres.view.viewContent.sprite.Scaller;

/** 
 * @author Esia Belbachir
 * @version 1.0
 * @since 06/10/15
 * Localize the buttons.
 */
public enum ButtonLocation {
	   	NEW_GAME	(new Rectangle2D.Double(200, 1385, 550, 400), 1, 1),
	   	STORE 		(new Rectangle2D.Double(3575, 1105, 400, 400), 2, 4);    
	   	
	   	private final Rectangle2D location;
	   	private final int xSize;
	   	private final int ySize;
	   	
	   	private static final double xStep = 50;
	   	private static final double yStep = 125;
	   	
	   	/**
	   	 * Creates a new ButtonLocation.
	   	 * @param loc Value of the location field
	   	 */
	   	private ButtonLocation(Rectangle2D loc, int x, int y) {
	   		location = loc;
	   		xSize = x;
	   		ySize = y;
	   	}
	   	
	   	/**
	   	 * Return the location of the button at the given coordinates.
	   	 * 
	   	 * If it is not within the the button table (specific for each button),
	   	 * the closest existing button location will be returned. 
	   	 * @param X x coordinate
	   	 * @param Y y coordinate
	   	 * @return the new location
	   	 */
	   	public Rectangle2D getBox(int X, int Y) {
	   	    int x;
	   	    int y;

	   		x = Math.min(Math.max(X, 0), xSize-1);
	   		y = Math.min(Math.max(Y, 0), ySize-1);
	   	    
	   	    return Scaller.applyFactor(
	   		    new Rectangle2D.Double(location.getX() + x * xStep + x * location.getWidth(),
	   		    location.getY() + y * yStep + y * location.getHeight(),
	   		    location.getWidth(),
	   		    location.getHeight())
	   		    );
	   	}
    }