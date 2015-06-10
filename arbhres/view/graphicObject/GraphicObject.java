package arbhres.view.graphicObject;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class GraphicObject {
    protected Rectangle2D box;
    protected boolean visible;
	
    /**
     * Build a graphic object delimited by a box and visible by default
     * 
     * @param box The box of this instance
     */
	public GraphicObject(Rectangle2D box) {
	    if (box == null) {
	    	this.box = new Rectangle2D.Double();
	    } else {
	    	this.box = box;
	    }
	    visible = true;
	}
	
	/**
	 * Return the box of the graphic object
	 * @return A copy of the instance's box
	 */
	public Rectangle2D getBox() {
	    return new Rectangle2D.Double(box.getX(), box.getY(), box.getWidth(), box.getHeight());
	}
	
	/**
	 * Does a point belong to the box of a given graphic object ?
	 * @param point the point to test
	 * @return TRUE of the point is in the box, FALSE otherwise
	 */
	public boolean contains(Point2D point) {
		return box.contains(point);
	}
	
	/**
	 * Is this object visible ?
	 * @return TRUE if it is, FALSE otherwise
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Set the visible field of this graphical object
	 * @param visible TRUE if it should be set visible, FALSE otherwise
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Set the coordinates of the object
	 * @param point the new origin of the box
	 */
	public void setLocation(Point2D point) {
		try {
			box.setRect(new Rectangle2D.Double(point.getX(), point.getY(), box.getWidth(), box.getHeight()));
		} catch (NullPointerException e) {}
	}
	
	/**
	 * Paints the graphical object
	 * @param g The Graphics context
	 */
	public abstract void paint(Graphics g);
}
