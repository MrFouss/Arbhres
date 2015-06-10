package arbhres.view.viewContent.sprite;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Scaller {
    public static final double factor = 0.15;
    
    public static Rectangle2D applyFactor(Rectangle2D rec) {
	return new Rectangle2D.Double(rec.getX()*factor, rec.getY()*factor, rec.getWidth()*factor,
				rec.getHeight()*factor);
    }
    
    public static Point2D applyFactor(Point2D rec) {
    	return new Point2D.Double(rec.getX()*factor, rec.getY()*factor);
    }
    
    public static Dimension applyFactor(Dimension d) {
    	return new Dimension((int)(d.getWidth()*factor), (int)(d.getHeight()*factor));
    }
}
