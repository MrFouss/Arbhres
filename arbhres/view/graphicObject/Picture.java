package arbhres.view.graphicObject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Picture extends GraphicObject {
	private BufferedImage image;
	private float opacity;
	
	public Picture(Rectangle box, String path) {
		super(box);
		this.image = scale(readFile(path));
		this.opacity = 1;
	}
	
	private BufferedImage readFile(String path) {
		File file;
		
		try {
			file = new File(path);
			try {
				return ImageIO.read(file);
			} catch (IOException e) {}
		} catch (NullPointerException e) {}
		
		return null;
	}

	public void setOpacity(float value) {
		if (value < 0) {
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
