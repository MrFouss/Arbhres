package arbhres.view.viewContent.sprite;

import java.awt.Graphics;


/**
 * Represents an element of a whole image.
 * @author esia
 *
 */
public interface Sprite {
	/**
	 * The path to the image folder of the project
	 */
    public static final String path = "./src/img/";
    /**
     * The file extension of the sprites (if extracted from a file)
     */
    public static final String extension = ".png";

    /**
     * Paint the sprite
     * @param g Garphic context
     */
    public void paint(Graphics g);
    
}
