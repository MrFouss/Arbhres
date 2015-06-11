package arbhres.model.modifiers;

import arbhres.model.structure.Grid;
import arbhres.model.structure.GridBackup;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/04/2015
 */
public class Undo extends Modifier {
	
	/**
	 * Create the modifier with its defined price
	 */
	public Undo(){
		super(5000);
	}
	
	/**
	 * Cancel the last movement of the player
	 * 
	 * @param grid The main grid
	 * @param backup The stored grid
	 * @return the updated score
	 */
	public long apply(Grid grid, GridBackup backup) {
		long oldScore = backup.getScore();
		grid.copyGrid(backup);
		backup = null;
		
		return oldScore - updateScore();
	}
	

	/**
	 * Checks if the modifier can be used
	 * 
	 * @param  score The actual score
	 * @param  backup The backup 
	 * @return true if the modifier can be used, false otherwise
	 */
	public boolean isAvailable(long score, GridBackup backup) {
		return (backup != null && super.isAvailable(backup.getScore()) );
	}
}
