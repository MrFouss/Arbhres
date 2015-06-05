package arbhres.model.modifiers;

import arbhres.model.structure.Grid;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	06/05/2015
 */
public class Erase extends Modifier{
	
	/**
	 * Create the modifier with its defined price
	 */
	public Erase() {
		super(6000);
	}
	
	/**
	 * Erase the tile chosen by the player
	 * 
	 * @param  score 	 The score be updated
	 * @param  grid		 The main grid of the game
	 * @param  indexTile The index of the tile to Erase (from 0 to 15)
	 * @return the updated score
	 */
	public long apply(Grid grid, int indexTile) {

		if (indexTile >= 0 && indexTile <= 15 && grid.getTile(indexTile) != -1) {

			grid.addTile(indexTile, -1);
			
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
		
		return (super.isAvailable(score) &&grid.isGridEmpty() );
	}

}
