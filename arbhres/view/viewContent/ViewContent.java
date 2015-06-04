package view.viewContent;

import view.graphicObject.GraphicObject;
import view.viewContent.sprite.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.viewContent.sprite.TileSprite.TileLocation;

public class ViewContent extends JPanel {
    
	private final GeneralSprite[] base;
	private TileSprite[] gridTile;
	private TileSprite[] nextTile;
	private TileSprite storeTile;
	private LinkedList<BonusSprite> bonusObj;
	private ButtonSprite[] storeBonus;
	
	public ViewContent() {
	    super();
	    this.setBackground(Color.white);
		this.setPreferredSize(new Dimension (800, 695));
		
		base  = GeneralSprite.getGeneralSpriteList();
		//buttonList = ButtonSprite.getButtonSpriteList();
		storeBonus = ButtonSprite.getButtonSprites();
		gridTile = new TileSprite[4*4];
		nextTile = new TileSprite[3];
		storeTile = null;
		bonusObj = null;
	}
	
	public boolean addGridTile(int x, int y, int value)
	{
	    if (x >= 0 && x < 4 && y >= 0 && y < 4) {
		gridTile[4* y + x] = new TileSprite(TileLocation.GRID, value, x, y);
		return true;
	    }
	    return false;
	}
	
	public boolean removeGridTile(int x, int y)
	{
	    if (x >= 0 && x < 4 && y >= 0 && y < 4) {
		gridTile[x + y * 4] = null;
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
		nextTile[index] = new TileSprite(TileLocation.NEXT, value, 0, index);
	    }
	}
	
	public TileSprite getTile(TileLocation loc, int x, int y) {
	    TileSprite t;
	    
	    switch (loc) {
	    case GRID:
		t = gridTile[4*y + x];
		break;
	    case STORE:
		t = storeTile;
		break;
	    case NEXT:
		t = nextTile[y];
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
	    for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 4; j++) {
				addGridTile(i, j, 1);
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
		paintIteration(g, base);
		paintIteration(g, gridTile);
		paintIteration(g, nextTile);
		if (storeTile != null) {
			storeTile.paint(g);
		}
		paintIteration(g, storeBonus);
	}
}
