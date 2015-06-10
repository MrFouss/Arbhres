package arbhres.events;

/**
 * @author	Maxime Brodat "maxime.brodat@fouss.fr"
 * @version	1.0
 * @since	06/10/2015
 */

public class MovementTileEvent {

	/**
	 * Tile index, between 0 and 16: [0-15] corresponds to the tiles within the
	 * grid, 16 corresponds to the inventory tile, [17-19] corresponds to the tiles
	 * in the queue (17 = next coming tile, 19 = last coming tile)
	 */
	private int oldIndex;
	private int newIndex;

	/**
	 * Constructor for MovementTileEvent
	 * 
	 * @param tileIndex the index of the clicked tile (0-15 for common tiles,
	 * 16 for the inventory tile, 17-19 for the queue tiles)
	 */
	public MovementTileEvent(int oldIndex, int newIndex) {
		this.oldIndex = oldIndex;
		this.newIndex = newIndex;
	}

	/**
	 * Getter for the old tile index
	 * 
	 * @return the index of the tile before the move
	 */
	public int getOldIndex() {
		return this.oldIndex;
	}
	
	/**
	 * Getter for the new tile index
	 * 
	 * @return the index of the tile after the move
	 */
	public int getNewIndex() {
		return this.newIndex;
	}
}
