package arbhres.controller.events;

/**
 * @author	Maxime Brodat "maxime.brodat@fouss.fr"
 * @version	1.0
 * @since	05/30/2015
 */

public class TileClickEvent {

	/**
	 * Tile index, between 0 and 16: [0-15] corresponds to the tiles within the
	 * grid and 16 corresponds to the inventory tile
	 */
	private int tileIndex;

	/**
	 * Constructor for TileClickEvent
	 * 
	 * @param tileIndex the index of the clicked tile (0-15 for common tiles,
	 * 16 for the inventory tile)
	 */
	public TileClickEvent(int tileIndex) {
		this.tileIndex = tileIndex;
	}

	/**
	 * Getter for the tile index
	 * 
	 * @return the index of the clicked tile
	 */
	public int getTileIndex() {
		return this.tileIndex;
	}
}
