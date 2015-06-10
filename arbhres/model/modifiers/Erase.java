package arbhres.model.modifiers;

import arbhres.model.structure.Grid;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/05/2015
 */
public class Erase extends Modifier{
	
	private Grid grid;
	
	/**
	 * Create the modifier with its defined price
	 * 
	 * @param grid The main grid
	 */
	public Erase(Grid grid) {
		super(6000);
		this.grid = grid;
	}
	
	/**
	 * Erase the tile chosen by the player
	 * 
	 * @param  indexTile The index of the tile to Erase (from 0 to 15)
	 * @return the updated score
	 */
	public long apply(int indexTile) {

		if (grid.getTile(indexTile) != -1) {

			grid.removeTile(indexTile);
			
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
		
		return (super.isAvailable(score) && !grid.isGridEmpty() );
	}

}
