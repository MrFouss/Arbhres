package arbhres.view.viewContent.sprite.general;

import java.awt.geom.Rectangle2D;
import arbhres.view.graphicObject.GraphicObject;
import arbhres.view.graphicObject.Picture;
import arbhres.view.graphicObject.Text;
import arbhres.view.viewContent.sprite.Scaller;

public enum GraphicObjectType
{
	PICTURE,
	TEXT;
	
	public GraphicObject getGraphicObject(Rectangle2D position, String name){
	    GraphicObject go;
	    switch (this) {
	    case PICTURE:
		go = new Picture(Scaller.applyFactor(position), name);
		break;
	    case TEXT:
		go = new Text(Scaller.applyFactor(position), name);
		break;
	    default:
		go = null;
		break;
	    }
	    
	    return go;
	}
}