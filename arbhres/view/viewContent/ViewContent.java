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
	
	public void addTile(TileLocation tLoc, TileType tType, int x, int y, int value)
	{
	    switch (tLoc) {
		case GRID:
			x = Math.min(3, x);
			y = Math.min(3, x);
			grid.get(tType)[y * 4 + x] = new TileSprite(tLoc, tType, value, x, y);
			break;
		case NEXT:
			y = Math.min(y, 2);
			next.get(tType)[y] = new TileSprite(tLoc, tType, value, x, y);
			break;
		case STORE:
			store.put(tType, new TileSprite(tLoc, tType, value, x, y));
			break;
		default:
			break;
		}
	}
	
	public void removeTile(TileLocation tLoc, TileType tType, int x, int y)
	{
		switch (tLoc) {
		case GRID:
			x = Math.min(3, x);
			y = Math.min(3, x);
			grid.get(tType)[4 * y + x] = null;
			break;
		case NEXT:
			y = Math.min(y, 2);
			next.get(tType)[y] = null;
			break;
		case STORE:
			store.put(tType, null);
			break;
		default:
			break;
		}
	}
	
	public TileSprite getTile(TileLocation tLoc, TileType tType, int x, int y)
	{
		TileSprite t;
		
		switch (tLoc) {
		case GRID:
			t = grid.get(tType)[4 * y + x];
			break;
		case NEXT:
			y = Math.min(y, 2);
			t = next.get(tType)[y];
			break;
		case STORE:
			t = store.get(tType);
			break;
		default:
			t = null;
			break;
		}

		return t;
	}
	
	public void moveTile(TileType type, TileLocation loc1, int x1, int y1,
			TileLocation loc2, int x2, int y2) {
		TileSprite ts = getTile(loc1, type, x1, y1);
		switch (loc2) {
		case GRID:
			grid.get(type)[y2*4 + x2] = ts;
			break;
		case NEXT:
			next.get(type)[y2] = ts;
			break;
		case STORE:
			store.put(type, ts);
			break;
		default:
			break;
		}
		removeTile(loc1, type, x1, y1);
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
		//cfgkdjfg kjsd fksj fs
		for (GeneralSprite s : background.values()) {
			if (s != null) {
				s.paint(g);
			}
		}
		
		for (ButtonSprite s : buttons.values()) {
			if (s != null) {
				s.paint(g);
			}
		}
		
		for	(TileType t : grid.keySet()) {
			for (TileSprite s : grid.get(t)) {
				if (s != null) {
					s.paint(g);
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
