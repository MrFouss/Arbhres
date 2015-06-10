package arbhres.view.graphicObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Text extends GraphicObject {
	private String content;
	private Font font;
	
	public Text(Rectangle2D box, String content) {
		super(box);
		try {
			this.content = content;
		} catch (NullPointerException e) {
			this.content = "";
		}
		this.font = new Font("Arial", Font.PLAIN, 50);
	}
	
	public void setContent(String s) {
		this.content = s;
	}
	
	private void adaptFont(Graphics2D g2) {
		FontMetrics fm = g2.getFontMetrics(font);
		double textH = fm.getAscent() - fm.getDescent();
		double textW = fm.stringWidth(content);
		double boxW = box.getWidth();
		double boxH = box.getHeight();
		
		while (boxW >= textW || boxH >= textH) {
			font = new Font(font.getName(), font.getStyle(), font.getSize()+1);
			fm = g2.getFontMetrics(font);
			textH = fm.getAscent() - fm.getDescent();
			textW = fm.stringWidth(content);
		}
		
		while (boxW < textW || boxH < textH) {
			font = new Font(font.getName(), font.getStyle(), font.getSize()-1);
			fm = g2.getFontMetrics(font);
			textH = fm.getAscent() - fm.getDescent();
			textW = fm.stringWidth(content);
		}
	}
	
	private Point2D centerText(Graphics2D g2) {
		FontMetrics fm = g2.getFontMetrics(font);
		
		Point2D p = new Point2D.Double(box.getCenterX() - fm.stringWidth(content)/2, 
				box.getCenterY() + (fm.getAscent() - fm.getDescent())/2);
		
		return p;
	}
	
	public void paint(Graphics g) {
		if (visible) {
			Graphics2D g2 = (Graphics2D) g;
			
			adaptFont(g2);
			
			Point2D point = centerText(g2);

			g2.setFont(font);
			g2.setColor(Color.black);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING , RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.drawString(content, (float) point.getX(), (float) point.getY());			
		}
	}
}
