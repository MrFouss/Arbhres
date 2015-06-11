package arbhres.view.viewContent.sprite.tile;

public enum TileType {
	TILE		("tile", 1f),
    HIGHLIGHT 	("tileFilter", 0.8f),
    BLIND 		("blindTile", 1f),
    TARGET 		("target", 0.5f);
	
    private String name;
    private float opacity;

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
    
    public static TileType[] getTileTypes() {
    	TileType[] t = {
    			TILE,
    			BLIND,
    			HIGHLIGHT,
    			TARGET
    	};
    	
    	return t;
    }
}
