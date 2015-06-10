package arbhres.model.modifiers;

import arbhres.model.structure.Grid;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/05/2015
 */
public class Target extends Modifier{
	
	private Grid grid;
	private int index;
	/**
	 * Create the modifier with its defined price
	 * 
	 * @param grid the main grid
	 */
	public Target(Grid grid, int targetIndex) {
		super(-8000);
		this.grid = grid;
		this.index = targetIndex;
	}
	
	/**
	 * Print a target on a random tile, which will be erased the next turn
	 * 
	 * @return The earned points
	 */
	public long apply() {
		
		index = grid.selectHighest();
		
		return updateScore();
	}

	public int getIndex() {
		return index;
	}
	
	public boolean isAvailable() {
		return (index == -1);
	}

}
