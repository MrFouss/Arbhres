package arbhres.view.graphicObject;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class GraphicObject {
    protected Rectangle box;
    protected boolean visible;
	
	public GraphicObject(Rectangle box) {
	    if (box == null) {
	    	this.box = new Rectangle();
	    } else {
	    	this.box = box;
	    }
	    visible = true;
	}
	
	public Rectangle getBox() {
	    return new Rectangle(box.x, box.y, box.width, box.height);
	}
	
	public boolean contains(Point point) {
		return box.contains(point);
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setLocation(Point point) {
		try {
			box.setBounds(new Rectangle(point.x, point.y, box.width, box.height));
		} catch (NullPointerException e) {}
	}
	
	public abstract void paint(Graphics g);
}
