package arbhres.view.viewContent.sprite;

import java.awt.geom.Rectangle2D;

public class Scaller {
    public static final double factor = 0.15;
    
    public static Rectangle2D applyFactor(Rectangle2D rec) {
	return new Rectangle2D.Double(rec.getX()*factor, rec.getY()*factor, rec.getWidth()*factor,
				rec.getHeight()*factor);
    }
}
