package arbhres.view;

import java.awt.Point;

import javax.swing.JFrame;

import arbhres.events.ButtonClickEvent;
import arbhres.events.ButtonClickEvent.Button;
import arbhres.events.MovementTileEvent;
import arbhres.events.ScoreChangeEvent;
import arbhres.events.StateEvent;
import arbhres.events.TileClickEvent;
import arbhres.model.listeners.ModelListener;
import arbhres.view.viewContent.ViewContent;
import arbhres.view.viewContent.sprite.button.ButtonType;
import arbhres.view.viewContent.sprite.general.GeneralType;
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
		return (content.getContainingButtonType(new Point(x, y)) != null);
	}
	
	public boolean isTile(int x, int y) {
		return (content.getContainingTileIndex(new Point(x, y)) != -1);
	}
	
	public int getTileIndex(int x, int y) {
		return content.getContainingTileIndex(new Point(x, y));
	}
	
	public Button getButton(int x, int y) {
		return Correspondance.ButtonTypeToButton(content.getContainingButtonType(new Point(x, y)));
	}

	@Override
	public void switchBlindMode(StateEvent e) {
		content.setBlindMode(e.getState());
	}

	@Override
	public void switchSeeMode(StateEvent e) {
		content.setBlindMode(e.getState());
	}

	@Override
	public void addTile(TileClickEvent e) {
		content.addTile(TileType.TILE, e.getTileIndex(), e.getTileValue());
	}

	@Override
	public void removeTile(TileClickEvent e) {
		content.removeTile(TileType.TILE, e.getTileIndex());
	}

	@Override
	public void pressButton(ButtonClickEvent e) {
		ButtonType bt = Correspondance.ButtonToButtonType(e.getButton());
		content.getButton(bt).setPressed(true);
		content.getBackground(GeneralType.HINT).setValue(bt.getHint());
	}

	@Override
	public void releaseButton(ButtonClickEvent e) {
		ButtonType bt = Correspondance.ButtonToButtonType(e.getButton());
		content.getButton(bt).setPressed(false);
		content.getBackground(GeneralType.HINT).setValue("");
	}

	@Override
	public void highlightTile(TileClickEvent e) {
		content.addTile(TileType.HIGHLIGHT, e.getTileIndex(), e.getTileValue());		
	}

	@Override
	public void unhighlightTile(TileClickEvent e) {
		content.removeTile(TileType.HIGHLIGHT, e.getTileIndex());		
	}

	@Override
	public void addTarget(TileClickEvent e) {
		content.addTile(TileType.TARGET, e.getTileIndex(), e.getTileValue());
	}

	@Override
	public void removeTarget(TileClickEvent e) {
		content.removeTile(TileType.TARGET, e.getTileIndex());	
	}

	@Override
	public void refreshGUI() {
		content.repaint();		
	}

	@Override
	public void restartGUI() {
		content.initViewContent();
	}
	
	@Override
	public void moveTile(MovementTileEvent e) {
		content.moveTile(TileType.TILE, e.getOldIndex(), e.getNewIndex());
	}


	@Override
	public void scoreChange(ScoreChangeEvent e) {
		content.getBackground(GeneralType.SCORE).setValue(String.valueOf(e.getScore()));
	}
	
	public void randomButtonDiscovered(ButtonClickEvent e) {
		ButtonType bt = Correspondance.ButtonToButtonType(e.getButton());
		String s = content.getButton(bt).getHint();
		content.getBackground(GeneralType.HINT).setValue(s);
	}
	
	
	
	public static class Correspondance {
		public static ButtonType ButtonToButtonType(Button e) {
			ButtonType b;
			
			switch (e) {
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
		
		public static Button ButtonTypeToButton(ButtonType t) {
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
