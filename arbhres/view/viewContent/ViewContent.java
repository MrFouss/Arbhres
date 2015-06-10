package arbhres.view.viewContent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import javax.swing.JPanel;

import arbhres.events.ButtonClickEvent.Button;
import arbhres.view.viewContent.sprite.Scaller;
import arbhres.view.viewContent.sprite.button.ButtonSprite;
import arbhres.view.viewContent.sprite.button.ButtonType;
import arbhres.view.viewContent.sprite.general.GeneralSprite;
import arbhres.view.viewContent.sprite.general.GeneralType;
import arbhres.view.viewContent.sprite.tile.TileLocation;
import arbhres.view.viewContent.sprite.tile.TileSprite;
import arbhres.view.viewContent.sprite.tile.TileType;

public class ViewContent extends JPanel {
    
	private HashMap<GeneralType, GeneralSprite> background;
	private HashMap<ButtonType, ButtonSprite> buttons;
	private HashMap<TileType, TileSprite[]> tiles;

	private boolean blindMode;
	
	public ViewContent() {
	    super();
	    this.setBackground(Color.white);
		this.setPreferredSize(Scaller.applyFactor(new Dimension (4650, 4325)));
		initViewContent();
	}
	
	public void initViewContent() {
		initBackground();
		initButtons();
		initTiles();

		blindMode = false;
	}
	
	private void initBackground() {
		GeneralType[] gType = GeneralType.getGeneralSprites();
		
		background = new HashMap<GeneralType, GeneralSprite>(gType.length);
		
		for (int i = 0; i < gType.length; i++) {
			background.put(gType[i], new GeneralSprite(gType[i]));
		}
		
		background.get(GeneralType.EXTENDED_NEXT_MENU).setVisible(false);
	}
	
	private void initButtons() {
		ButtonType[] bType = ButtonType.getButtonTypes();
		
		buttons = new HashMap<ButtonType, ButtonSprite>(bType.length);
		
		for (int i = 0; i < bType.length; i++) {
			buttons.put(bType[i], new ButtonSprite(bType[i]));
		}
	}
	
	private void initTiles() {
		TileType[] types = TileType.getTiles();
		
		tiles = new HashMap<TileType, TileSprite[]>(types.length);
		
		for (int i = 0; i < types.length; i++) {
			tiles.put(types[i], new TileSprite[19]);
		}
	}
	
	//background
	
	public GeneralSprite getBackground(GeneralType type) {
		return background.get(type);
	}
	
	//buttons
	
	public ButtonSprite getButton(ButtonType bt) {
		return buttons.get(bt);
	}
	
	//tiles
	
	public TileSprite getTile(TileType type, int index)
	{
		if (index >= 0 && index < 19) {
			return tiles.get(type)[index];
		}
		return null;
	}
	
	private void setTile(TileType type, int index, TileSprite ts) {
		if (index >= 0 && index < 19) {
			tiles.get(type)[index] = ts;
		}
	}
	
	private TileLocation getTileLocation(int index) {
		if (index >= 0 && index < 16) {
			return TileLocation.GRID;
		} else if (index == 16) {
			return TileLocation.STORE;
		} else if (index < 19) {
			return TileLocation.NEXT;
		}
		return null;
	}
	
	private Point getTilePosition(int index) {
		if (index >= 0 && index < 16) {
			return new Point(index%4, index/4);
		} else if (index == 16) {
			return new Point(0, 0);
		} else if (index < 19) {
			return new Point(0, index - 17);
		}
		return null;
	}
	
	public void addTile(TileType type, int index, int value) {
		Point p = getTilePosition(index);
		setTile(type, index,
				new TileSprite(getTileLocation(index), type, value, (int)p.getX(), (int)p.getY()));
		if (type == TileType.TILE) {
			setTile(TileType.BLIND, index,
					new TileSprite(getTileLocation(index), TileType.BLIND, 0, (int)p.getX(), (int)p.getY()));
			if (blindMode) {
				getTile(type, index).setVisible(false);
			} else {
				getTile(TileType.BLIND, index).setVisible(false);
			}
		}
		

	}
	
	public void removeTile(TileType type, int index)
	{
		setTile(type, index, null);
		if (type == TileType.TILE) {
			setTile(TileType.BLIND, index, null);
		}
	}
	
	public void moveTile(TileType type, int i1, int i2) {
		TileSprite ts = getTile(type, i1);
		Point A = getTilePosition(i1);
		Point B = getTilePosition(i2);
		
		if (ts != null) {
			ts.setPosition(getTileLocation(i2).getCoordinateofTile((int)B.getX(), (int)B.getY()));
			setTile(type, i2, ts);
			removeTile(type, i1);
		}

		if (type == TileType.TILE) {
			moveTile(TileType.BLIND, i1, i2);
		}
	}

	public void setBlindMode(boolean b) {
		if (blindMode != b) {
			TileType toHide;
			TileType toShow;
			
			if (b) {
				toHide = TileType.TILE;
				toShow = TileType.BLIND;
			} else {
				toHide = TileType.BLIND;
				toShow = TileType.TILE;
			}
				
			for (TileSprite ts : tiles.get(toHide)) {
				if (ts != null) {
					ts.setVisible(false);
				}
			}
			
			for (TileSprite ts : tiles.get(toShow)) {
				if (ts != null) {
					ts.setVisible(false);
				}
			}
		}
	}
	
	public int getContainingTileIndex(Point p) {
		int index = -1;
		int i = 0;
		
		while (i < tiles.get(TileType.TILE).length && index == -1) {
			if (tiles.get(TileType.TILE)[i] != null) {
				if (tiles.get(TileType.TILE)[i].contains(p)) {
					index = i;
				}
			}
			i++;
		}

		return index;
	}
	
	public ButtonType getContainingButtonType(Point p) {
		ButtonType bt = null;
		
		for (ButtonType s : ButtonType.getButtonTypes()) {
			if (buttons.get(s).contains(p)) {
				bt = s;
			}
		}
		
		return bt;
	}
	
	public void paint(Graphics g)
	{
		for (GeneralSprite s : background.values()) {
			s.paint(g);
		}
		
		for (ButtonSprite b : buttons.values()) {
			b.paint(g);
		}

		for (TileSprite[] ts : tiles.values()) {
			for (TileSprite a : ts) {
				if (a != null) {
					a.paint(g);
				}
			}
		}
	}
}
