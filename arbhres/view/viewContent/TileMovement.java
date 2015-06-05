package arbhres.view.viewContent;

import java.awt.Point;
import java.awt.geom.Point2D;

import arbhres.view.viewContent.sprite.tile.TileSprite;

public class TileMovement {
	TileSprite sprite;
	Point2D origin;
	Point2D destination;

	static final double stepLength = 0.01;
	
	public TileMovement(TileSprite t, Point2D o, Point2D d){
		this.sprite = t;
		this.origin = o;
		this.destination = d;
	}
	
	public void initMovement() {
		sprite.setPosition(origin);
	}
	
	public boolean stepForward() {
		Point2D p = new Point2D.Double(sprite.getBox().getX(), sprite.getBox().getY());
		if (p.distance(destination) < destination.distance(origin)*stepLength) {
			sprite.setPosition(destination);
			return true;
		} else {
			sprite.setPosition(new Point2D.Double(
					sprite.getBox().getX() + (destination.getX()-origin.getX())*stepLength,
					sprite.getBox().getY() + (destination.getY()-origin.getY())*stepLength)
			);
			return false;
		}
	}
	
}
