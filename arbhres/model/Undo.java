package arbhres.model;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
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
	 * @param score The actual score
	 * @param grid The main grid
	 * @param backup The stored grid
	 * @return the updated score
	 */
	public long apply(Grid grid, Grid backup) {
		//TODO add when the emptyGrid grid method is implemented
		//grid.emptyGrid(); 
		grid = backup;
		backup = null;
		
		return updateScore();
	}
	

	/**
	 * Checks if the modifier can be used
	 * 
	 * @param  score The actual score
	 * @param  grid  The backup grid
	 * @return true if the modifier can be used, false otherwise
	 */
	public boolean isAvailable(long score, Grid backup) {
		return (super.isAvailable(score) && backup != null);
	}
}
