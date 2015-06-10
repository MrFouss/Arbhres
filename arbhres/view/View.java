package arbhres.view;

import java.awt.Point;

import javax.swing.JFrame;

import arbhres.events.ButtonClickEvent;
import arbhres.events.ButtonClickEvent.Button;
import arbhres.events.MovementTileEvent;
import arbhres.events.StateEvent;
import arbhres.events.TileClickEvent;
import arbhres.model.listeners.ModelListener;
import arbhres.view.viewContent.ViewContent;
import arbhres.view.viewContent.sprite.button.ButtonType;
import arbhres.view.viewContent.sprite.tile.TileLocation;
import arbhres.view.viewContent.sprite.tile.TileType;

public class View extends JFrame implements ModelListener {
    	ViewContent content;
    
	public View() {
	    super("Arbhres!");
	    content = new ViewContent();
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().add(content);
	    pack();
	    setVisible(true);		
	}

	public boolean isButton(int x, int y) {
		return (content.getButtonPosition(x, y)!=null);
	}
	
	public boolean isTile(int x, int y) {
		return (content.getTilePosition(TileLocation.GRID, x, y) != null 
				|| content.getTilePosition(TileLocation.STORE, x, y) != null 
				|| content.getTilePosition(TileLocation.NEXT, x, y) != null);
	}
	
	public int getTileIndex(int x, int y) {
		Point p = content.getTilePosition(TileLocation.GRID, x, y);
		
		if (p != null) {
			return (int) (p.getY()*4 + p.getX());
		}
		
		p = content.getTilePosition(TileLocation.STORE, x, y);
		
		if (p != null) {
			return 16;
		}
		
		p = content.getTilePosition(TileLocation.NEXT, x, y);
		
		if (p != null) {
			return (int) (p.getY()+17);
		}
		
		return -1;
	}
	
	public Button getButton(int x, int y) {
		return Correspondance.getButton(content.getButtonPosition(x, y));
	}
	
	public ViewContent getContent() {
		return this.content;
	}

	@Override
	public void switchBlindMode(StateEvent e) {
		
	}

	@Override
	public void switchSeeMode(StateEvent e) {

	}

	@Override
	public void addTile(TileClickEvent e) {

	}

	@Override
	public void removeTile(TileClickEvent e) {

	}

	@Override
	public void pressButton(ButtonClickEvent e) {
		content.getButton(Correspondance.getButtonType(e)).setPressed(true);
		content.repaint();
	}

	@Override
	public void releaseButton(ButtonClickEvent e) {
		content.getButton(Correspondance.getButtonType(e)).setPressed(false);		
	}

	@Override
	public void highlightTile(TileClickEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unhighlightTile(TileClickEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTarget(TileClickEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTarget(TileClickEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshGUI() {
		content.repaint();		
	}

	@Override
	public void restartGUI() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void moveTile(MovementTileEvent e) {
		// TODO Auto-generated method stub
		
	}
	//TODO get obj from coordinate
	
	public static class Correspondance {
		public static TileLocation getTileLocation(TileClickEvent e) {
			if(e.getTileIndex() > 16) {
				return TileLocation.NEXT;
			} else if (e.getTileIndex() == 16) {
				return TileLocation.STORE;
			}
			return TileLocation.GRID;
		}
		
		public static Point getCoordinate(TileClickEvent e) {
			Point p;
			switch (getTileLocation(e)) {
			case NEXT:
				p = new Point((e.getTileIndex()-16)%4, (e.getTileIndex()-16)/4);
				break;
			case GRID:
				p = new Point(e.getTileIndex()%4, e.getTileIndex()/4);
				 break;				
			case STORE:
				p = new Point(0, (e.getTileIndex()-17));
				break;
			default:
				p = null;
				break;
			}
			
			return p;
		}
		
		public static ButtonType getButtonType(ButtonClickEvent e) {
			ButtonType b;
			
			switch (e.getButton()) {
			case BONUS_ERASE:
				b = ButtonType.ERASE;
				break;
			case BONUS_PAUSE:
				b = ButtonType.PAUSE;
				break;
			case BONUS_RANDOM:
				b = ButtonType.RANDOM;
				break;
			case BONUS_SEE:
				b = ButtonType.SEE;
				break;
			case BONUS_SWAP:
				b = ButtonType.SWAP;
				break;
			case BONUS_TURNLEFT:
				b = ButtonType.TURN_LEFT;
				break;
			case BONUS_TURNRIGHT:
				b = ButtonType.TURN_RIGHT;
				break;
			case BONUS_UNDO:
				b = ButtonType.UNDO;
				break;
			case NEW_GAME:
				b = ButtonType.NEW_GAME;
				break;
			default:
				b = null;
				break;
			}
			return b;
		}
		
		public static Button getButton(ButtonType t) {
			Button b;
			
			switch (t) {
			case ERASE:
				b = Button.BONUS_ERASE;
				break;
			case PAUSE:
				b = Button.BONUS_PAUSE;
				break;
			case RANDOM:
				b = Button.BONUS_RANDOM;
				break;
			case SEE:
				b = Button.BONUS_SEE;
				break;
			case SWAP:
				b = Button.BONUS_SWAP;
				break;
			case TURN_LEFT:
				b = Button.BONUS_TURNLEFT;
				break;
			case TURN_RIGHT:
				b = Button.BONUS_TURNRIGHT;
				break;
			case UNDO:
				b = Button.BONUS_UNDO;
				break;
			case NEW_GAME:
				b = Button.NEW_GAME;
				break;
			default:
				b = null;
				break;
			}
			return b;
		}
	}

}
