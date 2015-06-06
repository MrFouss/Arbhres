package arbhres.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import arbhres.view.graphicObject.*;
import arbhres.view.viewContent.ViewContent;

public class View extends JFrame {
    	ViewContent content;
    
	public View() {
	    super("Arbhres!");
	    content = new ViewContent();
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().add(content);
	    pack();
	    setVisible(true);
	}

	public ViewContent getContent() {
		return this.content;
	}
	
	//TODO get obj from coordinate
}
