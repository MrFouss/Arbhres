package arbhres.view.viewContent.sprite.button;

import java.awt.Rectangle;
import arbhres.view.viewContent.sprite.Scaller;

/** 
 * @author Esia Belbachir
 * @version 1.0
 * @since 06/10/15
 * Localize the buttons.
 */
public enum ButtonLocation {
	   	NEW_GAME	(new Rectangle(200, 1385, 550, 400), 1, 1),
	   	STORE 		(new Rectangle(3575, 1105, 400, 400), 2, 4);    
	   	
	   	private final Rectangle location;
	   	private final int xSize;
	   	private final int ySize;
	   	
	   	private static final double xStep = 50;
	   	private static final double yStep = 125;
	   	
	   	/**
	   	 * Creates a new ButtonLocation.
	   	 * @param loc Value of the location field
	   	 */
	   	private ButtonLocation(Rectangle loc, int x, int y) {
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
	   	public Rectangle getBox(int X, int Y) {
	   	    int x;
	   	    int y;

	   		x = Math.min(Math.max(X, 0), xSize-1);
	   		y = Math.min(Math.max(Y, 0), ySize-1);
	   	    
	   	    return Scaller.applyFactor(
	   		    new Rectangle((int)(location.x + x * xStep + x * location.width),
	   		    (int)(location.y + y * yStep + y * location.height),
	   		    location.width, location.height)
	   		    );
	   	}
    }