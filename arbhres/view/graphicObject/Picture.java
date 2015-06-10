package arbhres.view.graphicObject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture extends GraphicObject {
	private BufferedImage image;
	private float opacity;
	
	/**
	 * Build a Picture instance from a file
	 * @param box the box of the object
	 * @param path the path of the file
	 */
	public Picture(Rectangle2D box, String path) {
		super(box);
		this.image = scale(readFile(path));
		this.opacity = 1;
	}
	
	/**
	 * Read a file from its path
	 * @param path the file path
	 * @return The file if it could be correctly read, NULL otherwise
	 */
	private BufferedImage readFile(String path) {
		File file;
		
		try {
			file = new File(path);
		} catch (NullPointerException e) {
			return null;
		}

		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Set the opacity
	 * @param value new value ofthe opacity
	 */
	public void setOpacity(float value) {
		if (value < 0)
		{
			opacity = 0;
		} else {
			if (value > 1) {
				opacity = 1;
			} else {
				opacity = value;
			}
			this.image = opacify(this.image);
		}
	}
	
	/**
	 * Apply the opacity to the picture
	 * @param src source BufferedImage
	 * @return The opacified bufferedImage
	 */
	private BufferedImage opacify(BufferedImage src) {
		if (opacity != 0 && src != null) {
			BufferedImage bi = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
			float[] factors = {1, 1, 1, opacity};
			float[] offsets = {0, 0, 0, 0};
			RescaleOp op = new RescaleOp(factors, offsets, null);
			
			bi = op.filter(src, null);
			
			return bi;	
		}
		return src;
	}

	/**
	 * Apply dimensions to an image
	 * @param src source Image
	 * @return dimensionned image
	 */
	private BufferedImage scale(BufferedImage src) {
	    if (src != null) {
		BufferedImage bi = new BufferedImage((int)box.getWidth(), (int)box.getHeight(), BufferedImage.TYPE_INT_ARGB); 
		Image i = src.getScaledInstance((int)box.getWidth(), (int)box.getHeight(), Image.SCALE_SMOOTH);	
		    
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(i, 0, 0, null);
		g2.dispose();
		return bi;
	    }
	    return null;
	}
	
	@Override
	public void paint(Graphics g) {
		if (image != null && opacity != 0 && visible) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, (int)box.getX(), (int)box.getY(), null);
		}
	}
}
