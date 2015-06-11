package arbhres.view.graphicObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Text extends GraphicObject {
	private String content;
	private Font font;
	
	public Text(Rectangle box, String content) {
		super(box);
		this.content = content;
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
	
	private Point centerText(Graphics2D g2) {
		FontMetrics fm = g2.getFontMetrics(font);
		
		Point p = new Point((int)(box.getCenterX() - fm.stringWidth(content)/2), 
				(int)(box.getCenterY() + (fm.getAscent() - fm.getDescent())/2));
		
		return p;
	}
	
	public void paint(Graphics g) {
		if (visible && content != null && content != "") {
			Graphics2D g2 = (Graphics2D) g;
			
			adaptFont(g2);
			
			Point point = centerText(g2);

			g2.setFont(font);
			g2.setColor(Color.black);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING , RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.drawString(content, (float) point.getX(), (float) point.getY());			
		}
	}
}
