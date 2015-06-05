package arbhres.view.graphicObject;

import java.awt.Graphics;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class GraphicObject
{
    protected Rectangle2D box;
    protected boolean visibility;
	
	public GraphicObject()
	{
	    box = new Rectangle2D.Double();
	    visibility = true;
	}
	
	public GraphicObject(Rectangle2D box)
	{
	    if (box == null) {
	    	this.box = new Rectangle2D.Double();
	    } else {
	    	this.box = box;
	    }
	    visibility = true;
	}
	
	public Rectangle2D getBox() {
	    return new Rectangle2D.Double(box.getX(), box.getY(), box.getWidth(), box.getHeight());
	}
	
	public boolean contains(Point2D point)
	{
	    try 
	    {
		return box.contains(point);
	    } catch (NullPointerException e) {
    		return false;
	    }
	}
	
	public void setVisibility(boolean visible) {
		visibility = visible;
	}
	
	public void resize(Dimension2D dim)
	{
		try 
		{
			box.setRect(new Rectangle2D.Double(box.getX(), box.getY(), dim.getWidth(), dim.getHeight()));
		} catch (NullPointerException e) {}
	}
	
	public void resize(double factor)
	{
		box.setRect(new Rectangle2D.Double(box.getX(), box.getY(), box.getWidth()*factor, box.getHeight()*factor));
	}
	
	public void setLocation(Point2D point)
	{
		try 
		{
			box.setRect(new Rectangle2D.Double(point.getX(), point.getY(), box.getWidth(), box.getHeight()));
		} catch (NullPointerException e) {}
	}
	
	public abstract void paint(Graphics g);
}
