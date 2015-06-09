package arbhres.model.modifiers;

import arbhres.model.structure.Grid;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/05/2015
 */
public class Swap extends Modifier {
	
	private Grid grid;
	
	/**
	 * Create the modifier with its defined price
	 * 
	 * @param grid The main grid
	 */
	public Swap(Grid grid) {
		super(8000);
		this.grid = grid;		
	}
	
	/**
	 * Swaps two tiles of the grid, or a tile of the grid with the tile in the inventory
	 *  
	 * @param indexTile1 The index of the first tile
	 * @param indexTile2 The index of the second tile
	 * @return The price
	 */
	public long apply(int indexTile1, int indexTile2) {
		if (indexTile1 != indexTile2) {

			int value1 = (indexTile1 == 16 ? grid.getInventory() : grid.getTile(indexTile1));
			int value2 = (indexTile2 == 16 ? grid.getInventory() : grid.getTile(indexTile2));
			

			if (indexTile1 == 16) {
				grid.setInventory(value2);
			} else {
				grid.addTile(indexTile1, value2); 
			}
			
			if (indexTile2 == 16) {
				grid.setInventory(value1);
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
	 * @return true if the modifier can be used, false otherwise
	 */
	public boolean isAvailable(long score) {

		return (super.isAvailable(score) && grid.isGridEmpty());
	}

}
