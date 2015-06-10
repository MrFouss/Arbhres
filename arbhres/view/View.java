package arbhres.view;

import javax.swing.JFrame;

import arbhres.view.viewContent.ViewContent;
import arbhres.view.viewContent.sprite.tile.TileLocation;
import arbhres.view.viewContent.sprite.tile.TileType;

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
