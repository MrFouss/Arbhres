package arbhres.view.viewContent;

import arbhres.view.viewContent.sprite.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collection;
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
			next.put(tType[i], new TileSprite[3]);
		}
	}
	
	private void initStore() {
		TileType[] tType = TileType.getTiles();
		
		store = new HashMap<TileType, TileSprite>(tType.length);
		
		for (int i = 0; i < tType.length; i++) {
			store.put(tType[i], null);
		}
	}
	
	public void addTile(TileLocation tLoc, TileType tType, int x, int y, int value)
	{
	    switch (tLoc) {
		case GRID:
			x = Math.min(3, x);
			y = Math.min(3, x);
			grid.get(tType)[4 * y + x] = new TileSprite(tLoc, tType, value, x, y);
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
	
	public void moveTiles( TileType type) {
		/*TileSprite t;
		switch (srcLoc) {
		case GRID:
			t = grid.get(type)[(int) (p1.getX() + 4* p1.getY())];
			break;
		case NEXT:
			t = next.get(type)[(int)p1.getY()];
			break;
		case STORE:
			t = store.get(type);
			break;
		default:
			break;
		}*/
	}
	
	private void paintIteration(Graphics g, Collection<T extends Sprite> sl)
	{
		for (Sprite s : sl)
		{
		    if (s != null) {
			s.paint(g);
		    }
			
		}
	}

	public void paint(Graphics g)
	{    
		paintIteration((g, background.values());
		
	}
}
