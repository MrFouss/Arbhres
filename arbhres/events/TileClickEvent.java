package arbhres.events;

/**
 * @author	Maxime Brodat "maxime.brodat@fouss.fr"
 * @version	1.0
 * @since	05/30/2015
 */

public class TileClickEvent {

	/**
	 * Tile index, between 0 and 16: [0-15] corresponds to the tiles within the
	 * grid, 16 corresponds to the inventory tile, [17-19] corresponds to the tiles
	 * in the queue (17 = next coming tile, 19 = last coming tile)
	 */
	private int tileIndex;
	private int tileValue;

	/**
	 * Constructor for TileClickEvent with one parameter
	 * 
	 * @param tileIndex the index of the clicked tile (0-15 for common tiles,
	 * 16 for the inventory tile, 17-19 for the queue tiles)
	 */
	public TileClickEvent(int tileIndex) {
		this.tileIndex = tileIndex;
		this.tileValue = -1;
	}
	
	/**
	 * Constructor for TileClickEvent with two parameters
	 * 
	 * @param tileIndex the index of the clicked tile (0-15 for common tiles,
	 * 16 for the inventory tile)
	 */
	public TileClickEvent(int tileIndex, int tileValue) {
		this.tileIndex = tileIndex;
		this.tileValue = tileValue;
	}

	/**
	 * Getter for the tile index
	 * 
	 * @return the index of the clicked tile
	 */
	public int getTileIndex() {
		return this.tileIndex;
	}
	
	/**
	 * Getter for the tile value
	 * 
	 * @return the value of the clicked tile
	 */
	public int getTileValue() {
		return this.tileValue;
	}
}
