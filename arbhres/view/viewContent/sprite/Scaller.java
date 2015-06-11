package arbhres.view.viewContent.sprite;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Scaller {
    public static final double factor = 0.15;
    
    public static Rectangle applyFactor(Rectangle rec) {
	return new Rectangle((int)(rec.x*factor), (int)(rec.y*factor),
			(int)(rec.width*factor), (int)(rec.height*factor));
    }
    
    public static Point applyFactor(Point rec) {
    	return new Point((int)(rec.x*factor), (int)(rec.y*factor));
    }
    
    public static Dimension applyFactor(Dimension d) {
    	return new Dimension((int)(d.width*factor), (int)(d.height*factor));
    }
}
