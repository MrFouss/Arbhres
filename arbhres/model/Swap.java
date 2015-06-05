package arbhres.model;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	06/05/2015
 */
public class Swap extends Modifier {
	
	/**
	 * Create the modifier with its defined price
	 */
	public Swap() {
		super(8000);
	}
	
	/**
	 * Swaps two tiles of the grid, or a tile of the grid with the tile in the inventory
	 *  
	 * @param grid		 The main grid of the game
	 * @param indexTile1 The index of the first tile
	 * @param indexTile2 The index of the second tile
	 * @return The price
	 */
	public long apply( Grid grid, int indexTile1, int indexTile2) {
		if (indexTile1 != indexTile2) {
			//TODO change when getTile is implemented
			int value1 = (indexTile1 == 16 ? grid.getInventory() : 0/*grid.getTile(indexTile1)*/);
			int value2 = (indexTile2 == 16 ? grid.getInventory() : 0/*grid.getTile(indexTile2)*/);
			
			//TODO change when setInventory is implemented
			if (indexTile1 == 16) {
				/*grid.setInventory*/
			} else {
				grid.addTile(indexTile1, value2); 
			}
			
			if (indexTile2 == 16) {
				/*grid.setInventory*/
			} else {
				grid.addTile(indexTile2, value1); 
			}
			
			return updateScore();
		}
		return 0;
	}
	
	/**
	 * Checks if a modifier can be used
	 * 
	 * @param  score The actual score
	 * @param  grid  The main grid
	 * @return true if the modifier can be used, false otherwise
	 */
	public boolean isAvailable(long score, Grid grid) {
		//TODO add when isEmptyGrid is implemented
		return (super.isAvailable(score) /*&& grid.isEmptyGrid()*/);
	}

}
