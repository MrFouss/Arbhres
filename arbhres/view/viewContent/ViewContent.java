package arbhres.view.viewContent;

import arbhres.view.viewContent.sprite.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

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
	
	private HashMap<TileType, TileSprite[]> grid;
	private HashMap<TileType, TileSprite[]> next;
	private HashMap<TileType, TileSprite> store;
	
	public ViewContent() {
	    super();
	    this.setBackground(Color.white);
		this.setPreferredSize(new Dimension (800, 695));
		
		initBackground();
		initButtons();
		initGrid();
		initNext();
		initStore();
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
	
	private void initGrid() {
		TileType[] tType = TileType.getTiles();
		
		grid = new HashMap<TileType, TileSprite[]>(tType.length);
		
		for (int i = 0; i < tType.length; i++) {
			grid.put(tType[i], new TileSprite[4*4]);
		}
	}
	
	private void initNext() {
		TileType[] tType = TileType.getTiles();
		
		next = new HashMap<TileType, TileSprite[]>(tType.length);
		
		for (int i = 0; i < tType.length; i++) {
			next.put(tType[i],new TileSprite[3]);
		}
	}
	
	private void initStore() {
		TileType[] tType = TileType.getTiles();
		
		store = new HashMap<TileType, TileSprite>(tType.length);
		
		for (int i = 0; i < tType.length; i++) {
			store.put(tType[i], null);
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
	
	public TileSprite getTile(TileLocation loc, TileType type, int x, int y)
	{
		TileSprite ts;
		
		switch (loc) {
			case GRID:
				x = Math.min(3, Math.max(x, 0));
				y = Math.min(3, Math.max(y, 0));
				ts = grid.get(type)[y * 4 + x];
				break;
			case NEXT:
				y = Math.min(2, Math.max(y, 0));
				ts = next.get(type)[y];
				break;
			case STORE:
				ts = store.get(type);
				break;
			default:
				ts = null;
				break;
		 }

		 return ts;
	}
	
	private void setTile(TileLocation loc, TileType type, int x, int y, TileSprite ts) {
		switch (loc) {
		case GRID:
			x = Math.min(3, Math.max(x, 0));
			y = Math.min(3, Math.max(y, 0));
			grid.get(type)[y * 4 + x] = ts;
			break;
		case NEXT:
			y = Math.min(2, Math.max(y, 0));
			next.get(type)[y] = ts;
			break;
		case STORE:
			store.put(type, ts);
			break;
		default:
			break;
		}
	}
	
	public void addTile(TileLocation loc, TileType type, int x, int y, int value) {
		setTile(loc, type, x, y, new TileSprite(loc, type, value, x, y));
	}
	
	public void removeTile(TileLocation loc, TileType type, int x, int y)
	{
		setTile(loc, type, x, y, null);
	}
	
	public void moveTile(TileType type, TileLocation loc1, int x1, int y1,
			TileLocation loc2, int x2, int y2) {
		TileSprite ts = getTile(loc1, type, x1, y1);
		ts.setPosition(loc2.getCoordinateofTile(x2, y2));
		if (ts != null) {
			setTile(loc2, type, x2, y2, ts);
			removeTile(loc1, type, x1, y1);
		}

	}

	public void setVisible(TileType type, boolean visible) {
		for (TileSprite[] g : grid.values()) {
			for (TileSprite ts : g) {
				ts.setVisible(false);
			}
		}
		
		for (TileSprite[] g : next.values()) {
			for (TileSprite ts : g) {
				ts.setVisible(false);
			}
		}
		
		for (TileSprite g : store.values()) {
			g.setVisible(false);
		}
	}
	
	public void paint(Graphics g)
	{
		addTile(TileLocation.GRID, TileType.TILE, 0, 0, 6);
		moveTile(TileType.TILE, TileLocation.GRID, 0, 0, TileLocation.GRID, 0, 1);
		
		for (GeneralSprite s : background.values()) {
			if (s != null) {
				s.paint(g);
			}
		}
		
		for (ButtonSprite b : buttons.values()) {
			if (b != null) {
				b.paint(g);
			}
		}
		
		for	(TileType t : grid.keySet()) {
			for (TileSprite z : grid.get(t)) {
				if (z != null) {
					z.paint(g);
				}
			}
		}
		
		for	(TileType t : next.keySet()) {
			for (TileSprite s : next.get(t)) {
				if (s != null) {
					s.paint(g);
				}
			}
		}
		
		for	(TileSprite t : store.values()) {
			if (t != null) {
				t.paint(g);
			}
			
		}
	}
}
