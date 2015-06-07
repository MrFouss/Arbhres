package arbhres.view.viewContent.sprite.tile;

import arbhres.view.viewContent.sprite.SpriteType;

public enum TileType implements SpriteType{
	TILE		("tile", 1f, 0),
    HIGHLIGHT 	("tileFilter", 0.8f, -1),
    BLIND 		("blindTile", 1f, -2),
    TARGET 		("target", 0.5f, -3);
	
    private String name;
    private float opacity;
    private int value;
    
    public static TileType[] getTiles() {
    	TileType[] t = {
    			TILE,
    			HIGHLIGHT,
    			BLIND,
    			TARGET
    	};
    	
    	return t;
    }
    
    public static int getValue(TileType t) {
    	return t.value;
    }
    
    private TileType(String name, float opacity, int value) {
    	this.name = name;
    	this.opacity = opacity;
    	this.value = value;
    }

	public String getname() {
		return name;
	}

	public float getOpacity() {
		return opacity;
	}
	
	public int getValue() {
		return value;
	}
}
