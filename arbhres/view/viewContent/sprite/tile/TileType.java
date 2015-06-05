package arbhres.view.viewContent.sprite.tile;

import arbhres.view.viewContent.sprite.SpriteType;

public enum TileType implements SpriteType{
	TILE		("tile", 1f),
    HIGHLIGHT 	("tileFilter", 0.8f),
    BLIND 		("blindTile", 1f),
    TARGET 		("target", 0.5f);
	
    private String name;
    private float opacity;
    
    public static TileType[] getTiles() {
    	TileType[] t = {
    			TILE,
    			HIGHLIGHT,
    			BLIND,
    			TARGET
    	};
    	
    	return t;
    }
    
    private TileType(String name, float opacity) {
    	this.name = name;
    	this.opacity = opacity;
    }

	public String getname() {
		return name;
	}

	public float getOpacity() {
		return opacity;
	}
}
