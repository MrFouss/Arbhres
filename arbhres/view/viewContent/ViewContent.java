package arbhres.view.viewContent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import javax.swing.JPanel;
import arbhres.view.viewContent.sprite.Scaller;
import arbhres.view.viewContent.sprite.button.ButtonSprite;
import arbhres.view.viewContent.sprite.button.ButtonType;
import arbhres.view.viewContent.sprite.general.GeneralSprite;
import arbhres.view.viewContent.sprite.general.GeneralType;
import arbhres.view.viewContent.sprite.tile.TileLocation;
import arbhres.view.viewContent.sprite.tile.TileSprite;
import arbhres.view.viewContent.sprite.tile.TileType;

public class ViewContent extends JPanel {
	private static final long serialVersionUID = 6598338911570957351L;
	private HashMap<GeneralType, GeneralSprite> background;
	private HashMap<ButtonType, ButtonSprite> buttons;
	private HashMap<TileType, TileSprite[]> tiles;

	private boolean blindMode;
	private boolean seeMode;
	
	public ViewContent() {
	    super();
	    this.setBackground(Color.white);
		this.setPreferredSize(Scaller.applyFactor(new Dimension (4650, 4325)));
		initBackground();
		initButtons();
		initTiles();

		blindMode = false;
		seeMode = false;
	}
	
	public void initViewContent() {
		background.get(GeneralType.EXTENDED_NEXT_MENU).setVisible(false);
		initTiles();

		blindMode = false;
		seeMode = false;
	}
	
	private void initBackground() {
		GeneralType[] gType = GeneralType.getGeneralTypes();
		
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
		TileType[] types = TileType.getTileTypes();
		
		tiles = new HashMap<TileType, TileSprite[]>(types.length);
		
		for (int i = 0; i < types.length; i++) {
			tiles.put(types[i], new TileSprite[20]);
		}
	}
	
	public GeneralSprite getBackground(GeneralType type) {
		return background.get(type);
	}
	
	public ButtonSprite getButton(ButtonType bt) {
		return buttons.get(bt);
	}
	
	//tiles
	
	public TileSprite getTile(TileType type, int index)
	{
		if (index >= 0 && index <= 19) {
			return tiles.get(type)[index];
		}
		return null;
	}
	
	private void setTile(TileType type, int index, TileSprite ts) {
		if (index >= 0 && index <= 19) {
			tiles.get(type)[index] = ts;
			if (!seeMode && (index == 18 || index == 17) && ts != null) {				
				getTile(type, index).setVisible(false);
			}
		}
	}
	
	private TileLocation getTileLocation(int index) {
		if (index >= 0 && index < 16) {
			return TileLocation.GRID;
		} else if (index == 16) {
			return TileLocation.STORE;
		} else if (index <= 19) {
			return TileLocation.NEXT;
		}
		return null;
	}
	
	private Point getTilePosition(int index) {
		if (index >= 0 && index < 16) {
			return new Point(index%4, index/4);
		} else if (index == 16) {
			return new Point(0, 0);
		} else if (index <= 19) {
			return new Point(0, index - 17);
		}
		return null;
	}
	
	public void addTile(TileType type, int index, int value) {
		Point p = getTilePosition(index);
		
		setTile(type, index,
				new TileSprite(getTileLocation(index), type, value, p.x, p.y));
		if (type == TileType.TILE) {
			setTile(TileType.BLIND, index,
					new TileSprite(getTileLocation(index), TileType.BLIND, 0, p.x, p.y));			
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
		TileSprite ts2 = getTile(TileType.BLIND, i1);
		Point B = getTilePosition(i2);
		
		if (ts != null) {
			ts.setPosition(getTileLocation(i2).getCoordinateOfTile(B.x, B.y));
			setTile(type, i2, ts);
			removeTile(type, i1);
			if (type == TileType.TILE) {
				ts2.setPosition(getTileLocation(i2).getCoordinateOfTile(B.x, B.y));	
				setTile(TileType.BLIND, i2, ts2);
				removeTile(TileType.BLIND, i1);
			}
		}
	}

	public void setBlindMode(boolean b) {
		if (blindMode != b) {
			blindMode = b;
			
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
					ts.setVisible(true);
				}
			}
			
			TileType toHide2;
			if (b) {
				toHide2 = TileType.BLIND;
			} else {
				toHide2 = TileType.TILE;
			}
			
			if (getTile(toHide2, 18) != null) {
				getTile(toHide2, 18).setVisible(false);
			}
			if (getTile(toHide2, 17) != null) {
				getTile(toHide2, 17).setVisible(false);
			}
		}
	}
	
	public void setSeeMode(boolean s) {
		if (s != seeMode) {
			seeMode = s;
			
			for (TileSprite[] ts : tiles.values()) {
				if (ts[18] != null) {
					ts[18].setVisible(s);
				}
				if (ts[17] != null) {
					ts[17].setVisible(s);
				}
				background.get(GeneralType.EXTENDED_NEXT_MENU).setVisible(s);
			}
			
			TileType toHide;
			if (blindMode) {
				toHide = TileType.TILE;
			} else {
				toHide = TileType.BLIND;
			}
			
			if (getTile(toHide, 18) != null) {
				getTile(toHide, 18).setVisible(false);
			}
			if (getTile(toHide, 17) != null) {
				getTile(toHide, 17).setVisible(false);
			}
		}
	}
	
	public int getContainingTileIndex(Point p) {
		int index = -1;
		int i = 0;
		int j = 0;
		
		while (i < 4 && index == -1) {
			while (j < 4 && index == -1) {
				if (TileLocation.GRID.getBoxOfTile(i, j).contains(p)) {
					index = j*4 + i;
				}
				j++;
			}
			i++;
			j = 0;
		}
		
		if (index == -1) {
			if (TileLocation.STORE.getBoxOfTile(0, 0).contains(p)) {
				index = 16;
			} else {
				i = 0;
				while (i < 3 && index == -1) {
					if (TileLocation.NEXT.getBoxOfTile(0, i).contains(p)) {
						index = 17+i;
					}
					i++;
				}
			}
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
		background.get(GeneralType.BACKGROUND).paint(g);
		background.get(GeneralType.EXTENDED_NEXT_MENU).paint(g);
		background.get(GeneralType.SCORE).paint(g);
		background.get(GeneralType.HINT).paint(g);
		
		for (ButtonSprite b : buttons.values()) {
			b.paint(g);
		}

		TileType[] tt = TileType.getTileTypes();
		for (int i = 0; i < tt.length; i++) {
			for (TileSprite a : tiles.get(tt[i])) {
				if (a != null) {
					a.paint(g);
				}
			}
		}

	}
}
