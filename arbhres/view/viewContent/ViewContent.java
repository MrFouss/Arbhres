package arbhres.view.viewContent;

import arbhres.view.viewContent.sprite.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import arbhres.view.viewContent.sprite.TileCoverSprite.TileCoverSpriteType;
import arbhres.view.viewContent.sprite.TileSprite.TileLocation;

public class ViewContent extends JPanel {
    
	private final GeneralSprite[] background;
	
	private ButtonSprite[] buttons;
	
	private TileSprite[] gridTiles;
	private TileSprite[] nextTiles;
	private TileSprite storeTile;
	
	//Bonus
	
	private TileCoverSprite[] gridBlindTiles;
	private TileCoverSprite[] nextBlindTiles;
	private TileCoverSprite storeBlindTile;
	
	private TileCoverSprite target;
	
	private TileCoverSprite[] tileHighlight;
	
	public ViewContent() {
	    super();
	    this.setBackground(Color.white);
		this.setPreferredSize(new Dimension (800, 695));
		
		background  = GeneralSprite.getGeneralSprites();
		
		buttons = ButtonSprite.getButtonSprites();
		
		gridTiles = new TileSprite[4*4];
		initArray(gridTiles);
		nextTiles = new TileSprite[3];
		initArray(gridTiles);
		storeTile = null;
		
		//bonus
		
		gridBlindTiles = new TileCoverSprite[4*4];
		initArray(gridTiles);
		gridBlindTiles = new TileCoverSprite[3];
		initArray(gridTiles);
		storeTile = null;
		
		target = new TileCoverSprite(TileCoverSpriteType.TARGET	, null);
		
		tileHighlight = new TileCoverSprite[4];
		initArray(tileHighlight);
	}
	
	private void initArray(Sprite[] s) {
		for (int i = 0; i < s.length; i++) {
			s[i] = null;
		}
	}
	
	public boolean addGridTile(int x, int y, int value)
	{
	    if (x >= 0 && x < 4 && y >= 0 && y < 4) {
		gridTiles[4* y + x] = new TileSprite(TileLocation.GRID, value, x, y);
		return true;
	    }
	    return false;
	}
	
	public boolean removeGridTile(int x, int y)
	{
	    if (x >= 0 && x < 4 && y >= 0 && y < 4) {
		gridTiles[x + y * 4] = null;
		return true;
	    }
	    return false;
	}
	
	public void addStoreTile(int value)
	{
	    storeTile = new TileSprite(TileLocation.STORE, value, 0, 0);
	}
	
	public void removeStoreTile(int value)
	{
	    storeTile = new TileSprite(TileLocation.STORE, 0, 0, 0);
	}
	
	public void setNextTile(int index, int value) {
	    if (index >= 0 && index < 3) {
		nextTiles[index] = new TileSprite(TileLocation.NEXT, value, 0, index);
	    }
	}
	
	public TileSprite getTile(TileLocation loc, int x, int y) {
	    TileSprite t;
	    
	    switch (loc) {
	    case GRID:
		t = gridTiles[4*y + x];
		break;
	    case STORE:
		t = storeTile;
		break;
	    case NEXT:
		t = nextTiles[y];
		break;
	    default:
		t = null;
		break;
	    }
	    
	    return t;
	}
	
	private void paintIteration(Graphics g, Sprite[] sl)
	{
		for (Sprite s : sl)
		{
		    if (s != null) {
			s.paint(g);
		    }
			
		}
	}
	
	public void move()
	{
		int value = 3;

		addGridTile(0, 0, 1);
		addGridTile(0, 1, 2);
		addGridTile(0, 2, 3);
		
	    for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 4; j++) {
				addGridTile(i, j, value);
				value *= 2;
		    }
		}
	    
	    for (int k = 0; k < 3; k++) {
		setNextTile(k, 1);
	    }
	    
	    addStoreTile(1);
	    
	    repaint();
	}

	public void paint(Graphics g)
	{    
		paintIteration(g, background);
		paintIteration(g, buttons);
		paintIteration(g, gridTiles);
		paintIteration(g, nextTiles);
		if (storeTile != null) {
			storeTile.paint(g);
		}
		target.paint(g);
		
		TileCoverSprite t = new TileCoverSprite(TileCoverSpriteType.BLIND, gridTiles[0]);
		t.paint(g);
		
		TileCoverSprite t1 = new TileCoverSprite(TileCoverSpriteType.HIGHLIGHT, gridTiles[10]);
		t1.paint(g);
	}
}
