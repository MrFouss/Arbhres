package arbhres.view.graphicObject;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Button extends GraphicObject {
	private Picture unpressed;
	private Picture pressed;
	private ButtonStatus status;
	
	public Button(Rectangle2D box, String Upath, String Ppath) {
		super(box);
		unpressed = new Picture(box, Upath);
		pressed = new Picture(box, Ppath);
		status = ButtonStatus.UNPRESSED;
	}
	
	public void setStatus(ButtonStatus newStatus) {
		status = newStatus;
	}
	
	public void paint(Graphics g) {
		if (visible) {
			switch (status)
			{
				case PRESSED :
					pressed.paint(g);
					break;
				case UNPRESSED :
					unpressed.paint(g);
					break;
				default :
					break;
			}
		}
		
	}
}
