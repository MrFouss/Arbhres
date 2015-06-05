package arbhres.model;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	06/05/2015
 */
public class Erase extends Modifier{
	
	public Erase() {
		super(6000);
	}
	
	/**
	 * Erase the tile chosen by the player
	 * @param score the score be updated
	 * @param grid
	 * @param indexTile
	 * @return the updated score
	 */
	public long apply(long score, Grid grid, int indexTile) {
		//TODO add when getTile is added
		/*if (grid.getTile(indexTile) != -1) {

			grid.addTile(indexTile, -1);
			
			return updateScore(score);
		}*/
		return score;
	}
	
	public boolean isAvailable(long score, Grid grid) {
		
		//TODO replace when isEmptyGrid is implemented
		//return (grid.isEmptyGrid() && super.isAvailable(score));
		return true;
	}

}
